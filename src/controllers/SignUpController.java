/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class SignUpController implements Initializable {

    Connection con;
    public PreparedStatement pst;
    public ResultSet rs;

    private String nomUtilisateur;
    private String adresseMail;
    private String motDePasse;
    @FXML
    private JFXTextField nom_utilisateur;

    @FXML
    private JFXTextField adresse_mail;

    @FXML
    private JFXPasswordField mot_de_passe;

    @FXML
    private JFXButton btn_sinscrire;
    @FXML
    private HBox userType;

    @FXML
    private ComboBox<String> userTypeCombo;

    @FXML
    void inscrire(MouseEvent event) {

        String nomUtilisateur = nom_utilisateur.getText();
        String motDePasse = mot_de_passe.getText();
        String userType = userTypeCombo.getValue();

        // Insérez les informations d'inscription dans la base de données
        try {
            String query = "INSERT INTO utilisateur (nom_utilisateur, mot_de_passe, userType) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nomUtilisateur);
            pst.setString(2, motDePasse);
            pst.setString(3, userType);

            pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inscription réussie");
            alert.setHeaderText(null);
            alert.setContentText("Utilisateur inscrit avec succès");
            alert.showAndWait();

            System.out.println("Utilisateur inscrit avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'inscription de l'utilisateur : " + ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeCombo.getItems().addAll("Etudiant", "Enseignant", "Admin");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
            System.out.println("Connexion réussie !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la connexion à la base de données : " + ex.getMessage());
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
