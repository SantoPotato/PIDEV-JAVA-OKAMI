/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import entities.Stock;
import entities.Stockcategories;
import services.StockCRUD;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Parent;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author belkn
 */
public class StockIndexController implements Initializable {

    @FXML
    private baseController BaseController;

    @FXML
    private TableView<Stock> tableviewEquipement;
    @FXML
    private TableColumn<Stock, Integer> nomColumn;
    @FXML
    private TableColumn<Stock, String> desColumn;
    @FXML
    private TableColumn<Stock, Date> datColumn;
    @FXML
    private TableColumn<Stock, Stockcategories> catColumn;
    @FXML
    private TableColumn<Stock, String> quantColumn;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    private FilteredList<Stock> filteredStockList;
    @FXML
    private Button buttonDeletee;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomst"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        datColumn.setCellValueFactory(new PropertyValueFactory<>("dateexpirationst"));
        catColumn.setCellValueFactory(new PropertyValueFactory<>("stockcat_id"));
        quantColumn.setCellValueFactory(new PropertyValueFactory<>("quantites"));

        nomColumn.setPrefWidth(100);
        desColumn.setPrefWidth(500);
        datColumn.setPrefWidth(300);
        catColumn.setPrefWidth(600);
        quantColumn.setPrefWidth(60);

        StockCRUD ec = new StockCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.Afficher()));

        ObservableList<Stock> stockList = FXCollections.observableArrayList(ec.Afficher());
        filteredStockList = new FilteredList<>(stockList, p -> true);
        tableviewEquipement.setItems(filteredStockList);

//search
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredStockList.setPredicate(stock -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (stock.getNomst().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(stock.getQuantites()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(stock.getDateexpirationst()).contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });
        // Create an instance of the Stock class
        Stock stock = new Stock();

// Call the getQuantites() method on the instance of Stock
        if (stock.getQuantites() < 10) {
            Notification.showNotification("Nouvelle Notification", String.format("Votre stock Aspirine est inférieur à 10!"));
        }

    }

    @FXML
    private void StockAdd(ActionEvent event) {
        BaseController.redirectToPage("AjoutStock");
    }

    @FXML
    private void StockUpdate(ActionEvent event) throws SQLException {
        Stock e = tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/app/gui/EditStock.fxml"));

                Parent root = loader.load();
                EditStockController c = loader.getController();

                c.setEquipement(e);

                tableviewEquipement.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void StockDeletee(ActionEvent event) {
        Stock e = (Stock) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            StockCRUD ec = new StockCRUD();
            ec.Supprimer(e.getId());
            tableviewEquipement.getItems().remove(e); // remove from the tableview

        }
    }

}
