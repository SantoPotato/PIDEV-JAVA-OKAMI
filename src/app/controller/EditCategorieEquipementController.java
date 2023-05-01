/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Categoriesequipement;
import services.CategoriesEquipementCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EditCategorieEquipementController implements Initializable {

    @FXML
    private baseController BaseController;

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonEdit;
    @FXML
    private TextField nomcate;

    private int id;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        BaseController.redirectToPage("CategorieEquipementIndex");
    }

    void setCategorie(Categoriesequipement ce) throws SQLException {
        nomcate.setText(ce.getNomcate());

        id = ce.getId();
    }

    @FXML
    private void cateEdit(ActionEvent event) {
        String nom = nomcate.getText();
        Categoriesequipement ce = new Categoriesequipement(id, nom);
        CategoriesEquipementCRUD cc = new CategoriesEquipementCRUD();

        cc.modifierCategorie(ce, id);

        BaseController.redirectToPage("CategorieEquipementIndex");
    }
}
