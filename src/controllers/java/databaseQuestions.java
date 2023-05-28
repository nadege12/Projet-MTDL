package controllers.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controllers.java.questions;

public class databaseQuestions {

    private Connection connection;

    public databaseQuestions() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");
        } catch (SQLException e) {
        }
    }

    public void insertQuestion(questions question) {
        String sql = "INSERT INTO questions (question, answer1, answer2, answer3, answer4, correct_choice_index) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question.getQuestionText());
            statement.setString(2, question.getChoices().get(0));
            statement.setString(3, question.getChoices().get(1));
            statement.setString(4, question.getChoices().get(2));
            statement.setString(5, question.getChoices().get(3));
            statement.setInt(6, question.getCorrectChoiceIndex());

            statement.executeUpdate();

            System.out.println("Question inserted into the database successfully!");
        } catch (SQLException e) {
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
        }
    }
}
