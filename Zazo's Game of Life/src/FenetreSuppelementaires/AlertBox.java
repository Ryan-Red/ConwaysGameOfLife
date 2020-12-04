package FenetreSuppelementaires;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    //Ici j'initialise les constantes, la longueur et largeur de la fenêtre de la boite d'alerte.
    private static final int BOITEALERTE_LARGEUR = 550;
    private static final int BOITEALERTE_LONGUEUR = 250;

    public static void alert(String titre, String message, int fontSize){
        //Les arguments de la méthode alert est juste un titre sous forme de string et un message sous forme de string.

        //Ici j'initialise la fenêtre, en ce qui concerne sa taille et son titre.
        Stage boiteAlerte = new Stage();
        boiteAlerte.setTitle(titre);

        //Ici je dis au Stage(une fenêtre) (boiteAlerte), d'être de la priorité la plus haute en ce qui concerne des fenêtres (aucune fenêtre ne la couvrera).
        boiteAlerte.initModality(Modality.APPLICATION_MODAL);

        //Ici j'initialise le label qui contiendrait le message, avec le message étant donné lorsqu'on appelle cette méthode.
        Label lblMessage = new Label(message);
        lblMessage.setFont(new Font("Times New Roman",fontSize));
        lblMessage.setMaxWidth(400);
        lblMessage.setWrapText(true);
        lblMessage.setTextAlignment(TextAlignment.JUSTIFY);

        //J'initialise aussi un boutton pour fermer la boite d'alerte.
        Button btnFermer = new Button("Ok");

        //Ici je donne la fonction de fermer l'écran lorsqu'on pèse sur le boutton.
        btnFermer.setOnMouseClicked(e -> boiteAlerte.close());

        //J'ajoute ensuite un conteneur de type VBox pour contenir le Label contenant le message et le boutton pour fermer la boite d'alerte.
        VBox vbxCentre = new VBox(10);
        vbxCentre.getChildren().addAll(lblMessage,btnFermer);
        vbxCentre.setAlignment(Pos.CENTER);

        //Finalement j'intialise la scène du stage et j'y ajoute le VBox contenant le Label et le boutton et ensuite je montre la fenêtre.
        Scene scAlertBox = new Scene(vbxCentre,BOITEALERTE_LARGEUR,BOITEALERTE_LONGUEUR);
        boiteAlerte.setScene(scAlertBox);
        boiteAlerte.showAndWait();

    }
}
