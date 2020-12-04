package com.zazo.gameoflife;

import com.zazo.gameoflife.fenetreSuppelementaires.AlertBox;
import com.zazo.gameoflife.fenetreSuppelementaires.FenetreAide;
import com.zazo.gameoflife.formes.Carre;
import com.zazo.gameoflife.formes.Cercle;
import com.zazo.gameoflife.formes.Glider;
import com.zazo.gameoflife.formes.Gosper;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EcranDeJeu {
    //Ici j'initialise ma variable qui va contenir le nombre de rondes qui sont passées.
    private static int ronde = 0;
    //Ici j'intialise ma scene de jeu, sceneJeu.
    static Scene sceneJeu;

    //Ici j'initialise les variables contenant le nombre de colones et rangées de ma grille de jeu.
    public static int COLONES = 75;
    public static int RANGEES = 75;

    //Ici j'initialise ma liste de Cases, listeCases..
    public static Case[][] listeCases = new Case[COLONES][RANGEES];

    //Ici j'initialise ma liste d'integer, scoreParRonde, qui contient le score des sept dernières Rondes afin de déterminer si le jeu est stagnant.
    private static int [] scoreParRonde = new int[7];

    //Ici j'intialise mes labels contenant le nombre de rondes et le nombre de cellules présentes.
    private static Label lblRonde = new Label();
    private static Label lblNombreDeCellules = new Label();

    //Ici j'intialise le font qu'aurant les labels dans cette fenêtre.
    private static final Font font = new Font("Times New Roman", 18);

    //Ici j'initialise les variables contenant la longeur et la largeur de l'écran.
    private static final int LONGEUR_FENETRE = 950;
    private static final int LARGEUR_FENETRE = 1600;

    //Ici j'initialise mon VBox vbxTout qui contiendrait tout le contenu de ma fenêtre.
    static VBox vbxTout = new VBox(10);

    //Ici j'initialise le font de tous les sous-titres dans le programme
    private static final Font fontSousTitre = Font.font("Times New Roman", FontWeight.BOLD, 24);

    //Ici j'initialise toutes les images que j'utiliserait dans ce programme, se tronvant dans le fichier res.
    private static Image imgPlay = new Image("/play_arrow.png");
    private static Image imgNext = new Image("/skip_next.png");
    private static Image imgPause = new Image("/pause.png");
    private static Image imgClear = new Image("/close-box.png");
    private static Image imgSave = new Image("/save_black.png");
    private static Image imgLoad = new Image("/folder_open.png");
    private static Image imgAide = new Image("/help_circle.png");
    private static Image imgHome = new Image("/homeicon.png");
    private static Image imgCercle = new Image("/cercle.PNG");
    private static Image imgGlider = new Image ("/glider.PNG");
    private static Image imgCarre = new Image("/carre.PNG");
    private static Image imgGosper = new Image("/gosperglider.PNG");

    //Ici j'intitialise les bouttons qui sertont utilisés dans cet écran, comme le boutton de play et pause.
    private static Button btnPause = new Button();
    private static Button btnClear = new Button();
    private static Button btnSave = new Button();
    private static Button btnLoad = new Button();
    private static Button btnNext = new Button();
    private static Button btnPlay = new Button();
    private static Button btnAide = new Button();
    private static Button btnHome = new Button();

    //Ici j'initialise les checkBox pour mes deux modes, le mode Pinceau et le mode Efface.
    private static CheckBox checkModePinceau = new CheckBox("Mode Peinture");
    private static CheckBox checkModeEfface = new CheckBox("Mode Efface");

    //Ici j'initialise ma variable de vitesse, qui contrôllerait la vitesse de la simulation.
    private static double vitesse = 1;

    //Ici j'initialise ma variable loop qui ferait que la simulation cesse lorsque le boutton pause est pressé.
    static boolean loop = false;

    //Ici j'initialise mon Slider, sliderNombrePourVivre, qui serait utilisé pour changer les règles du jeu.
    private static Slider sliderNombrePourVivre = new Slider(0,4,2);

    //Ici j'initialise mon gridPane, gpJeu, qui serait la grille du jeu, mon stackPane, spJeu, qui contiendrait le gridPane gpJeu et le scrollPane scrollJeu qui contiendrait spJeu.
    private static GridPane gpJeu;
    private static StackPane spJeu = new StackPane();
    private static ScrollPane scrollJeu = new ScrollPane();

    //Ici j'intialise mes autres sliders correspondant à mes autres reglages et modes, comme le sliderColones, sliderRangées, sliderVitesse et sliderEpaisseurPinceauEfface. Plus de details sur chacun d'entre eux serait abordé dans le code ci-dessous lorsqu'ils seront proprement initialisés.
    static Slider sliderColones = new Slider(0, 150, 75);
    static Slider sliderRangees = new Slider(0, 150, 75);
    private static Slider sliderVitesse = new Slider(0.5, 2, 1);
    private static Slider sliderEpaisseurPinceauEfface = new Slider(1,7,1);

    //Ici j'initialise mes Labels qui diraient aux utilisateurs ce que chaque slider modifie.
    private static Label lblNombrePourVivre = new Label("Nombre de cellules adjacentes pour vivre:");
    private static Label lblVitesse = new Label("Vitesse du simulateur:");
    private static Label lblRangees = new Label("Nombre de rangées:");
    private static Label lblColones = new Label("Nombre de colones:");

    //Ici j'intialise mes conteneurs VBox pour chaque partie de la fenêtre. du reglage aux formes prédféfinies. Plus de details sur chacun d'entre eux serait abordé dans le code ci-dessous lorsqu'ils seront proprement initialisés.
    private static VBox vbxOptionsReglages = new VBox(10);
    private static VBox vbxReglages = new VBox(10);
    private static VBox vbxOptionsPinceauEfface = new VBox(10);
    private static VBox vbxPinceauEfface = new VBox(10);
    private static VBox vbxRadioButtonFormes = new VBox(10);
    private static VBox vbxFormes = new VBox(10);

    //Ici j'intialise mon checkBox checkModeFormesPredefinies qui ferait en sorte que l'utilisateur puisse sélectionner des formes prédéfinies et les mettre dans le grillage de jeu.
    private static  CheckBox checkModeFormesPredefinies = new CheckBox("Mode formes prédéfinies");

    //Ici j'initialise les radioButtons qui seront selectionnés lorsque l'utilisateur a envi de mettre une forme prédéfinie dans le grillage de jeu.
    private static RadioButton rbGlider = new RadioButton("Glider");
    private static RadioButton rbCercle = new RadioButton("Cercle");
    private static RadioButton rbCarre = new RadioButton("Carré");
    private static RadioButton rbGosperGlider = new RadioButton("Gosper Glider");


    //Ici j'intialise les variables booleans qui signaleront lorsqu'un des radioButtons se fasse sélectionnés.
    private static boolean gliderSelected = false;
    private static boolean cercleSelected = false;
    private static boolean carreSelected = false;
    private static boolean gosperGliderSelected = false;

    /**
     * Cette méthode sert à la création de la scene de jeu.
     */
    static void creationScene(){

        //Je commence en intialisant le label qui agirait de titre de l'écran.
        Label lblTitre = new Label("Zazo's Game of Life");
        lblTitre.setFont(Font.font("Papyrus",FontWeight.BOLD,36));
        StackPane spTitre = new StackPane();
        spTitre.getChildren().add(lblTitre);

        //Ensuite j'initialise le gridPane qui contiendrait les cellules mortes et vivantes, en initialisant en premier un gridPane temporaire et ensuite faisant que le gridPane de jue soit égal à ce gridPane.
        GridPane tempGrid = new GridPane();
        tempGrid.setHgap(0.1);
        tempGrid.setVgap(0.1);
        tempGrid.setAlignment(Pos.CENTER);
        tempGrid.setMinWidth(900);
        tempGrid.setMinHeight(900);
        tempGrid = initialisation(tempGrid, listeCases, RANGEES, COLONES);

        //Maintenant j'initialise le vrai gridPane, gpJeu.
        gpJeu = tempGrid;
        gpJeu.setStyle("-fx-background-color: white");

        //J'initialise le stackPane qui contiendrait gpJeu.
        spJeu.getChildren().add(gpJeu);
        spJeu.setPadding(new Insets(10, 10, 10, 10));
        spJeu.setStyle("-fx-background-color: white");

        //J'initialise le scrollPane qui contiendrait spJeu.
        scrollJeu.setContent(spJeu);
        scrollJeu.setPrefSize(950, 900);

        //J'initialise le label qui agirait de sous-titre pour les modes Pinceau et Efface.
        Label lblPinceauEfface = new Label("Pinceau et Efface:");
        lblPinceauEfface.setFont(fontSousTitre);

        //Ici j'initialise une ligne séparatrice (esthétique).
        Line lignePinceauEfface = new Line(0,0,300,0);
        lignePinceauEfface.setStrokeWidth(2);

        //Ici j'initialise le label qui signale à l'utilisateur ou sélectionner la taille du pinceau ou efface.
        Label lblEpaisseurPinceauEfface = new Label("Épaisseure du pinceau ou efface:");
        lblEpaisseurPinceauEfface.setFont(font);

        //Ici j'initialie le sliderEpaisseurPinceauEfface, qui donnerait l'épaisseur du pinceau ou l'efface lorsque l'utilisateur sélectionne un de ces deux modes.
        sliderEpaisseurPinceauEfface.setShowTickLabels(true);
        sliderEpaisseurPinceauEfface.setShowTickMarks(true);
        sliderEpaisseurPinceauEfface.setMajorTickUnit(2);
        sliderEpaisseurPinceauEfface.setMinorTickCount(0);
        sliderEpaisseurPinceauEfface.setBlockIncrement(2);
        sliderEpaisseurPinceauEfface.setSnapToTicks(true);
        sliderEpaisseurPinceauEfface.setDisable(true);

        //Ici j'initialise le checkModeEfface, le checkbox à sélectionner lorsqu'on a envi d'utiliser l'efface.
        checkModeEfface.setFont(font);
        checkModeEfface.setOnMouseClicked(e-> modeSwitch(false));

        //Ici j'initialise le checkModePinceau, le checkbox à sélectionner lorsqu'on a envi d'utiliser le pinceau.
        checkModePinceau.setFont(font);
        checkModePinceau.setOnMouseClicked(e-> modeSwitch(true));

        //Ici j'initialise le HBox qui contiendrait le checkBox de pinceau et efface.
        HBox hbxcheckBoxPinceauEfface = new HBox(10);
        hbxcheckBoxPinceauEfface.getChildren().addAll(checkModePinceau,checkModeEfface);

        //Ici j'inititialise le VBox qui contiendrait tout ce qui serait « modifiable » dans le VBox PinceauEfface, qui contiendrait tout ce qui a avoir avec les modes Pinceau et Efface.
        vbxOptionsPinceauEfface.getChildren().addAll(hbxcheckBoxPinceauEfface, lblEpaisseurPinceauEfface, sliderEpaisseurPinceauEfface);

        //Ici j'initialise le VBox vbxPinceauEfface, qui contiendrait tout ce qui a avoir avec le mode Pinceau et Efface.
        vbxPinceauEfface.getChildren().addAll(lblPinceauEfface,lignePinceauEfface, vbxOptionsPinceauEfface);
        vbxPinceauEfface.setPadding(new Insets(10,10,10,10));
        vbxPinceauEfface.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));

        //Ici j'initialise le label qui agirait de sous-titre pour la catégorie de réglages.
        Label lblReglages = new Label("Réglages:");
        lblReglages.setFont(fontSousTitre);

        //Ici j'initialise une ligne séparatrice (esthétique).
        Line ligneReglages = new Line(0,0,300,0);
        ligneReglages.setStrokeWidth(2);

        //Ici j'initialise les fonts pour les labels définissant les différents réglages.
        lblNombrePourVivre.setFont(font);
        lblColones.setFont(font);
        lblRangees.setFont(font);
        lblVitesse.setFont(font);

        //Ici j'initialise le slider qui définirait le nombre de cellules adjacentes requises pour qu'une cellule donnée puisse vivre.
        sliderNombrePourVivre.setShowTickLabels(true);
        sliderNombrePourVivre.setShowTickMarks(true);
        sliderNombrePourVivre.setMajorTickUnit(1);
        sliderNombrePourVivre.setMinorTickCount(0);
        sliderNombrePourVivre.setBlockIncrement(1);
        sliderNombrePourVivre.setSnapToTicks(true);

        //Ici j'initialise le slider qui déterminerait le nombre de colones dans le grillage.
        sliderColones.setShowTickLabels(true);
        sliderColones.setShowTickMarks(true);
        sliderColones.setMajorTickUnit(25);
        sliderColones.setMinorTickCount(0);
        sliderColones.setBlockIncrement(25);
        sliderColones.setSnapToTicks(true);
        sliderColones.valueProperty().addListener(e->{if(sliderColones.getValue()%25 == 0) resizeGridPane(((int) sliderRangees.getValue()), ((int) sliderColones.getValue()));});

        //Ici j'initialise le slider qui déterminerait le nombre de rangées dans le grillage.
        sliderRangees.setShowTickLabels(true);
        sliderRangees.setShowTickMarks(true);
        sliderRangees.setMajorTickUnit(25);
        sliderRangees.setMinorTickCount(0);
        sliderRangees.setBlockIncrement(25);
        sliderRangees.setSnapToTicks(true);
        sliderRangees.valueProperty().addListener(e->{if(sliderRangees.getValue()%25 == 0) resizeGridPane(((int) sliderRangees.getValue()), ((int) sliderColones.getValue()));});

        //Ici j'initialise le slider qui déterminerait la vitesse de la simulation.
        sliderVitesse.setShowTickLabels(true);
        sliderVitesse.setShowTickMarks(true);
        sliderVitesse.setMajorTickUnit(0.5);
        sliderVitesse.setMinorTickCount(1);
        sliderVitesse.setBlockIncrement(0.5);
        sliderVitesse.setSnapToTicks(true);
        sliderVitesse.valueProperty().addListener(e->vitesse = sliderVitesse.getValue());

        //Ici j'intialise le VBox qui contiendrait tout ce qui serait « modifiable » dans le VBoxReglages.
        vbxOptionsReglages.getChildren().addAll(lblNombrePourVivre, sliderNombrePourVivre , lblColones, sliderColones, lblRangees, sliderRangees, lblVitesse,sliderVitesse);

        //Ici j'initialise le VBox qui contiendrait tout ce qui sertait dans la catégorie de reglages.
        vbxReglages.getChildren().addAll(lblReglages, ligneReglages, vbxOptionsReglages);
        vbxReglages.setPadding(new Insets(10,10,10,10));
        vbxReglages.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));

        //Ici j'initialise mon Label lbInformationsPartie, qui agirait de sous titre pour la section d'information de la partie.
        Label lblInformationsPartie = new Label("Informations sur la partie:");
        lblInformationsPartie.setFont(fontSousTitre);

        //Ici j'initialise une ligne séparatrice (esthétique).
        Line ligneInformationPartie = new Line(0,0,300,0);
        ligneInformationPartie.setStrokeWidth(2);

        //Ici j'initialise le font des label qui afficheront le nombre de rondes et cellules.
        lblRonde.setFont(font);
        lblNombreDeCellules.setFont(font);

        //Ici j'initialise mon VBox vbxInformationsPartie qui contient tout ce qui a avoir avec l'information de la partie.
        VBox vbxInformationsPartie = new VBox(10);
        vbxInformationsPartie.getChildren().addAll(lblInformationsPartie,ligneInformationPartie,lblRonde,lblNombreDeCellules);
        vbxInformationsPartie.setPadding(new Insets(10,10,10,10));
        vbxInformationsPartie.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));

        //Ici j'initialise mon VBox vbxCentre qui contient tout ce qui serait au « centre » de la fenêtre.
        VBox vbxCentre = new VBox(10);
        vbxCentre.getChildren().addAll(vbxInformationsPartie, vbxPinceauEfface,vbxReglages);

        //Ici j'initialise mon label qui agirait de sous-titre pour la section de formes prédéfinies.
        Label lblFormesPredefinies = new Label("Formes Prédéfinies:");
        lblFormesPredefinies.setFont(fontSousTitre);

        //Ici j'initialise une ligne séparatrice (esthétique).
        Line ligneFormesPredefinies = new Line(0,0,300,0);
        ligneFormesPredefinies.setStrokeWidth(2);

        //Ici j'initialise mon ToggleGroup (je joint les radiobuttons ensemble).
        ToggleGroup group = new ToggleGroup();

        //Ici j'initialise mon RadioButton pour sélectionner des Glider, rbGlider, en initialisant son image.
        ImageView ivGlider = new ImageView(imgGlider);
        ivGlider.setFitHeight(80);
        ivGlider.setFitWidth(80);
        ivGlider.setPreserveRatio(true);
        rbGlider.setGraphic(ivGlider);
        rbGlider.setFont(font);
        rbGlider.setToggleGroup(group);
        rbGlider.setOnMouseClicked(e->{
            aucuneForme();
            gliderSelected = rbGlider.isSelected();
        });

        //Ici j'initialise mon RadioButton pour sélectionner des Cercles, rbCercles, en initialisant son image.
        ImageView ivCercle = new ImageView(imgCercle);
        ivCercle.setFitHeight(80);
        ivCercle.setFitWidth(80);
        ivCercle.setPreserveRatio(true);
        rbCercle.setGraphic(ivCercle);
        rbCercle.setFont(font);
        rbCercle.setToggleGroup(group);
        rbCercle.setOnMouseClicked(e->{
            cercleSelected = rbCercle.isSelected();
        });

        //Ici j'initialise mon RadioButton pour sélectionner des Carrés, rbCarre, en initialisant son image.
        ImageView ivCarre = new ImageView(imgCarre);
        ivCarre.setFitHeight(80);
        ivCarre.setFitWidth(80);
        ivCarre.setPreserveRatio(true);
        rbCarre.setGraphic(ivCarre);
        rbCarre.setFont(font);
        rbCarre.setToggleGroup(group);
        rbCarre.setOnMouseClicked(e->{
            aucuneForme();
            carreSelected = rbCarre.isSelected();
        });

        //Ici j'initialise mon RadioButton pour sélectionner des Gosper Glider, rbGosperGlider, en initialisant son image.
        ImageView ivGosperGlider = new ImageView(imgGosper);
        ivGosperGlider.setFitHeight(80);
        ivGosperGlider.setFitWidth(80);
        ivGosperGlider.setPreserveRatio(true);
        rbGosperGlider.setGraphic(ivGosperGlider);
        rbGosperGlider.setFont(font);
        rbGosperGlider.setToggleGroup(group);
        rbGosperGlider.setOnMouseClicked(e->{
            aucuneForme();
            gosperGliderSelected = rbGosperGlider.isSelected();
        });

        //Ici j'initialise mon CheckBox qu'on coche lorsqu'on veut mettre des formes Prédéfinies.
        checkModeFormesPredefinies.setFont(font);
        checkModeFormesPredefinies.setOnMouseClicked(e->{
            if(checkModeFormesPredefinies.isSelected()){
                //Lorsque le checkBox est sélectionné, j'active les radioButton et je les montre, tout en enlevant l'option de de séelctionner la taille du pinceau ou efface du mode pinceau et efface.
                vbxRadioButtonFormes.setManaged(true);
                checkModePinceau.setDisable(true);
                checkModePinceau.setSelected(false);
                checkModeEfface.setDisable(true);
                checkModeEfface.setSelected(false);
                sliderEpaisseurPinceauEfface.setDisable(true);
                vbxFormes.getChildren().add(vbxRadioButtonFormes);

            }else{
                //Lorsque le checkBox est déselctionné, je désactive les radioButton et je les cache.
                vbxRadioButtonFormes.setManaged(false);
                checkModePinceau.setDisable(false);
                checkModeEfface.setDisable(false);
                aucunRadioButton();
                aucuneForme();
                vbxFormes.getChildren().remove(vbxRadioButtonFormes);
            }

        });

        //Ici j'initialise le VBox qui contiendrait tout les radioButtons des formes.
        vbxRadioButtonFormes.getChildren().addAll(rbGlider,rbCercle,rbCarre, rbGosperGlider);

        //Ici j'initialise le VBox qui contiendrait tout ce qui à avoir avec les formes prédéfinies.
        vbxFormes.getChildren().addAll(lblFormesPredefinies,ligneFormesPredefinies,checkModeFormesPredefinies);
        vbxFormes.setPadding(new Insets(10,10,10,10));
        vbxFormes.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderStroke.MEDIUM)));

        //Ici j'initialise le boutton Home, qui permettrait à l'utilisateur de repartir à l'acceuil principal.
        ImageView ivHome = new ImageView(imgHome);
        ivHome.setFitHeight(30);
        ivHome.setFitHeight(30);
        ivHome.setPreserveRatio(true);
        btnHome.setGraphic(ivHome);
        btnHome.setTooltip(new Tooltip("Retourner au menu principal"));
        btnHome.setStyle("-fx-background-color: white;");
        btnHome.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnHome.setOnMouseClicked(e->{
            Main.fadeOut(vbxTout);
            Main.homeScene(Main.stage);
            Main.fadeIn(Main.vbxTout);
        });

        //Ici j'initialise mon VBox vbxDroite qui contiendrait VBox vbxFormes et le boutton btnHome.
        VBox vbxDroite = new VBox(10);
        vbxDroite.getChildren().addAll(vbxFormes,btnHome);

        //Ici j'initialise mon HBox, hbxCentre, qui contiendtrait tout ce qui se trouverait au centre de la scène.
        HBox hbxCentre = new HBox(10);
        hbxCentre.getChildren().addAll(scrollJeu,vbxCentre,vbxDroite);

        //Ici j'initialise mon boutton pour effacer le contenu de la surface de jeu, btnClear.
        ImageView ivClear = new ImageView(imgClear);
        ivClear.setFitHeight(30);
        ivClear.setFitHeight(30);
        ivClear.setPreserveRatio(true);
        btnClear.setGraphic(ivClear);
        btnClear.setTooltip(new Tooltip("Effacer et Recommencer"));
        btnClear.setStyle("-fx-background-color: white;");
        btnClear.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnClear.setOnMouseClicked(e->clear());

        //Ici j'initialise mon boutton pour sauveguarder la surface du jeu dans un fichier externe, btnSave.
        ImageView ivSave = new ImageView(imgSave);
        ivSave.setFitHeight(30);
        ivSave.setFitHeight(30);
        ivSave.setPreserveRatio(true);
        btnSave.setGraphic(ivSave);
        btnSave.setTooltip(new Tooltip("Saveguarder la Partie"));
        btnSave.setStyle("-fx-background-color: white;");
        btnSave.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,(new CornerRadii(5)),BorderWidths.DEFAULT)));
        btnSave.setOnMouseClicked(e-> SaveLoad.save());

        //Ici j'initialise mon boutton pour « load » ce qui est sauveguardé dans la surface de jeu à partir d'un fichier externe, btnLoad.
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

        //Ici j'initialise mon boutton pour jouer une ronde, btnNext.
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

        //Ici j'initialise mon boutton pour démarrer la simulation, btnPlay.
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
            thread.start();
            buttonEnableDisable(true);
            cacherReglages(true);

            gpJeu.setOnMouseClicked(a-> {
                buttonEnableDisable(false);
                if(loop)cacherReglages(false);
                loop = false;
                thread.interrupt();

            });

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
            cacherReglages(false);
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

        spJeu.setOnMouseEntered(e->{
                for(int j = 0; j < RANGEES; j++){
                    for(int i = 0; i < COLONES; i++){
                        final int x = i;
                        final int y = j;
                        if(checkModeFormesPredefinies.isSelected() && !loop) {
                            if (gliderSelected) listeCases[i][j].Cellule.setOnMouseClicked(z -> Glider.drawGlider(x, y));
                            else if (cercleSelected) listeCases[i][j].Cellule.setOnMouseClicked(z -> Cercle.drawCercle(x, y));
                            else if(carreSelected) listeCases[i][j].Cellule.setOnMouseClicked(z -> Carre.drawCarre(x, y));
                            else if(gosperGliderSelected) listeCases[i][j].Cellule.setOnMouseClicked(z -> Gosper.drawGosper(x, y));
                        }
                        else if(checkModePinceau.isSelected() && !loop){
                            listeCases[i][j].Cellule.setOnMouseEntered(z->peinturerEtEffacerCasesAdjacentes(x,y,(int) sliderEpaisseurPinceauEfface.getValue(),true));
                            listeCases[i][j].Cellule.setOnMouseClicked(z->click(x,y));
                        }
                        else if(checkModeEfface.isSelected() && !loop){
                            listeCases[i][j].Cellule.setOnMouseEntered(z->peinturerEtEffacerCasesAdjacentes(x,y,(int) sliderEpaisseurPinceauEfface.getValue(),false));
                            listeCases[i][j].Cellule.setOnMouseClicked(z->click(x,y));
                        }
                        else listeCases[i][j].Cellule.setOnMouseClicked(z->click(x,y));
                    }
                }
        });


        spJeu.setOnMouseExited(e->{
            for(int j = 0; j < RANGEES; j++){
                for(int i = 0; i < COLONES; i++){
                    listeCases[i][j].Cellule.setOnMouseEntered(null);
                }
            }

        });



        HBox hbxBouttons = new HBox(10);
        hbxBouttons.getChildren().addAll(btnSave,btnLoad,btnPlay, btnNext, btnPause,btnClear,btnAide);
        hbxBouttons.setAlignment(Pos.CENTER);


        vbxTout.getChildren().addAll(spTitre, hbxCentre, hbxBouttons);
        vbxTout.setPadding(new Insets(20, 20, 20, 20));
        vbxTout.setStyle("-fx-background-color: white;");
        vbxTout.setOpacity(0.0);

        sceneJeu = new Scene(vbxTout, LARGEUR_FENETRE, LONGEUR_FENETRE);

    }


    private static void modeSwitch(boolean pinceau){
        if (!checkModePinceau.isSelected() && !checkModeEfface.isSelected()){
            sliderEpaisseurPinceauEfface.setDisable(true);
            return;
        }
        if(pinceau) {
            checkModeEfface.setSelected(false);
            checkModeFormesPredefinies.setSelected(false);
            vbxFormes.getChildren().remove(vbxRadioButtonFormes);
        }
        else {
            checkModePinceau.setSelected(false);
            vbxFormes.getChildren().remove(vbxRadioButtonFormes);
        }


        sliderEpaisseurPinceauEfface.setDisable(false);


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
        listeCases[i][j].Cellule.setOnMouseClicked(z->click(x,y));

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
                    AlertBox.alert("Terminé", message, 20);
                    buttonEnableDisable(false);
                    cacherReglages(false);
                }

            });
            if(uneFois)return;
            Thread.sleep((long)(200/vitesse));
        }
    }
    private static boolean methodeStall(int totalDeCellules, int i){

        if(i == 0 ){
            if(totalDeCellules == scoreParRonde[i])return methodeStall(totalDeCellules, (i + 1));
            else {
                scoreParRonde[i] = totalDeCellules;
                return false;
            }
        }

        if( i == 6){
            for(int j = 0; j < 7; j ++) {
                scoreParRonde[j] = 0;
            }
            return true;
        }

        if(scoreParRonde[i-1] == scoreParRonde[i]) return methodeStall(totalDeCellules, (i + 1));
        else scoreParRonde[i] = totalDeCellules;



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
    private static void cacherReglages(boolean cacher){

        if(cacher){
            vbxReglages.getChildren().remove(vbxOptionsReglages);
            vbxOptionsReglages.setManaged(false);
            vbxPinceauEfface.getChildren().remove(vbxOptionsPinceauEfface);
            vbxOptionsPinceauEfface.setManaged(false);
            vbxFormes.getChildren().removeAll(vbxRadioButtonFormes,checkModeFormesPredefinies);
            vbxRadioButtonFormes.setManaged(false);
            checkModeFormesPredefinies.setManaged(false);
            checkModeFormesPredefinies.setSelected(false);

        }else{
            vbxReglages.getChildren().add(vbxOptionsReglages);
            vbxOptionsReglages.setManaged(true);
            vbxPinceauEfface.getChildren().add(vbxOptionsPinceauEfface);
            vbxOptionsPinceauEfface.setManaged(true);
            vbxFormes.getChildren().addAll(checkModeFormesPredefinies);
            vbxRadioButtonFormes.setManaged(true);
            checkModeFormesPredefinies.setManaged(true);
            }
    }

    private static void aucuneForme(){
        gliderSelected = false;
        cercleSelected = false;
        carreSelected = false;
        gosperGliderSelected = false;
    }
    private static void aucunRadioButton(){
        rbGlider.setSelected(false);
        rbGosperGlider.setSelected(false);
        rbCercle.setSelected(false);
        rbCarre.setSelected(false);
    }



    private static void buttonEnableDisable(boolean disable){
        sliderColones.setDisable(disable);
        sliderRangees.setDisable(disable);
        sliderNombrePourVivre.setDisable(disable);
        sliderEpaisseurPinceauEfface.setDisable(disable);
        sliderVitesse.setDisable(disable);
        btnPlay.setDisable(disable);
        btnNext.setDisable(disable);
        btnPause.setDisable(!disable);
        btnClear.setDisable(disable);
        checkModePinceau.setDisable(disable);
        checkModeEfface.setDisable(disable);
        btnSave.setDisable(disable);
        btnLoad.setDisable(disable);
        btnHome.setDisable(disable);

        if(!disable)lblNombreDeCellules.setText("");




    }
}
