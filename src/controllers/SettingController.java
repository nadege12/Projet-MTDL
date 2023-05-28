/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SettingController {

    private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    private CheckBox darkModeCheckbox;

    @FXML
    private Slider volumeSlider;

    @FXML
    void saveSetting() {
        boolean isDarkModeEnabled = darkModeCheckbox.isSelected();

        if (isDarkModeEnabled) {
            root.getStylesheets().addAll(
                    getClass().getResource("/Application/style.css").toExternalForm(),
                    getClass().getResource("/Application/darkmode.css").toExternalForm()
            );

        }
    }

    @FXML
    void retour(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
