import FenetreSuppelementaires.AlertBox;
import FenetreSuppelementaires.FenetreAide;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class EcranDeJeu {

    private static int ronde = 0;
    static Scene sceneJeu;
    static int COLONES = 75;
    static int RANGEES = 75;
    static Case[][] listeCases = new Case[COLONES][RANGEES];


    private static int [] score = new int[7];

    private static Label lblRonde = new Label();
    private static Label lblNombreDeCellules = new Label();

    private static final Font font = new Font("Times New Roman", 18);

    private static final int LONGEUR_FENETRE = 950;
    private static final int LARGEUR_FENETRE = 1600;

    private static final Font fontSousTitre = Font.font("Times New Roman", FontWeight.BOLD, 24);

    private static Image imgPlay = new Image("/res/play_arrow.png");
    private static Image imgNext = new Image("/res/skip_next.png");
    private static Image imgPause = new Image("/res/pause.png");
    private static Image imgClear = new Image("/res/close-box.png");
    private static Image imgSave = new Image("/res/save_black.png");
    private static Image imgLoad = new Image("/res/folder_open.png");
    private static Image imgAide = new Image("/res/help_circle.png");

    private static Button btnPause = new Button();
    private static Button btnClear = new Button();
    private static Button btnSave = new Button();
    private static Button btnLoad = new Button();
    private static Button btnNext = new Button();
    private static Button btnPlay = new Button();
    private static Button btnAide = new Button();

    private static CheckBox checkModePinceau = new CheckBox("Mode Peinture");
    private static CheckBox checkModeEfface = new CheckBox("Mode Efface");

    private static double vitesse = 1;

    static boolean loop = false;
    private static Slider sliderNombrePourVivre = new Slider(0,4,2);


    private static GridPane gpJeu;
    private static StackPane spJeu = new StackPane();
    private static ScrollPane scrollJeu = new ScrollPane();

    static Slider sliderColones = new Slider(0, 150, 75);
    static Slider sliderRangees = new Slider(0, 150, 75);
    private static Slider sliderVitesse = new Slider(0.25, 2, 1);
    private static Slider sliderTaillePeintureEfface = new Slider(1,7,1);

    private static Label lblNombrePourVivre = new Label("Nombre de cellules adjacentes pour vivre:");
    private static Label lblVitesse = new Label("Vitesse du simulateur:");
    private static Label lblRangees = new Label("Nombre de rangées:");
    private static Label lblColones = new Label("Nombre de colones:");

    private static VBox vbxOptionsReglages = new VBox(10);
    private static VBox vbxReglages = new VBox(10);

    private static VBox vbxOptionsMode = new VBox(10);
    private static VBox vbxModes = new VBox(10);

    static void creationScene(){

        GridPane tempGrid = new GridPane();
        tempGrid.setHgap(0.1);
        tempGrid.setVgap(0.1);
        tempGrid.setAlignment(Pos.CENTER);
        tempGrid.setMinWidth(900);
        tempGrid.setMinHeight(900);

        tempGrid = initialisation(tempGrid, listeCases, RANGEES, COLONES);
        gpJeu = tempGrid;
        gpJeu.setStyle("-fx-background-color: white");

        spJeu.getChildren().add(gpJeu);
        spJeu.setPadding(new Insets(10, 10, 10, 10));
        spJeu.setStyle("-fx-background-color: white");

        scrollJeu.setContent(spJeu);
        scrollJeu.setPrefSize(950, 900);

        Label lblTitre = new Label("Zazo's Game of Life");
        lblTitre.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
        StackPane spTitre = new StackPane();
        spTitre.getChildren().add(lblTitre);


        Label lblModes = new Label("Modes:");
        lblModes.setFont(fontSousTitre);

        Label lblTaillePinceauEfface = new Label("Sélectionner la taille du pinceau ou efface:");
        lblTaillePinceauEfface.setFont(font);

        sliderTaillePeintureEfface.setShowTickLabels(true);
        sliderTaillePeintureEfface.setShowTickMarks(true);
        sliderTaillePeintureEfface.setMajorTickUnit(2);
        sliderTaillePeintureEfface.setMinorTickCount(0);
        sliderTaillePeintureEfface.setBlockIncrement(2);
        sliderTaillePeintureEfface.setSnapToTicks(true);
        sliderTaillePeintureEfface.setDisable(true);

        checkModeEfface.setFont(font);
        checkModeEfface.setOnMouseClicked(e-> modeSwitch(false));

        checkModePinceau.setFont(font);
        checkModePinceau.setOnMouseClicked(e-> modeSwitch(true));

        HBox hbxCheckBox = new HBox(10);
        hbxCheckBox.getChildren().addAll(checkModePinceau,checkModeEfface);


        vbxOptionsMode.getChildren().addAll(hbxCheckBox, lblTaillePinceauEfface, sliderTaillePeintureEfface);

        vbxModes.getChildren().addAll(lblModes,vbxOptionsMode);

        vbxModes.setPadding(new Insets(10,10,10,10));
        vbxModes.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));


        Label lblReglages = new Label("Réglages:");
        lblReglages.setFont(fontSousTitre);


        lblNombrePourVivre.setFont(font);

        sliderNombrePourVivre.setShowTickLabels(true);
        sliderNombrePourVivre.setShowTickMarks(true);
        sliderNombrePourVivre.setMajorTickUnit(1);
        sliderNombrePourVivre.setMinorTickCount(0);
        sliderNombrePourVivre.setBlockIncrement(1);
        sliderNombrePourVivre.setSnapToTicks(true);


        lblColones.setFont(font);

        sliderColones.setShowTickLabels(true);
        sliderColones.setShowTickMarks(true);
        sliderColones.setMajorTickUnit(25);
        sliderColones.setMinorTickCount(0);
        sliderColones.setBlockIncrement(25);
        sliderColones.setSnapToTicks(true);
        sliderColones.valueProperty().addListener(e->{if(sliderColones.getValue()%25 == 0) resizeGridPane(((int) sliderRangees.getValue()), ((int) sliderColones.getValue()));});


        lblRangees.setFont(font);

        sliderRangees.setShowTickLabels(true);
        sliderRangees.setShowTickMarks(true);
        sliderRangees.setMajorTickUnit(25);
        sliderRangees.setMinorTickCount(0);
        sliderRangees.setBlockIncrement(25);
        sliderRangees.setSnapToTicks(true);
        sliderRangees.valueProperty().addListener(e->{if(sliderRangees.getValue()%25 == 0) resizeGridPane(((int) sliderRangees.getValue()), ((int) sliderColones.getValue()));});

        lblVitesse.setFont(font);

        sliderVitesse.setShowTickLabels(true);
        sliderVitesse.setShowTickMarks(true);
        sliderVitesse.setMajorTickUnit(0.25);
        sliderVitesse.setMinorTickCount(0);
        sliderVitesse.setBlockIncrement(0.25);
        sliderVitesse.setSnapToTicks(true);
        sliderVitesse.valueProperty().addListener(e->vitesse = sliderVitesse.getValue());

        vbxOptionsReglages.getChildren().addAll(lblNombrePourVivre, sliderNombrePourVivre , lblColones, sliderColones, lblRangees, sliderRangees, lblVitesse,sliderVitesse);

        vbxReglages.getChildren().addAll(lblReglages, vbxOptionsReglages);
        vbxReglages.setPadding(new Insets(10,10,10,10));
        vbxReglages.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));


        Label lblInformationsPartie = new Label("Informations sur la partie:");
        lblInformationsPartie.setFont(fontSousTitre);

        lblRonde.setFont(font);
        lblNombreDeCellules.setFont(font);

        VBox vbxInformationsPartie = new VBox(10);
        vbxInformationsPartie.getChildren().addAll(lblInformationsPartie,lblRonde,lblNombreDeCellules);
        vbxInformationsPartie.setPadding(new Insets(10,10,10,10));
        vbxInformationsPartie.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));


        VBox vbxDroite = new VBox(10);
        vbxDroite.getChildren().addAll(vbxInformationsPartie,vbxModes,vbxReglages);


        ImageView ivClear = new ImageView(imgClear);
        ivClear.setFitHeight(30);
        ivClear.setFitHeight(30);
        ivClear.setPreserveRatio(true);
        btnClear.setGraphic(ivClear);
        btnClear.setTooltip(new Tooltip("Effacer et Recommencer"));
        btnClear.setStyle("-fx-background-color: white;");
        btnClear.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnClear.setOnMouseClicked(e->clear());


        ImageView ivSave = new ImageView(imgSave);
        ivSave.setFitHeight(30);
        ivSave.setFitHeight(30);
        ivSave.setPreserveRatio(true);
        btnSave.setGraphic(ivSave);
        btnSave.setTooltip(new Tooltip("Saveguarder la Partie"));
        btnSave.setStyle("-fx-background-color: white;");
        btnSave.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnSave.setOnMouseClicked(e->SaveLoad.save());

        ImageView ivLoad = new ImageView(imgLoad);
        ivLoad.setFitHeight(30);
        ivLoad.setFitHeight(30);
        ivLoad.setPreserveRatio(true);
        btnLoad.setGraphic(ivLoad);
        btnLoad.setTooltip(new Tooltip("Ouvrir la Partie enregistrée"));
        btnLoad.setStyle("-fx-background-color: white;");
        btnLoad.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnLoad.setOnMouseClicked(e->{
            clear();
            SaveLoad.load();
        });


        ImageView ivNext = new ImageView(imgNext);
        ivNext.setFitHeight(30);
        ivNext.setFitHeight(30);
        ivNext.setPreserveRatio(true);
        btnNext.setGraphic(ivNext);
        btnNext.setTooltip(new Tooltip("Une Ronde à la fois"));
        btnNext.setStyle("-fx-background-color: white;");
        btnNext.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnNext.setOnMouseClicked(e->{
            try {
                loop = true;
                simulation(true);
            }catch (InterruptedException z){
                AlertBox.alert("Erreur", "Une erreure s'est produite, veuillez fermer le programme.", 18);
            }
            resizeGridPane(((int) sliderRangees.getValue()), ((int) sliderColones.getValue()));
            buttonEnableDisable(false);

        });

        ImageView ivPlay = new ImageView(imgPlay);
        ivPlay.setFitWidth(30);
        ivPlay.setFitHeight(30);
        ivPlay.setPreserveRatio(true);
        btnPlay.setGraphic(ivPlay);
        btnPlay.setTooltip(new Tooltip("Jouer Automatiquement"));
        btnPlay.setStyle("-fx-background-color: white;");
        btnPlay.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnPlay.setOnMouseClicked(e -> {
            resizeGridPane(((int) sliderRangees.getValue()), ((int) sliderColones.getValue()));
            SimulationTask task = new SimulationTask();
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            loop = true;
            gpJeu.setOnMouseClicked(a-> {
                loop = false;
                buttonEnableDisable(false);
                thread.interrupt();
            });
            thread.start();
            buttonEnableDisable(true);
        });

        ImageView ivPause = new ImageView(imgPause);
        ivPause.setFitWidth(30);
        ivPause.setFitHeight(30);
        ivPause.setPreserveRatio(true);
        btnPause.setGraphic(ivPause);
        btnPause.setTooltip(new Tooltip("Pause"));
        btnPause.setStyle("-fx-background-color: white;");
        btnPause.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnPause.setOnMouseClicked(e -> {
            loop = false;
            buttonEnableDisable(false);
        });


        ImageView ivAide = new ImageView(imgAide);
        ivAide.setFitWidth(30);
        ivAide.setFitHeight(30);
        ivAide.setPreserveRatio(true);
        btnAide.setGraphic(ivAide);
        btnAide.setTooltip(new Tooltip("Aide"));
        btnAide.setStyle("-fx-background-color: white;");
        btnAide.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnAide.setOnMouseClicked(e-> FenetreAide.aide());


        spJeu.setOnMouseClicked(e->{
            if(checkModePinceau.isSelected() && !loop){
                for(int j = 0; j < RANGEES; j++){
                    for(int i = 0; i < COLONES; i++){
                        final int x = i;
                        final int y = j;
                        listeCases[i][j].Cellule.setOnMouseEntered(z->peinturerEtEffacerCasesAdjacentes(x,y,(int) sliderTaillePeintureEfface.getValue(),true));

                    }
                }
            }else if (checkModeEfface.isSelected() &&!loop){
                for(int j = 0; j < RANGEES; j++){
                    for(int i = 0; i < COLONES; i++){
                        final int x = i;
                        final int y = j;
                        listeCases[i][j].Cellule.setOnMouseEntered(z->peinturerEtEffacerCasesAdjacentes(x,y,(int) sliderTaillePeintureEfface.getValue(),false));

                    }
                }
            }
        });
        spJeu.setOnMouseReleased(e->{
            for(int j = 0; j < RANGEES; j++){
                for(int i = 0; i < COLONES; i++){
                    listeCases[i][j].Cellule.setOnMouseEntered(null);
                }
            }

        });



        HBox hbxBouttons = new HBox(10);
        hbxBouttons.getChildren().addAll(btnSave,btnLoad,btnPlay, btnNext, btnPause,btnClear,btnAide);
        hbxBouttons.setAlignment(Pos.CENTER);

        HBox hbxCentre = new HBox(20);
        hbxCentre.getChildren().addAll(scrollJeu, vbxDroite);

        VBox vbxTout = new VBox(10);
        vbxTout.getChildren().addAll(spTitre, hbxCentre, hbxBouttons);
        vbxTout.setPadding(new Insets(20, 20, 20, 20));
        vbxTout.setStyle("-fx-background-color: white;");

        sceneJeu = new Scene(vbxTout, LARGEUR_FENETRE, LONGEUR_FENETRE);

    }


    private static void modeSwitch(boolean pinceau){
        if (!checkModePinceau.isSelected() && !checkModeEfface.isSelected()){
            sliderTaillePeintureEfface.setDisable(true);
            return;
        }
        if(pinceau) checkModeEfface.setSelected(false);
        else checkModePinceau.setSelected(false);


        sliderTaillePeintureEfface.setDisable(false);


    }
    private static void peinturerEtEffacerCasesAdjacentes(int x, int y, int taille,boolean peinturer){

        int rayon =(taille-1)/2;

        for(int j = -rayon; j<=rayon; j++){
            for(int i = -rayon; i <=rayon; i++){
                final int xEntourant = x+i;
                final int yEntourant = y+j;

                if(xEntourant < 0 || yEntourant <0 || xEntourant >= COLONES || yEntourant >= RANGEES){
                    break;
                }else{
                    if(peinturer) listeCases[xEntourant][yEntourant].setCellulePresente(true);
                    else {
                        listeCases[xEntourant][yEntourant].resetCellulesMortes();
                        listeCases[xEntourant][yEntourant].setCellulePresente(false);
                    }
                }
            }
        }

    }

    private static void clear(){
        ronde = 0;
        initialisation(gpJeu,listeCases,RANGEES,COLONES);
        lblNombreDeCellules.setText("");
        lblRonde.setText("");
    }

    private static void celluleVide(int i, int j, Case[][] listeCases){
        final int x = i;
        final int y = j;
        Case tempCase = new Case();
        tempCase.setCellulePresente(false);
        tempCase.resetCellulesMortes();
        tempCase.Cellule.setFill(Color.WHITE);
        listeCases[i][j] = tempCase;

        listeCases[i][j].Cellule.setOnMouseClicked(e->click(x,y));
    }
    private static GridPane initialisation(GridPane tempGrid, Case[][] listeCases,int RANGEES, int COLONES){
        for(int j = 0; j < RANGEES; j++){
            for(int i = 0; i < COLONES; i++){
                celluleVide(i,j,listeCases);

                //Je créé un StackPane temporaire, je lui ajoute l'ImageView de la liste de Cases, ensuite je lui ajoute une bordure.
                StackPane tempPane = new StackPane();
                tempPane.getChildren().add(listeCases[i][j].Cellule);
                tempPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
                //J'ajoute ensuite ce StackPane se fait ajouté au gridPane temporaire.
                tempGrid.add(tempPane, i, j);
            }

        }
        return tempGrid;
    }

    static void resizeGridPane(int longeur, int largeur){
        Case[][] tempCase = new Case[largeur][longeur];

        GridPane newGrid = new GridPane();

        for (int j = 0; j < longeur; j++) {
            for (int i = 0; i < largeur; i++) {
                if (j >= (RANGEES) || i >= (COLONES)) {
                    celluleVide(i, j, tempCase);
                } else {
                    tempCase[i][j] = listeCases[i][j];
                }

                //Je créé un StackPane temporaire, je lui ajoute l'ImageView de la liste de Cases, ensuite je lui ajoute une bordure.
                StackPane tempPane = new StackPane();
                tempPane.getChildren().add(tempCase[i][j].Cellule);
                tempPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
                //J'ajoute ensuite ce StackPane se fait ajouté au gridPane temporaire.
                newGrid.add(tempPane, i, j);

            }
        }
        RANGEES = longeur;
        COLONES = largeur;

        listeCases = tempCase;

        gpJeu = newGrid;

        spJeu.getChildren().clear();
        spJeu.getChildren().add(gpJeu);
        spJeu.setPadding(new Insets(10, 10, 10, 10));
    }
    static void simulation(boolean uneFois) throws InterruptedException {

        int nombrePourVivre = (int)sliderNombrePourVivre.getValue();

        int[][] mouvements = new int[COLONES][RANGEES];

        for (int x = 1; x < Integer.MAX_VALUE; x++) {
            for (int j = 0; j < RANGEES; j++) {
                for (int i = 0; i < COLONES; i++) {

                    int nombreDeCellulesEntourantes;

                    if (Thread.interrupted()) {
                        return;
                    }
                    if(!loop)return;

                    if (i == 0 && j > 0 && j < (RANGEES-1)) {
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 1, 0, 1);
                    }else if (i > 0 && i < (COLONES-1) && j == 0) {
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, 0, 1, -1, 1);
                    }else if (i > 0 && i < (COLONES-1) && j == (RANGEES-1)) {
                    nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 0, -1, 1);
                    }else if (i == 0 && j == 0) {
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, 0, 1, 0, 1);
                    }else if (i == 0 && j == (RANGEES-1)) {
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 0, 0, 1);
                    }else if (i == (COLONES-1) && j == 0) {
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, 0, 1, -1, 0);
                    }else if (i == (COLONES-1) && j > 0 && j < (RANGEES-1)) {
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 1, -1, 0);
                    } else if(i > 0 && i < (COLONES-1) && j == (RANGEES-1)){
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 0, -1, 1);
                    }else if(i > 0 && i < (COLONES-1) && j > 0 && j < (RANGEES-1)){
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 1, -1, 1);
                    }else if(i == (COLONES-1) && j == (RANGEES-1)){
                        nombreDeCellulesEntourantes = cellulesEntourantes(i, j, -1, 0, -1, 0);
                    } else{
                        nombreDeCellulesEntourantes = 0;
                    }

                    if (nombreDeCellulesEntourantes < nombrePourVivre) {
                        mouvements[i][j] = 0;
                    } else if (nombreDeCellulesEntourantes == nombrePourVivre) {
                        if (!listeCases[i][j].getCellulePresente()) {
                            mouvements[i][j] = 0;
                        } else {
                            mouvements[i][j] = 1;
                        }
                    } else if (nombreDeCellulesEntourantes == (nombrePourVivre+1)) {
                        if (!listeCases[i][j].getCellulePresente() || listeCases[i][j].getCellulePresente()) {
                            mouvements[i][j] = 1;
                        }
                    } else if (nombreDeCellulesEntourantes >= (nombrePourVivre+2)) {
                        mouvements[i][j] = 0;

                    }
                }
            }

            int nombreDeCellules = 0;

            for (int j = 0; j < RANGEES; j++) {
                for (int i = 0; i < COLONES; i++) {
                    if (mouvements[i][j] == 1) {
                        nombreDeCellules++;
                        listeCases[i][j].setCellulePresente(true);
                    } else {
                        listeCases[i][j].setCellulePresente(false);
                    }
                }

            }
            ronde++;
            final int totalDeCellules = nombreDeCellules;


            Platform.runLater(() -> {
                lblRonde.setText("Nombre de Rondes: " + ronde);
                lblNombreDeCellules.setText("Nombre de Cellules: " + totalDeCellules);

                if (methodeStall(totalDeCellules,0)){
                    loop = false;
                    String message = "Le jeu est maintenant stagnant, après " + ronde + " rondes, ayant " + totalDeCellules + " cellules!";
                    buttonEnableDisable(false);
                    AlertBox.alert("Terminé", message, 20);
                }

            });
            if(uneFois)return;
            Thread.sleep((long)(200/vitesse));
        }
    }
    private static boolean methodeStall(int totalDeCellules, int i){

        if(i == 0 ){
            if(totalDeCellules == score[i])return methodeStall(totalDeCellules, (i + 1));
            else {
                score[i] = totalDeCellules;
                return false;
            }
        }

        if( i == 6){
            return true;
        }

        if(score[i-1] == score[i]) return methodeStall(totalDeCellules, (i + 1));
        else score[i] = totalDeCellules;



        return false;

    }

    private static void click(int i, int j){

        boolean mettre = checkModeEfface.isSelected();

        if(!mettre) {
            if(listeCases[i][j].getCellulePresente()) {
                listeCases[i][j].resetCellulesMortes();
                listeCases[i][j].setCellulePresente(false);
            }
            else listeCases[i][j].setCellulePresente(true);
        }
        else {
            listeCases[i][j].resetCellulesMortes();
            listeCases[i][j].setCellulePresente(false);
        }


    }

    private static int cellulesEntourantes(int x, int y, int minimumY, int maximumY, int minimumX, int maximumX){
        int total = 0;
        for(int j = minimumY; j<= maximumY; j++){
            for(int i = minimumX; i<= maximumX; i++){
                if( i!= 0 || j!=0){
                    if(listeCases[x+i][y+j].getCellulePresente())total++;
                }
            }
        }
        return total;
    }
    private static void buttonEnableDisable(boolean disable){
        sliderColones.setDisable(disable);
        sliderRangees.setDisable(disable);
        sliderNombrePourVivre.setDisable(disable);
        sliderTaillePeintureEfface.setDisable(disable);
        sliderVitesse.setDisable(disable);
        btnPlay.setDisable(disable);
        btnNext.setDisable(disable);
        btnPause.setDisable(!disable);
        btnClear.setDisable(disable);
        checkModePinceau.setDisable(disable);
        checkModeEfface.setDisable(disable);
        btnSave.setDisable(disable);
        btnLoad.setDisable(disable);

        if(disable){
            vbxReglages.getChildren().remove(vbxOptionsReglages);
            vbxOptionsReglages.setManaged(false);
            vbxModes.getChildren().remove(vbxOptionsMode);
            vbxOptionsMode.setManaged(false);
        }else{
            vbxReglages.getChildren().add(vbxOptionsReglages);
            vbxOptionsReglages.setManaged(true);
            vbxModes.getChildren().add(vbxOptionsMode);
            vbxOptionsMode.setManaged(true);
        }

    }
}
