/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class SignInController implements Initializable {

    Connection con;
    public PreparedStatement pst;
    public ResultSet rs;
    private String userType;

    private String nom;
    private String mdp;
    private Parent fxml;

    @FXML
    private VBox Vbox;
    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXButton btn_passwordforgotten;

    @FXML
    private JFXButton btn_seconnecter;

    @FXML
    void openHome() {

        Vbox.getScene().getWindow().hide();
        Stage home = new Stage();
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
            Scene scene = new Scene(fxml);
            home.setScene(scene);
            home.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String nom = txt_username.getText();
        String mdp = txt_password.getText();
        try {

            String query = "SELECT * FROM utilisateur WHERE nom_utilisateur = ? AND mot_de_passe = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, nom);
            pst.setString(2, mdp);

            rs = pst.executeQuery();
            if (rs.next()) {
                String userType = rs.getString("userType");
                if (userType.equals("Enseignant") || userType.equals("admin") || userType.equals("Etudiant")) {
                    System.out.println("Connexion réussie en tant que " + userType + " !");

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Connexion réussie");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion réussie en tant que " + userType + " !");
                    alert.showAndWait();
                } else {
                    System.out.println("Vous n'avez pas l'autorisation d'accéder à la section enseignant ou admin !");
                    // Afficher un message d'erreur ou prendre les mesures appropriées pour les autres types d'utilisateurs
                }
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect !");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche dans la base de données : " + ex.getMessage());
        }

    }

    @FXML
    void sendPassword() {
// Récupérer le nom d'utilisateur entré par l'utilisateur
        String nomUtilisateur = txt_username.getText();

        // Interroger la base de données pour récupérer le mot de passe associé au nom d'utilisateur
        try {
            String query = "SELECT mot_de_passe FROM utilisateur WHERE nom_utilisateur = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, nomUtilisateur);
            rs = pst.executeQuery();

            if (rs.next()) {
                String motDePasse = rs.getString("mot_de_passe");
                // Afficher le mot de passe récupéré ou prendre une autre action appropriée
                System.out.println("Le mot de passe de l'utilisateur est : " + motDePasse);
            } else {
                System.out.println("Nom d'utilisateur incorrect !");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche dans la base de données : " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
            System.out.println("Connexion réussie !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la connexion à la base de données : " + ex.getMessage());
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
