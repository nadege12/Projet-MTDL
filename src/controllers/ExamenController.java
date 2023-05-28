/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class ExamenController implements Initializable {

    private Parent fxml;
    @FXML
    private VBox root;
    @FXML
    private Label question1;

    @FXML
    private JFXRadioButton option1;

    @FXML
    private JFXRadioButton option2;

    @FXML
    private JFXRadioButton option3;

    @FXML
    private JFXRadioButton option4;

    @FXML
    private Label question2;

    @FXML
    private JFXRadioButton option5;

    @FXML
    private JFXRadioButton option6;

    @FXML
    private JFXRadioButton option7;

    @FXML
    private JFXRadioButton option8;
    @FXML

    private ToggleGroup toggleGroup1;
    private ToggleGroup toggleGroup2;

    @FXML
    void valider() {
        int note = 0;

        if (toggleGroup1.getSelectedToggle() == option2) {
            note++;
        }

        if (toggleGroup2.getSelectedToggle() == option5) {
            note++;
        }

        String resultat = "Note : " + note + "/2";
        System.out.println(resultat);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Résultat");
        alert.setHeaderText(null);
        alert.setContentText(resultat);
        alert.showAndWait();
    }

    @FXML
    void quitter(MouseEvent event) {

        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(AccueilEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toggleGroup1 = new ToggleGroup();
        toggleGroup2 = new ToggleGroup();

        option1.setToggleGroup(toggleGroup1);
        option2.setToggleGroup(toggleGroup1);
        option3.setToggleGroup(toggleGroup1);
        option4.setToggleGroup(toggleGroup1);

        option5.setToggleGroup(toggleGroup2);
        option6.setToggleGroup(toggleGroup2);
        option7.setToggleGroup(toggleGroup2);
        option8.setToggleGroup(toggleGroup2);

        question1.setText("Qu'est-ce que l'infographie ?");
        option1.setText("c'est un outil mathematique qui permet de calculer");
        option2.setText("C'est le domaine de la création d'images numériques assistée par ordinateur");
        option3.setText("C'est un parametre d'images");
        option4.setText("aucune reponses vraies");

        question2.setText("C'est quoi l'infographie 2D ?");
        option5.setText("C'est la creation des animations en 2D pour les jeux video ");
        option6.setText("Dessin technique");
        option7.setText("Un model de  dessin");
        option8.setText("Aucune reponses correctes");
    }

}
