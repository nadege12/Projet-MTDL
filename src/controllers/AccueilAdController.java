package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class AccueilAdController implements Initializable {

    @FXML
    private AnchorPane root;
    private Parent fxml;

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Establish database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteCourse(ActionEvent event) {
        showConfirmationDialog("Supprimer un cours", "Êtes-vous sûr de vouloir supprimer ce cours ?", "DELETE FROM cours WHERE id = ?");
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        showConfirmationDialog("Supprimer un étudiant", "Êtes-vous sûr de vouloir supprimer cet étudiant ?", "DELETE FROM student WHERE id = ?");
    }

    @FXML
    void deleteTeacher(ActionEvent event) {
        showConfirmationDialog("Supprimer un professeur", "Êtes-vous sûr de vouloir supprimer ce professeur ?", "DELETE FROM teacher WHERE id = ?");
    }

    private void showConfirmationDialog(String title, String message, String deleteQuery) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with the deletion
            try {
                preparedStatement = connection.prepareStatement(deleteQuery);
                // Set the appropriate ID parameter for deletion
                preparedStatement.setInt(1, 1); // 
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Deletion successful, show a success message or perform any additional actions
                    System.out.println("Deletion successful");
                } else {
                    // No rows affected, handle deletion failure
                    System.out.println("Deletion failed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any database errors
            }
        }

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

}
