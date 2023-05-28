/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class CoursController implements Initializable {

    Connection con;
    public PreparedStatement pst;
    public ResultSet rs;
    private Parent fxml;
    @FXML
    private AnchorPane root;

    @FXML
    private Label titre;

    @FXML
    private Label prof;
    @FXML
    private JFXTextArea cours;

    @FXML
    void retour(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/AccueilE.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(AccueilEController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    void voirCours() {
        int courseId = 4;
        try {
            String query = "SELECT titre, prof,cours FROM cours WHERE id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, courseId);

            rs = pst.executeQuery();
            if (rs.next()) {
                String titreCours = rs.getString("titre");
                String nomProfesseur = rs.getString("prof");
                String coursDetails = rs.getString("cours");

                titre.setText(titreCours);
                prof.setText("Nom du Prof : " + nomProfesseur);
                cours.setText(coursDetails);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des données du cours : " + ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
            System.out.println("Connexion réussie !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la connexion à la base de données : " + ex.getMessage());
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
