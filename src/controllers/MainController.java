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
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class MainController implements Initializable {

    @FXML
    private Button btn_seconnecter;

    @FXML
    private VBox VBox;

    @FXML
    private Button btn_sinscrire;
    private Parent fxml;

    @FXML
    void openSignIn() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
        t.setToX(5);
        t.play();
        t.setOnFinished(e -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("/interfaces/SignIn.fxml"));

                VBox.getChildren().removeAll();
                VBox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    void openSignUp() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
        t.setToX(VBox.getLayoutX() + 5);
        t.play();
        t.setOnFinished(e -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("/interfaces/SignUp.fxml"));

                VBox.getChildren().removeAll();
                VBox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
        //t.setToX(VBox.getLayoutX() + 2.5);

        t.play();
        t.setOnFinished(e -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("/interfaces/SignIn.fxml"));

                VBox.getChildren().removeAll();
                VBox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
