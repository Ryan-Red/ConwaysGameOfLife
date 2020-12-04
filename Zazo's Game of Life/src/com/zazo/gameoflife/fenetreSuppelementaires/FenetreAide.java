package com.zazo.gameoflife.fenetreSuppelementaires;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FenetreAide {

    private static final int LONGEUR_FENETRE = 700;
    private static final int LARGEUR_FENETRE = 650;

    private static final Font fontSousTitre = Font.font("Times New Roman", FontWeight.BOLD, 24);
    private static final Font font = new Font("Times New Roman", 22);


    public static void aide(){

        Stage stageAide = new Stage();
        stageAide.setTitle("Aide");

        Label lblTitre = new Label("Aide et Information pour Zazo's Game of Life");
        lblTitre.setFont(Font.font("Times New Roman", FontWeight.BOLD,30));

        StackPane spTitre = new StackPane();
        spTitre.getChildren().add(lblTitre);
        spTitre.setPadding(new Insets(10,10,10,10));


        Label lblReglementsGeneraux = new Label("Les règlements généraux de Zazo's Game of Life:");
        lblReglementsGeneraux.setFont(fontSousTitre);

        Label lblInformationReglements = new Label("Les règlements généraux de Zazo's Game of Life sont très similaires à celle de Conway's Game of Life. Les règlements sont: \n1) Une cellule meurt lorsqu'il y a moins de cellules adjacentes que le nombre requis. \n2) Une cellule vit lorsqu'il y a autant ou un de plus que le nombre de cellules adjacentes requises pour vivre. \n3) Une cellule se fait réssuscitér lorsqu'elle est morte et il y a une cellule de plus que le nombre de cellules adjacentes requise pour vivre. \n4) Une cellule meurt lorsqu'il ya 2 cellules adjacentes de plus que le nomrbe de cellules requise pour vivre.");
        labelSet(lblInformationReglements);

        VBox vbxReglements = new VBox(10);
        vbxReglements.getChildren().addAll(lblReglementsGeneraux,lblInformationReglements);
        vbxReglements.setAlignment(Pos.CENTER);


        Label lblCellulesAdjacentesTitre = new Label("Le nombre de Cellules Adjacentes pour vivre:");
        lblCellulesAdjacentesTitre.setFont(fontSousTitre);

        Label lblInformationCelluleAdjacente = new Label("Le nombre de cellules adjacentes pour vivre défini le nombre de cellules adjacentes requises pour que la cellule vive. De plus, la valeur entrée définie aussi le nombre de cellules adjcatentes requises pour que la cellule meurt de surpopulation, meurt de sous population et pour qu'une case vide se fait « réssusciter »");
        labelSet(lblInformationCelluleAdjacente);

        VBox vbxCelluleAdjacentes = new VBox(10);
        vbxCelluleAdjacentes.getChildren().addAll(lblCellulesAdjacentesTitre,lblInformationCelluleAdjacente);
        vbxCelluleAdjacentes.setAlignment(Pos.CENTER);


        Label lblRangeeColone = new Label("Le nombre de rangées et de colonnes:");
        lblRangeeColone.setFont(fontSousTitre);

        Label lblInformationRangeeColone = new Label("Le nombre de rangées et de colones définit le nombre de rangées et de colonnes dans la grille du jeu.");
        labelSet(lblInformationRangeeColone);

        VBox vbxRangeeColone = new VBox(10);
        vbxRangeeColone.getChildren().addAll(lblRangeeColone,lblInformationRangeeColone);
        vbxRangeeColone.setAlignment(Pos.CENTER);


        Label lblModePeinture = new Label("Le mode peinture:");
        lblModePeinture.setFont(fontSousTitre);

        Label lblInformationModePeinture = new Label("Le Mode Peinture est un mode qui créé des cellules sous la souris de l'ordinateur et lorsqu'on clique la souris, ceci s'interrompte.");
        labelSet(lblInformationModePeinture);

        VBox vbxModePeinture = new VBox(10);
        vbxModePeinture.getChildren().addAll(lblModePeinture,lblInformationModePeinture);
        vbxModePeinture.setAlignment(Pos.CENTER);


        Label lblModeEfface = new Label("Le mode efface:");
        lblModeEfface.setFont(fontSousTitre);

        Label lblInformationModeEfface = new Label("Le Mode efface est un mode qui efface toute cellule trouvée sous la souris. Lorsqu'on clique la souris, ceci s'interrompte.");
        labelSet(lblInformationModeEfface);

        VBox vbxModeEfface = new VBox(10);
        vbxModeEfface.getChildren().addAll(lblModeEfface,lblInformationModeEfface);
        vbxModeEfface.setAlignment(Pos.CENTER);


        Label lblSaveLoad = new Label("Save et Load:");
        lblSaveLoad.setFont(fontSousTitre);

        Label lblInformationSaveLoad = new Label("Les bouttons Save et Load ont des fonctions très spéciales; elles permettent de sauveguarder la partie dans la mémoire du jeu, et d'ouvrir la partie sauveguardée, même après avoir quitté le jeu!");
        labelSet(lblInformationSaveLoad);

        VBox vbxSaveLoad = new VBox(10);
        vbxSaveLoad.getChildren().addAll(lblSaveLoad, lblInformationSaveLoad);
        vbxSaveLoad.setAlignment(Pos.CENTER);


        Label lblFormes = new Label("Formes Prédéfinies:");
        lblFormes.setFont(fontSousTitre);

        Label lblInformationFormes = new Label("Le mode de Formes prédéfinies est un mode qui sert de mettre des formes prédéfinies dans le jeu, comme un cercle, à la posisiton sélectionnée par l'utilisateur.");
        labelSet(lblInformationFormes);

        VBox vbxFormes = new VBox(10);
        vbxFormes.getChildren().addAll(lblFormes, lblInformationFormes);
        vbxFormes.setAlignment(Pos.CENTER);


        Label lblHome = new Label("Boutton Home:");
        lblHome.setFont(fontSousTitre);

        Label lblInformationHome = new Label("Le boutton Home sert à repartir vers l'écran principal du jeu, en guardant l'écran de jeu intacte.");
        labelSet(lblInformationHome);

        VBox vbxHome = new VBox(10);
        vbxHome.getChildren().addAll(lblHome,lblInformationHome);
        vbxHome.setAlignment(Pos.CENTER);


        Label lblPlay = new Label("Boutton Play:");
        lblPlay.setFont(fontSousTitre);

        Label lblInformationPlay = new Label("Le boutton Play sert à commencer le simulateur et de jouer de façon automatique.");
        labelSet(lblInformationPlay);

        VBox vbxPlay = new VBox(10);
        vbxPlay.getChildren().addAll(lblPlay,lblInformationPlay);
        vbxPlay.setAlignment(Pos.CENTER);


        Label lblPause = new Label("Boutton Pause:");
        lblPause.setFont(fontSousTitre);

        Label lblInformationPause = new Label("Le boutton Pause sert à arr[eter le simulateur lorsqu'il est en marche.");
        labelSet(lblInformationPause);

        VBox vbxPause = new VBox(10);
        vbxPause.getChildren().addAll(lblPause,lblInformationPause);
        vbxPause.setAlignment(Pos.CENTER);


        Label lblStep = new Label("Boutton Step:");
        lblStep.setFont(fontSousTitre);

        Label lblInformationStep = new Label("Le boutton Step sert à jouer le simulateur une ronde à la fois.");
        labelSet(lblInformationStep);

        VBox vbxStep = new VBox(10);
        vbxStep.getChildren().addAll(lblStep,lblInformationStep);
        vbxStep.setAlignment(Pos.CENTER);


        Label lblClear = new Label("Boutton Clear:");
        lblClear.setFont(fontSousTitre);

        Label lblInformationClear = new Label("Le boutton Clear sert à effacer tout le contenu du grillage de jeu.");
        labelSet(lblInformationClear);

        VBox vbxClear = new VBox(10);
        vbxClear.getChildren().addAll(lblClear,lblInformationClear);
        vbxClear.setAlignment(Pos.CENTER);


        Label lblRonde = new Label("Le nombre de Rondes:");
        lblRonde.setFont(fontSousTitre);

        Label lblInformationRonde = new Label("Le nombre de rondes est le nombre de rondes qui sont passées depuis le début de la simulation.");
        labelSet(lblInformationRonde);

        VBox vbxRonde = new VBox(10);
        vbxRonde.getChildren().addAll(lblRonde,lblInformationRonde);
        vbxRonde.setAlignment(Pos.CENTER);


        Label lblCellules = new Label("Le nombre de Cellules:");
        lblCellules.setFont(fontSousTitre);

        Label lblInformationCellules = new Label("Le nombre de cellules s'agit du nombre de cellules vivantes présentes en ce moment sur le grillage.");
        labelSet(lblInformationCellules);

        VBox vbxCellules = new VBox(10);
        vbxCellules.getChildren().addAll(lblCellules,lblInformationCellules);
        vbxCellules.setAlignment(Pos.CENTER);


        Label lblVitesse = new Label("La vitesse:");
        lblVitesse.setFont(fontSousTitre);

        Label lblInformationVitesse = new Label("La vitesse définit le multiplicateur de vitesse du jeu.");
        labelSet(lblInformationVitesse);

        VBox vbxVitesse = new VBox(10);
        vbxVitesse.getChildren().addAll(lblVitesse,lblInformationVitesse);
        vbxVitesse.setAlignment(Pos.CENTER);


        VBox vbxRenseignements = new VBox(10);
        vbxRenseignements.getChildren().addAll(spTitre, vbxReglements, vbxCelluleAdjacentes,vbxRangeeColone,vbxModePeinture,vbxModeEfface,vbxSaveLoad, vbxFormes,vbxHome, vbxPlay,vbxClear,vbxStep,vbxRonde,vbxCellules, vbxVitesse);
        vbxRenseignements.setAlignment(Pos.CENTER);
        vbxRenseignements.setStyle("-fx-background-color: white");


        ScrollPane scrollTout = new ScrollPane(vbxRenseignements);
        scrollTout.setPadding(new Insets(10,10,10,10));
        scrollTout.setStyle("-fx-background-color: white");

        //Ici je dis au Stage(une fenêtre) (boiteAlerte), d'être de la priorité la plus haute en ce qui concerne des fenêtres (aucune fenêtre ne la couvrera).
        stageAide.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(scrollTout,LARGEUR_FENETRE,LONGEUR_FENETRE, Color.WHITE);
        stageAide.setScene(scene);
        stageAide.showAndWait();

    }
    private static void labelSet(Label lbl){
        lbl.setFont(font);
        lbl.setMaxWidth(550);
        lbl.setWrapText(true);
        lbl.setTextAlignment(TextAlignment.JUSTIFY);

    }


}
