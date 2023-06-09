/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Categorievehicules;
import services.VehiculescategoriesCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
public class CategorieVehiculesIndexController implements Initializable {

    @FXML
    baseController BaseController;

    @FXML
    private TableView<Categorievehicules> tableviewEquipement;
    @FXML
    private TableColumn<Categorievehicules, String> NomColumn;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    private FilteredList<Categorievehicules> filteredcategorieList;
    @FXML
    private Button buttonDelete;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("typecatv"));

        VehiculescategoriesCRUD cc = new VehiculescategoriesCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(cc.Afficherc()));

        ObservableList<Categorievehicules> stockcatList = FXCollections.observableArrayList(cc.Afficherc());
        filteredcategorieList = new FilteredList<>(stockcatList, p -> true);
        tableviewEquipement.setItems(filteredcategorieList);

//search
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredcategorieList.setPredicate(Stockcategories -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return Stockcategories.getTypecatv().toLowerCase().contains(lowerCaseFilter);
            });
        });

    }

    @FXML
    private void CategorieDelete(ActionEvent event) {

        Categorievehicules e = (Categorievehicules) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            VehiculescategoriesCRUD ec = new VehiculescategoriesCRUD();
            ec.Supprimerc(e.getId());
            tableviewEquipement.getItems().remove(e); // remove from the tableview
        }
    }

    @FXML
    private void CategorieSearch(ActionEvent event) {
        textSearch.setText("");
        filteredcategorieList.setPredicate(vehicule -> true);
    }

    @FXML
    private void CategorieAdd(ActionEvent event) {
        BaseController.redirectToPage("Ajoutcategorievehicule");
    }

    @FXML
    private void CategorieUpdate(ActionEvent event) throws SQLException {
        Categorievehicules e = tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/app/gui/Editcategorievehicules.fxml"));

                Parent root = loader.load();
                EditcategorievehiculeController c = loader.getController();

                c.setEquipements(e);

                buttonUpdate.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
