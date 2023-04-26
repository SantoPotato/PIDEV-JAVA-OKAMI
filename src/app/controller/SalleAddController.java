/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import salle.entities.Salle;
import salle.services.SalleCRUD;
import salle.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class SalleAddController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private Button buttonAdd;
    @FXML
    private TextField champNum;
    @FXML
    private TextField champEtage;
    @FXML
    private ComboBox<String> champType;
    Connection c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    c = MyDB.getInstance().getCnx();

    // Ajout des options dans la ChoiceBox champType
    champType.getItems().addAll("Opération", "Administratif", "Repos");

    // Définition de la façon dont les options seront affichées dans la ChoiceBox
    champType.setConverter(new StringConverter<String>() {
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
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleIndex.fxml"));
        buttonIndex.getScene().setRoot(loader.load());

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void redirectPlannification(ActionEvent event) {
    }

    @FXML
private void SalleAdd(ActionEvent event) {
    String errorMessage = "";
    int num = 0;
    int etage = 0;
    String type = "";

    // Vérifier les champs saisis
    try {
        num = Integer.parseInt(champNum.getText());
        if (num > 10) {
            errorMessage += "Le numéro de la salle ne doit pas dépasser 10.\n";
        }
    } catch (NumberFormatException e) {
        errorMessage += "Le numéro de la salle doit être un entier.\n";
    }
    try {
        etage = Integer.parseInt(champEtage.getText());
        if (etage > 6) {
            errorMessage += "L'étage de la salle ne doit pas dépasser 6.\n";
        }
    } catch (NumberFormatException e) {
        errorMessage += "L'étage de la salle doit être un entier.\n";
    }
    if (champType.getValue() == null || champType.getValue().isEmpty()) {
        errorMessage += "Le type de la salle ne doit pas être vide.\n";
    } else {
        type = champType.getValue();
    }

    if (errorMessage.isEmpty()) {
        Salle salle = new Salle(num, etage, type);
        SalleCRUD salleCRUD = new SalleCRUD();
        salleCRUD.AjouterSalle(salle);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleIndex.fxml"));
        try {
            Parent root = loader.load();
            buttonAdd.getScene().setRoot(root);   // Change the scene to another one
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        // Afficher une boîte de dialogue modale avec les messages d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Il y a des erreurs de saisie");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}


    
}
