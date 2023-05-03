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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import entities.Categorievehicules;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import services.VehiculescategoriesCRUD;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EditcategorievehiculeController implements Initializable {

    Connection c;
    private int id;

    @FXML
    baseController BaseController;

    @FXML
    private TextField categorie;
    @FXML
    private Button buttonAdd;

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

    void setEquipements(Categorievehicules e) throws SQLException {
        categorie.setText(e.getTypecatv());
        id = e.getId();

    }

    @FXML
    private void CategoriesUpdate(ActionEvent event) throws SQLException {
        String typecat = categorie.getText();

        Categorievehicules e = new Categorievehicules();
        VehiculescategoriesCRUD ec = new VehiculescategoriesCRUD();
        e.setTypecatv(typecat);

        ec.modifier(e, id);
        BaseController.redirectToPage("CategorieVehiculesIndex");
    }

}
