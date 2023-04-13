/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categoriesequipement;
import Services.CategoriesEquipementCRUD;
import Services.EquipementCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class CategorieIndexController implements Initializable {

    @FXML
    private Label labelPage;
    @FXML
    private Label labelPath;
    @FXML
    private Label labelIndex;
    @FXML
    private Button buttonEquipement;
    @FXML
    private Button buttonCategorie;
    @FXML
    private Button buttonTest;
    @FXML
    private TableView<Categoriesequipement> tableviewEquipement;
    @FXML
    private TableColumn<Categoriesequipement, Integer> idColumn;
    @FXML
    private TableColumn<Categoriesequipement,String> NomColumn;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nomcate"));
        
        CategoriesEquipementCRUD cc = new CategoriesEquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(cc.afficherCategorie()));
    }    

   

    @FXML
    private void CategorieSearch(ActionEvent event) {
    }

    @FXML
    private void CategorieAdd(ActionEvent event) {
    }

    @FXML
    private void CategorieUpdate(ActionEvent event) {
    }

    @FXML
    private void CategorieDelete(ActionEvent event) {
    }
    
}
