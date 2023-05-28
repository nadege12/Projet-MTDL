package controllers;

import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class QuestionsController {

    @FXML
    private TextField questionText;

    @FXML
    private TextField answer1Text;

    @FXML
    private TextField answer2Text;

    @FXML
    private TextField answer3Text;

    @FXML
    private TextField answer4Text;

    @FXML
    void saveQuestion() {
        String question = questionText.getText();
        String answer1 = answer1Text.getText();
        String answer2 = answer2Text.getText();
        String answer3 = answer3Text.getText();
        String answer4 = answer4Text.getText();

        // Enregistrement dans la base de données
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
            String query = "INSERT INTO questions (question, answer1, answer2, answer3, answer4) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, question);
            statement.setString(2, answer1);
            statement.setString(3, answer2);
            statement.setString(4, answer3);
            statement.setString(5, answer4);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Exporter les questions dans les fichiers
        try {
            String fileWithMark = Paths.get(".", "fichiers", "test_marques.txt").toString();
            String fileWithoutMark = Paths.get(".", "fichiers", "test_sansmarques.txt").toString();
            // Exporter avec la bonne réponse marquée
            BufferedWriter writer1 = new BufferedWriter(new FileWriter("test_marque.txt", true));
            writer1.write("Question: " + question);
            writer1.newLine();
            writer1.write("Réponse 1: " + answer1);
            writer1.newLine();
            writer1.write("Réponse 2: " + answer2);
            writer1.newLine();
            writer1.write("Réponse 3: " + answer3);
            writer1.newLine();
            writer1.write("Réponse 4 (Bonne réponse): " + answer4);
            writer1.newLine();
            writer1.newLine();
            writer1.close();

            // Exporter sans la bonne réponse marquée
            BufferedWriter writer2 = new BufferedWriter(new FileWriter("test_sans_marque.txt", true));
            writer2.write("Question: " + question);
            writer2.newLine();
            writer2.write("Réponse 1: " + answer1);
            writer2.newLine();
            writer2.write("Réponse 2: " + answer2);
            writer2.newLine();
            writer2.write("Réponse 3: " + answer3);
            writer2.newLine();
            writer2.write("Réponse 4: " + answer4);
            writer2.newLine();
            writer2.newLine();
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
