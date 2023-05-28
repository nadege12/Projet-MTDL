package controllers;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

public class NoteController implements Initializable {

    Connection con;
    public PreparedStatement pst;
    public ResultSet rs;
    @FXML
    private AnchorPane root;

    @FXML
    private Label note;

    @FXML
    void retour(MouseEvent event) {

    }

    void voirNote() {
        int noteId = 4;
        try {
            String query = "SELECT note FROM note WHERE id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, noteId);

            rs = pst.executeQuery();
            if (rs.next()) {
                String maNote = rs.getString("note");

                note.setText(maNote);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de la note: " + ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
            System.out.println("Connexion réussie !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la connexion à la base de données : " + ex.getMessage());
            Logger.getLogger(NoteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
