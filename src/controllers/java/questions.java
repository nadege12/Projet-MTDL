/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.java;

import java.util.List;

/**
 *
 * @author nhaba
 */
public class questions {

    private String questionText;
    private List<String> choices;
    private int correctChoiceIndex;

    public questions(String questionText, List<String> choices, int correctChoiceIndex) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctChoiceIndex = correctChoiceIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectChoiceIndex() {
        return correctChoiceIndex;
    }
    private int selectedChoiceIndex;

    // Existing constructor and getter methods
    public int getSelectedChoiceIndex() {
        return selectedChoiceIndex;
    }

    public void setSelectedChoiceIndex(int selectedChoiceIndex) {
        this.selectedChoiceIndex = selectedChoiceIndex;
    }

}
