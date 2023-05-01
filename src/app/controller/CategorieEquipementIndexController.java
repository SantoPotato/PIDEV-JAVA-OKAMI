/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Categoriesequipement;
import services.CategoriesEquipementCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class CategorieEquipementIndexController implements Initializable {

    @FXML
    private baseController BaseController;

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
    private TableView<Categoriesequipement> tableviewCategorie;
    @FXML
    private TableColumn<Categoriesequipement, String> columnNom;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomcate"));

        CategoriesEquipementCRUD cc = new CategoriesEquipementCRUD();
        tableviewCategorie.setItems(FXCollections.observableArrayList(cc.afficherCategorie()));
    }

    @FXML
    private void equipementTextSearch(ActionEvent event) {
    }

    @FXML
    private void equipementButtonSearch(ActionEvent event) {
    }

    @FXML
    private void CatAdd(ActionEvent event) {
        BaseController.redirectToPage("AjoutCategorieEquipement");
    }

    @FXML
    private void CatUpdate(ActionEvent event) throws SQLException {
        Categoriesequipement ce = tableviewCategorie.getSelectionModel().getSelectedItem();

        if (ce != null) {

            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/app/gui/EditCategorieEquipement.fxml"));

                Parent root = loader.load();
                EditCategorieEquipementController c = loader.getController();

                c.setCategorie(ce);

                buttonUpdate.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void CatDelete(ActionEvent event) {
        Categoriesequipement ce = (Categoriesequipement) tableviewCategorie.getSelectionModel().getSelectedItem();

        if (ce != null) {
            CategoriesEquipementCRUD cc = new CategoriesEquipementCRUD();
            cc.supprimerCategorie(ce.getId());
            tableviewCategorie.getItems().remove(ce);

        }
    }

}
