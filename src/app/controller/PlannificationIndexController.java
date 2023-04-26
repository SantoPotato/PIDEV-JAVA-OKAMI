package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import salle.entities.Plannification;
import salle.entities.Salle;
import salle.services.PlannificationCRUD;

public class PlannificationIndexController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private TableView<Plannification> tableviewPlannification;
    @FXML
    private TableColumn<Plannification, Integer> columnNumsa;
    @FXML
    private TableColumn<Plannification, Date> columnDatepl;
    @FXML
    private TableColumn<Plannification, Time> columnHeuredebutpl;
    @FXML
    private TableColumn<Plannification, Time> columnHeurefinpl;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    private FilteredList<Plannification> filteredPlannificationList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnNumsa.setCellValueFactory(new PropertyValueFactory<>("id_salle"));
        columnDatepl.setCellValueFactory(new PropertyValueFactory<>("datepl"));
        columnHeuredebutpl.setCellValueFactory(new PropertyValueFactory<>("heuredebutpl"));
        columnHeurefinpl.setCellValueFactory(new PropertyValueFactory<>("heurefinpl"));

        PlannificationCRUD pc = new PlannificationCRUD();
        tableviewPlannification.setItems(FXCollections.observableArrayList(pc.AfficherPlannification()));
        ObservableList<Plannification> plannificationList = FXCollections.observableArrayList(pc.AfficherPlannification());
        filteredPlannificationList = new FilteredList<>(plannificationList, p -> true);
        tableviewPlannification.setItems(filteredPlannificationList);

        //search
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPlannificationList.setPredicate(plannification -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(plannification.getId_salle()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    @FXML
    private void handleButtonSearch(ActionEvent event) {
        textSearch.setText("");
        filteredPlannificationList.setPredicate(salle -> true);
    }

    @FXML
    private void PlannificationAdd(ActionEvent event) {
    }

    @FXML
    private void PlannificationUpdate(ActionEvent event) {
    }

    @FXML
    private void PlannificationDelete(ActionEvent event) {
    }
    
    @FXML
    private void redirectSalle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
