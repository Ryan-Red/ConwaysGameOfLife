package FenetreSuppelementaires;

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
        lblInformationReglements.setFont(font);
        lblInformationReglements.setMaxWidth(550);
        lblInformationReglements.setWrapText(true);
        lblInformationReglements.setTextAlignment(TextAlignment.JUSTIFY);


        VBox vbxReglements = new VBox(10);
        vbxReglements.getChildren().addAll(lblReglementsGeneraux,lblInformationReglements);
        vbxReglements.setAlignment(Pos.CENTER);


        Label lblCellulesAdjacentesTitre = new Label("Le nombre de Cellules Adjacentes pour vivre:");
        lblCellulesAdjacentesTitre.setFont(fontSousTitre);

        Label lblInformationCelluleAdjacente = new Label("Le nombre de cellules adjacentes pour vivre défini le nombre de cellules adjacentes requises pour que la cellule vive. De plus, la valeur entrée définie aussi le nombre de cellules adjcatentes requises pour que la cellule meurt de surpopulation, meurt de sous population et pour qu'une case vide se fait « réssusciter »");
        lblInformationCelluleAdjacente.setFont(font);
        lblInformationCelluleAdjacente.setMaxWidth(550);
        lblInformationCelluleAdjacente.setWrapText(true);
        lblInformationCelluleAdjacente.setTextAlignment(TextAlignment.JUSTIFY);

        VBox vbxCellule = new VBox(10);
        vbxCellule.getChildren().addAll(lblCellulesAdjacentesTitre,lblInformationCelluleAdjacente);
        vbxCellule.setAlignment(Pos.CENTER);


        Label lblRangeeColone = new Label("Le nombre de rangées et de colonnes:");
        lblRangeeColone.setFont(fontSousTitre);

        Label lblInformationRangeeColone = new Label("Le nombre de rangées et de colones définit le nombre de rangées et de colonnes dans la grille du jeu.");
        lblInformationRangeeColone.setFont(font);
        lblInformationRangeeColone.setMaxWidth(550);
        lblInformationRangeeColone.setWrapText(true);
        lblInformationRangeeColone.setTextAlignment(TextAlignment.JUSTIFY);

        VBox vbxRangeeColone = new VBox(10);
        vbxRangeeColone.getChildren().addAll(lblRangeeColone,lblInformationRangeeColone);
        vbxRangeeColone.setAlignment(Pos.CENTER);


        Label lblModePeinture = new Label("Le mode peinture:");
        lblModePeinture.setFont(fontSousTitre);

        Label lblInformationModePeinture = new Label("Le Mode Peinture est un mode qui fait que lorsque le grillage est sélectionné, les cellules se font créées sous la souris de l'ordinateur et lorsqu'on clique la souris, ceci s'interrompte.");
        lblInformationModePeinture.setFont(font);
        lblInformationModePeinture.setMaxWidth(550);
        lblInformationModePeinture.setWrapText(true);
        lblInformationModePeinture.setTextAlignment(TextAlignment.JUSTIFY);

        VBox vbxModePeinture = new VBox(10);
        vbxModePeinture.getChildren().addAll(lblModePeinture,lblInformationModePeinture);
        vbxModePeinture.setAlignment(Pos.CENTER);

        Label lblVitesse = new Label("La vitesse:");
        lblVitesse.setFont(fontSousTitre);

        Label lblInformationVitesse = new Label("La vitesse définit le multiplicateur de vitesse du jeu.");
        lblInformationVitesse.setFont(font);
        lblInformationVitesse.setMaxWidth(550);
        lblInformationVitesse.setWrapText(true);
        lblInformationVitesse.setTextAlignment(TextAlignment.JUSTIFY);

        VBox vbxVitesse = new VBox(10);
        vbxVitesse.getChildren().addAll(lblVitesse,lblInformationVitesse);
        vbxVitesse.setAlignment(Pos.CENTER);


        VBox vbxRenseignements = new VBox(10);
        vbxRenseignements.getChildren().addAll(spTitre, vbxReglements, vbxCellule,vbxRangeeColone,vbxModePeinture,vbxVitesse);
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


}
