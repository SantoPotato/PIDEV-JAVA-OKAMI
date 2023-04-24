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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import salle.entities.Plannification;
import salle.entities.Salle;
import salle.services.SalleCRUD;
import salle.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class SalleAddController implements Initializable {
    Connection c;

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private Button buttonAdd;
    @FXML
    private Label labelNum;
    @FXML
    private TextField champNum;
    @FXML
    private Label labelEtage;
    @FXML
    private TextField champEtage;
    @FXML
    private Label labelType;
    @FXML
    private ChoiceBox<String> champType;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
    c = MyDB.getInstance().getCnx();

    // Ajout des options dans la ChoiceBox champType
    champType.getItems().addAll("Option 1", "Option 2", "Option 3");

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
private void SalleAdd(ActionEvent event) {
    int num = Integer.parseInt(champNum.getText());
    int etage = Integer.parseInt(champEtage.getText());
    String type = champType.getSelectionModel().getSelectedItem();

    Salle salle = new Salle(num, etage, type);
    SalleCRUD salleCRUD = new SalleCRUD();
    salleCRUD.AjouterSalle(salle);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("SalleList.fxml"));
    try {
        Parent root = loader.load();
        buttonAdd.getScene().setRoot(root);   // Change the scene to another one
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
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



}