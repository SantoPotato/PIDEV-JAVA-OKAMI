/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import salle.entities.Plannification;
import salle.entities.Salle;
import salle.services.SalleCRUD;
import salle.utils.MyDB;
/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class SalleIndexController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private TableView<Salle> tableviewSalle;
    @FXML
private TableColumn<Salle,Integer> columnNumsa;
@FXML
private TableColumn<Salle, Integer> columnEtagesa;
@FXML
private TableColumn<Salle, String> columnTypesa;

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
    private FilteredList<Salle> filteredSalleList;

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {

    columnNumsa.setCellValueFactory(new PropertyValueFactory<>("numsa"));
    columnEtagesa.setCellValueFactory(new PropertyValueFactory<>("etagesa"));
    columnTypesa.setCellValueFactory(new PropertyValueFactory<>("typesa"));

    SalleCRUD sc = new SalleCRUD();
    tableviewSalle.setItems(FXCollections.observableArrayList(sc.AfficherSalle()));
ObservableList<Salle> salleList = FXCollections.observableArrayList(sc.AfficherSalle());
filteredSalleList = new FilteredList<>(salleList, p -> true);
tableviewSalle.setItems(filteredSalleList);


//search
textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredSalleList.setPredicate(salle -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (salle.getTypesa().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (String.valueOf(salle.getEtagesa()).contains(lowerCaseFilter)) {
            return true;
        } else if (String.valueOf(salle.getNumsa()).contains(lowerCaseFilter)) {
            return true;
        }

        return false;
    });
});

}
 

    @FXML
private void SalleAdd(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleAdd.fxml"));
        buttonIndex.getScene().setRoot(loader.load());

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}

@FXML
private void SalleDelete(ActionEvent event) {
    Salle s = tableviewSalle.getSelectionModel().getSelectedItem();

    if (s != null) {
        SalleCRUD sc = new SalleCRUD();
        sc.SupprimerSalle(s.getId());
        tableviewSalle.getItems().remove(s); // remove from the tableview
        
        System.out.println("Salle Supprimé !");
        refreshTable();
    }
}

    
    @FXML
    private void SalleUpdate(ActionEvent event) {
        Salle s = tableviewSalle.getSelectionModel().getSelectedItem();

        if (s != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleUpdate.fxml"));

                Parent root = loader.load();
                SalleUpdateController c = loader.getController();

                c.setSalle(s);

                buttonIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

}
    
    @FXML
private void handleButtonSearch(ActionEvent event) {
    textSearch.setText("");
    filteredSalleList.setPredicate(salle -> true);
}



@FXML
private void refreshTable() {
    SalleCRUD sc = new SalleCRUD();
    ObservableList<Salle> salleList = FXCollections.observableArrayList(sc.AfficherSalle());
    filteredSalleList = new FilteredList<>(salleList, p -> true);
    tableviewSalle.setItems(filteredSalleList);
}
}
