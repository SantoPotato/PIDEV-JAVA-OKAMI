/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import entities.Categorievehicules;
import services.VehiculescategoriesCRUD;
import javafx.scene.control.TextField;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutcategorievehiculeController implements Initializable {

    Connection c;
    
    @FXML
    baseController BaseController;
    
    @FXML
    private Button buttonAdd;
    @FXML
    private TextField categorie;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        c = c = ConnectionDB.getInstance().getConnection();

    }

    @FXML
    private void StockAdd(ActionEvent event) {
        String typecatv = categorie.getText();

        Categorievehicules e = new Categorievehicules();
        VehiculescategoriesCRUD ec = new VehiculescategoriesCRUD();
        e.setTypecatv(typecatv);

        ec.Ajouterc(e);
        BaseController.redirectToPage("CategorieVehiculesIndex");
    }

}
