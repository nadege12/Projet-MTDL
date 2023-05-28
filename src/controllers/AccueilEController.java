/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class AccueilEController implements Initializable {

    private Parent fxml;

    @FXML
    private AnchorPane root;
    private String userType;

    @FXML
    private void suivreCours() {
        // Action pour le bouton "Suivre le cours"
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Cours.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showAlert("Suivre le cours");
    }

    @FXML
    private void faireTest() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Test.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showAlert("Faire un test");

    }

    @FXML
    private void faireExamen() {
        // Action pour le bouton "Faire un examen"
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Test.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showAlert("Faire un examen");
    }

    @FXML
    private void voirNote() {
        // Action pour le bouton "Voir la note"
        // Ajoutez votre code ici...
        showAlert("Voir la note");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez cliqué sur : " + message);
        alert.showAndWait();
    }

    @FXML
    void Home(MouseEvent event) {

        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
