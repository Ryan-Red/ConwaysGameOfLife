package com.zazo.gameoflife;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static final int LONGEUR_FENETRE = 950;
    private static final int LARGEUR_FENETRE = 1600;
    //Ici j'initialise le font qui sera utilisé dans les labels.
    static final Font font = new Font("Times New Roman", 22);

    private static final Image imgAnimation = new Image("/gof.gif");
    private static final Image imgIcon = new Image("/ZazoLogo.png");

    static VBox vbxTout = new VBox(20);

    static Scene sceneInitiale = new Scene(vbxTout,LARGEUR_FENETRE,LONGEUR_FENETRE);

    static Stage stage = new Stage();

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
    primaryStage.setTitle("Zazo's Game of Life");

    primaryStage.getIcons().add(imgIcon);

    Label lblTitrePrincipal = new Label("Zazo's Game of Life");
    lblTitrePrincipal.setFont(Font.font("Papyrus",FontWeight.BOLD,36));
    StackPane spTitre = new StackPane();
    spTitre.getChildren().add(lblTitrePrincipal);

    Line lnSeparatrice = new Line(0,0,750,0);

    StackPane spLine = new StackPane();
    spLine.getChildren().add(lnSeparatrice);

    ImageView ivAnimation = new ImageView(imgAnimation);
    ivAnimation.setFitHeight(500);
    ivAnimation.setFitWidth(500);
    ivAnimation.setPreserveRatio(true);

    Label lblInformation = new Label("Zazo's Game of Life est un jeu développée par Ryan Zazo pour le projet final du cours de ICS4U en 2018.");
    lblInformation.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 13));

    VBox vbxGauche = new VBox(50);
    vbxGauche.getChildren().addAll(ivAnimation, lblInformation);
    vbxGauche.setAlignment(Pos.CENTER);

    Label lblTitreInstructions = new Label("Instructions:");
    lblTitreInstructions.setFont(Font.font("Times New Roman", FontWeight.BOLD,24));

    Label lblInstructions = new Label("Zazo's Game of Life est une version du Game of Life de Conway. Ce jeu s'agit d'à vrai dire d'un jeu de zéro joueurs, a pour but d'être une automation cellulaire; les cellules dans le jeu peuvent vivre, se reproduire et même mourir, comme une vrai cellule dans le corps humain. Les cellules dans le jeu de Conway opèrent en respectant 4 règles simples: " +
            "\n1/ Une cellule vivante ayant moins de deux cellules meurt" +
            "\n2/ Une cellule vivante ayant deux ou trois voisins vit à la prochaine génération" +
            "\n3/ Une cellule vivante ayant plus de trois voisins meurt" +
            "\n4/ Une cellule morte auant trois voisins se fait réanimée" +
            "\n\nDans ma version par contre, c'est le joueur qui choisit le nombre minimum de cellules adjacentes afin qu'une cellule survive. Afin de jouer commencer le jeu, il ne faut que presser le boutton ci-contre, puis choisir les positions des cellules!");
    lblInstructions.setFont(font);
    lblInstructions.setMaxWidth(500);
    lblInstructions.setWrapText(true);
    lblInstructions.setTextAlignment(TextAlignment.JUSTIFY);

    VBox vbxInstructions = new VBox(10);
    vbxInstructions.getChildren().addAll(lblTitreInstructions,lblInstructions);
    vbxInstructions.setAlignment(Pos.CENTER);
    lblInstructions.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));


    HBox hbxCentre = new HBox(20);
    hbxCentre.getChildren().addAll(vbxGauche,vbxInstructions);
    hbxCentre.setAlignment(Pos.CENTER);

    Button btnCommencer = new Button("Commencer!");
    btnCommencer.setFont(font);
    btnCommencer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,(new CornerRadii(3)), BorderStroke.MEDIUM)));
    btnCommencer.setStyle("-fx-background-color: #d2e2ff;");
    btnCommencer.setPrefSize(200,70);
    StackPane spBoutton = new StackPane();
    spBoutton.getChildren().add(btnCommencer);

    EcranDeJeu.creationScene();




    vbxTout.getChildren().addAll(spTitre,spLine, hbxCentre, spBoutton);
    vbxTout.setPadding(new Insets(30,10,30,10));
    vbxTout.setStyle("-fx-background-color: white;");
    vbxTout.setOpacity(0.0);

    btnCommencer.setOnMouseClicked(e-> {
        fadeOut(vbxTout);
        primaryStage.setScene(EcranDeJeu.sceneJeu);
        fadeIn(EcranDeJeu.vbxTout);
    });

    stage = primaryStage;

    primaryStage.setScene(sceneInitiale);
    primaryStage.setResizable(false);
    primaryStage.show();

    fadeIn(vbxTout);


    }
    static void homeScene(Stage primaryStage){primaryStage.setScene(sceneInitiale);}
    static void fadeIn(Node conteneur){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2),conteneur);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }
    static void fadeOut(Node conteneur){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2),conteneur);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();

    }

}
