/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Categoriesequipement;
import entities.Equipement;
import services.CategoriesEquipementCRUD;
import services.EquipementCRUD;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EditEquipementController implements Initializable {

    Connection c;

    @FXML
    private baseController BaseController;

    @FXML
    private TextField nomeq;
    @FXML
    private CheckBox etat;
    @FXML
    private CheckBox Disponible;
    @FXML
    private ComboBox<Categoriesequipement> Categorie;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonEdit;

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

        EquipementCRUD ec = new EquipementCRUD();
        Categoriesequipement ce = new Categoriesequipement();
        CategoriesEquipementCRUD ct = new CategoriesEquipementCRUD();
        c = ConnectionDB.getInstance().getConnection();
        Categorie.setItems(FXCollections.observableArrayList(ct.afficherCategorie()));
    }

    void setEquipement(Equipement e) throws SQLException {
        nomeq.setText(e.getNomeq());
        etat.setSelected(e.getEtateq());
        Disponible.setSelected(e.getDispoeq());
        Categorie.setValue(e.getCategoriesequipement());
        id = e.getId();
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        BaseController.redirectToPage("EquipementIndex");
    }

    @FXML
    private void equipementeDIT(ActionEvent event) {
        String nom = nomeq.getText();
        Boolean etateq = etat.isSelected();
        Boolean dispo = Disponible.isSelected();
        Categoriesequipement type = Categorie.getValue();
        Equipement e = new Equipement(id, nom, etateq, dispo, type);
        EquipementCRUD ec = new EquipementCRUD();

        ec.modifierequipement(e, id);

        BaseController.redirectToPage("EquipementIndex");
    }

}
