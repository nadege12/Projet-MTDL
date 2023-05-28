package controllers;

import controllers.java.questions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TestController {

    private ObservableList<questions> questionList = FXCollections.observableArrayList();
    private boolean questionsLoaded = false;
    private Parent fxml;
    @FXML
    private VBox root;

    @FXML
    void showQuestions() {
        if (!questionsLoaded) {
            loadQuestions();
        }
        showQuestionsDialog();
    }

    private void loadQuestions() {
        try (InputStream inputStream = getClass().getResourceAsStream("/fichiers/test_sansmarques.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Question:")) {
                    String questionText = line.substring(9).trim();
                    List<String> choices = new ArrayList<>();
                    int correctChoiceIndex = -1;

                    for (int i = 0; i < 4; i++) {
                        line = reader.readLine();
                        if (line.startsWith(".")) {
                            correctChoiceIndex = i;
                            choices.add(line.substring(1).trim());
                        } else {
                            choices.add(line.substring(11).trim());
                        }
                    }

                    questions question = new questions(questionText, choices, correctChoiceIndex);
                    questionList.add(question);
                }
            }

            questionsLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showQuestionsDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Questions");
        alert.setHeaderText(null);

        List<ToggleGroup> toggleGroups = new ArrayList<>();

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        for (int i = 0; i < questionList.size(); i++) {
            questions question = questionList.get(i);

            ToggleGroup toggleGroup = new ToggleGroup();

            VBox questionVBox = new VBox();
            questionVBox.setSpacing(5);

            Label questionLabel = new Label(question.getQuestionText());
            questionLabel.setStyle("-fx-font-weight: bold");

            List<RadioButton> radioButtons = new ArrayList<>();
            for (int j = 0; j < question.getChoices().size(); j++) {
                RadioButton radioButton = new RadioButton(question.getChoices().get(j));
                radioButton.setToggleGroup(toggleGroup);
                radioButtons.add(radioButton);
            }

            questionVBox.getChildren().addAll(questionLabel, radioButtons.get(0), radioButtons.get(1),
                    radioButtons.get(2), radioButtons.get(3));

            vbox.getChildren().add(questionVBox);
            toggleGroups.add(toggleGroup);
        }

        alert.getDialogPane().setContent(vbox);

        ButtonType submitButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
        alert.getButtonTypes().add(submitButtonType);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == submitButtonType) {
            // Process the submitted answers
            int correctAnswers = 0;
            int totalQuestions = questionList.size();

            for (int i = 0; i < questionList.size(); i++) {
                questions question = questionList.get(i);
                int selectedChoiceIndex = toggleGroups.get(i).getToggles()
                        .indexOf(toggleGroups.get(i).getSelectedToggle());

                question.setSelectedChoiceIndex(selectedChoiceIndex);

                if (selectedChoiceIndex == question.getCorrectChoiceIndex()) {
                    correctAnswers++;
                }
            }

            double score = (double) correctAnswers / totalQuestions * 100;

            // Save the score to the database
            saveScoreToDatabase(score);

            Alert resultAlert = new Alert(AlertType.INFORMATION);
            resultAlert.setTitle("Test Results");
            resultAlert.setHeaderText(null);
            resultAlert.setContentText("You answered " + correctAnswers + " out of " + totalQuestions + " correctly.\n"
                    + "Your score: " + score + "%");
            resultAlert.showAndWait();
        }
    }

    private void saveScoreToDatabase(double score) {
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/graphique_help", "root", "Monavenir1998!");

            // Prepare the SQL statement
            String sql = "INSERT INTO note (note) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the parameter values
            statement.setDouble(1, score);

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void submitAnswers() {
        int correctAnswers = 0;
        int totalQuestions = questionList.size();

        for (questions question : questionList) {
            if (question.getSelectedChoiceIndex() == question.getCorrectChoiceIndex()) {
                correctAnswers++;
            }
        }

        double score = (double) correctAnswers / totalQuestions * 100;

        // Save the score to the database
        saveScoreToDatabase(score);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Test Results");
        alert.setHeaderText(null);
        alert.setContentText("You answered " + correctAnswers + " out of " + totalQuestions + " correctly.\n"
                + "Your score: " + score + "%");
        System.out.println(" la note est : ");
        alert.showAndWait();
    }

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
}
