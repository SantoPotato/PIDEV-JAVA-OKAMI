/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import entities.Vehicules;
import entities.Categorievehicules;
import services.VehiculescategoriesCRUD;
import services.VehiculesCRUD;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutVehiculesController implements Initializable {

    Connection c;

    @FXML
    baseController BaseController;

    @FXML
    private Button buttonAdd;
    @FXML
    private TextField nomeq;
    @FXML
    private TextField nomeq1;
    @FXML
    private CheckBox dat;
    @FXML
    private ChoiceBox<Categorievehicules> catColumn;
    @FXML
    private TextField nomq2;
    @FXML
    private TextField nomq3;
    @FXML
    private DatePicker nomq4;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        c = ConnectionDB.getInstance().getConnection();

        VehiculescategoriesCRUD S = new VehiculescategoriesCRUD();

        // Ajout des noms des types dans la choiceBox
        catColumn.getItems().addAll(S.Afficherc());

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        catColumn.setConverter(new StringConverter<Categorievehicules>() {
            @Override
            public String toString(Categorievehicules type) {
                return type.getTypecatv();
            }

            @Override
            public Categorievehicules fromString(String string) {
                return catColumn.getItems().stream().filter(type
                        -> type.getTypecatv().equals(string)).findFirst().orElse(null);
            }
        });

    }

    @FXML
    private void StockAdd(ActionEvent event) {
        Categorievehicules Categoriesvehicules = catColumn.getSelectionModel().getSelectedItem();
        String nomvh = nomeq.getText();
        int dispovh = dat.isSelected() ? 1 : 0;
        String etatvh = nomeq1.getText();

        String descvh = nomq2.getText();
        String imagesvh = nomq3.getText();
        String date = nomq4.getValue().toString();

        Vehicules e = new Vehicules(nomvh, dispovh, etatvh, descvh, imagesvh, date, Categoriesvehicules);
        VehiculesCRUD ec = new VehiculesCRUD();
        System.out.println(e + " ");
        ec.Ajouter(e);

        BaseController.redirectToPage("VehiculesIndex");
    }

}
