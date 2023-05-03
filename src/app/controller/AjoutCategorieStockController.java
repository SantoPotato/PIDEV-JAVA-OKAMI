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
import javafx.scene.control.Label;
import entities.Stockcategories;
import services.StockcategoriesCRUD;
import javafx.scene.control.TextField;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutCategorieStockController implements Initializable {
    
    @FXML
    private baseController BaseController;

    Connection c;
    private Label labelIndex;
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

        c = ConnectionDB.getInstance().getConnection();

    }

    @FXML
    private void StockAdd(ActionEvent event) {
        String typecat = categorie.getText();

        Stockcategories e = new Stockcategories();
        StockcategoriesCRUD ec = new StockcategoriesCRUD();
        e.setTypecat(typecat);

        ec.Ajouterc(e);
        BaseController.redirectToPage("CategorieStockIndex");
    }

}
