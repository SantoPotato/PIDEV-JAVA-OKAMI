/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import salle.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class PlannificationAddController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private Button buttonAdd;
    @FXML
    private Label labesNumsa;
    @FXML
    private ComboBox<String> champNumsa;
    @FXML
    private DatePicker champDatepl;
    @FXML
    private DatePicker champHeuredebutpl;
    @FXML
    private DatePicker champHeurefinpl;
    Connection c;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = MyDB.getInstance().getCnx();

        // Création d'une liste pour stocker les numéros de salles
List<String> numSalleList = new ArrayList<>();

// Exécution de la requête SQL pour récupérer les numéros de salles depuis la base de données
String sql = "SELECT numsa FROM salle";
try (Statement stmt = c.createStatement()) {
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        String numSalle = rs.getString("numsa");
        numSalleList.add(numSalle);
    }
} catch (SQLException ex) {
    // Gestion des erreurs SQL
    ex.printStackTrace();
}

// Ajout des numéros de salles à la ComboBox champNumsa
champNumsa.getItems().addAll(numSalleList);

// Définition de la façon dont les options seront affichées dans la ComboBox champNumsa
champNumsa.setConverter(new StringConverter<String>() {
    @Override
    public String toString(String option) {
        return option;
    }

    @Override
    public String fromString(String string) {
        return string;
    }
});

    }
        

    @FXML
    private void redirectSalle(ActionEvent event) {
    }

    @FXML
    private void redirectPlannification(ActionEvent event) {
    }

    @FXML
    private void PlannificationAdd(ActionEvent event) {
    }
    
}
