/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class RendezvousTypeIndexController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private MenuItem buttonStatistique;
    @FXML
    private TableView<?> tableviewRendezvousType;
    @FXML
    private TableColumn<?, ?> columnNom;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectIndex(ActionEvent event) {
    }

    @FXML
    private void redirectStatistique(ActionEvent event) {
    }

    @FXML
    private void equipementTextSearch(ActionEvent event) {
    }

    @FXML
    private void equipementButtonSearch(ActionEvent event) {
    }

    @FXML
    private void CatAdd(ActionEvent event) {
    }

    @FXML
    private void CatUpdate(ActionEvent event) {
    }

    @FXML
    private void CatDelete(ActionEvent event) {
    }
    
}
