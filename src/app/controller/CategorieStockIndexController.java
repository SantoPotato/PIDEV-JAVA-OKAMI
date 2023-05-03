/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Stockcategories;
import services.StockcategoriesCRUD;
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
public class CategorieStockIndexController implements Initializable {

    @FXML
    baseController BaseController;

    @FXML
    private TableView<Stockcategories> tableviewEquipement;
    @FXML
    private TableColumn<Stockcategories, String> NomColumn;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    private FilteredList<Stockcategories> filteredcategorieList;
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

        NomColumn.setCellValueFactory(new PropertyValueFactory<>("typecat"));

        StockcategoriesCRUD cc = new StockcategoriesCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(cc.Afficherc()));

        ObservableList<Stockcategories> stockcatList = FXCollections.observableArrayList(cc.Afficherc());
        filteredcategorieList = new FilteredList<>(stockcatList, p -> true);
        tableviewEquipement.setItems(filteredcategorieList);

//search
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredcategorieList.setPredicate(Stockcategories -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return Stockcategories.getTypecat().toLowerCase().contains(lowerCaseFilter);
            });
        });

    }

    @FXML
    private void categorieAdd(ActionEvent event) {
        BaseController.redirectToPage("AjoutCategorieStock");
    }

    @FXML
    private void categorieUpdate(ActionEvent event) throws SQLException {
        Stockcategories e = tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/app/gui/EditCategorieStock.fxml"));

                Parent root = loader.load();
                EditCategorieStockController c = loader.getController();

                c.setEquipements(e);

                tableviewEquipement.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void CategorieDelete(ActionEvent event) {

        Stockcategories e = (Stockcategories) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            StockcategoriesCRUD ec = new StockcategoriesCRUD();
            ec.Supprimerc(e.getId());
            tableviewEquipement.getItems().remove(e); // remove from the tableview
        }
    }

}
