/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import controllers.java.StudentGrade;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author nhaba
 */
public class AccueilPController {

    private Parent fxml;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StudentGrade> gradesTable;

    @FXML
    private TableColumn<StudentGrade, String> studentColumn;

    @FXML
    private TableColumn<StudentGrade, Double> gradeColumn;

    private Connection connection;

    public void initialize() {
        // Associer les colonnes à des propriétés de la classe StudentGrade
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // Permettre la modification des cellules de la colonne des notes
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        // Ajouter des données de test à la TableView (remplacez avec vos données de la base de données)
        ObservableList<StudentGrade> studentGrades = FXCollections.observableArrayList();
        studentGrades.add(new StudentGrade("Jean Bernard", 0.0));
        studentGrades.add(new StudentGrade("Nadia Loris", 70.0));
        studentGrades.add(new StudentGrade("Thierno", 80.0));

        gradesTable.setItems(studentGrades);
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
