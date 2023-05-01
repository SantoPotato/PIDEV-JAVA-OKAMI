/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.Equipement;
import entities.Categoriesequipement;
import services.EquipementCRUD;
import java.sql.SQLException;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author belkn
 */
public class EquipementIndexController implements Initializable {

    @FXML
    private baseController BaseController;

    @FXML
    private TableView<Equipement> tableviewEquipement;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private TableColumn<Equipement, String> columnNom;
    @FXML
    private TableColumn<Equipement, Boolean> columnEtat;
    @FXML
    private TableColumn<Equipement, Boolean> columnDispo;
    @FXML
    private TableColumn<Equipement, Categoriesequipement> columnCat;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomeq"));
        columnEtat.setCellValueFactory(new PropertyValueFactory<>("etateq"));
        columnDispo.setCellValueFactory(new PropertyValueFactory<>("dispoeq"));
        columnCat.setCellValueFactory(new PropertyValueFactory<>("Categoriesequipement"));

        EquipementCRUD ec = new EquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.afficherEquipement()));

    }

    @FXML
    private void equipementTextSearch(ActionEvent event) {
        EquipementCRUD ec = new EquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.searchEquipement(textSearch.getText())));
    }

    @FXML
    private void equipementButtonSearch(ActionEvent event) {
        EquipementCRUD ec = new EquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.searchEquipement(textSearch.getText())));
    }

    @FXML
    private void equipementAdd(ActionEvent event) {
        BaseController.redirectToPage("AjoutEquipement");
    }

    @FXML
    private void equipementUpdate(ActionEvent event) throws SQLException {
        Equipement e = tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {

            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/app/gui/EditEquipement.fxml"));

                Parent root = loader.load();
                EditEquipementController c = loader.getController();

                c.setEquipement(e);

                buttonUpdate.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void equipementDelete(ActionEvent event) {
        Equipement e = (Equipement) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            EquipementCRUD ec = new EquipementCRUD();
            ec.supprimerequipement(e.getId());
            tableviewEquipement.getItems().remove(e);

        }
    }

}
