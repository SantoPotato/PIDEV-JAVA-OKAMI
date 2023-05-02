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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EditCategorieStockController implements Initializable {
    
    @FXML
    private baseController BaseController;

    Connection c;
    private Label labelIndex;
    @FXML
    private TextField categorie;


    private List<Stockcategories> types = new ArrayList<>();

    private int id;
    private Button buttonAdd;
    @FXML
    private Button buttonEdit;

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

    void setEquipements(Stockcategories e) throws SQLException {
        categorie.setText(e.getTypecat());
        id = e.getId();

    }

    @FXML
    private void CategoriesUpdate(ActionEvent event) throws SQLException {
        String typecat = categorie.getText();

        Stockcategories e = new Stockcategories();
        StockcategoriesCRUD ec = new StockcategoriesCRUD();
        e.setTypecat(typecat);

        ec.modifier(e, id);
        BaseController.redirectToPage("CategorieStockIndex");
    }

}
