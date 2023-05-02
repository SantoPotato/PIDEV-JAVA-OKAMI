/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import entities.Stock;
import entities.Stockcategories;
import services.StockcategoriesCRUD;
import services.StockCRUD;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutStockController implements Initializable {
    
    @FXML
    private baseController BaseController;
    
    Connection c;
    private Label labelIndex;
    @FXML
    private Button buttonAdd;
    @FXML
    private TextField nomeq;
    @FXML
    private TextField nomeq1;
    @FXML
    private DatePicker dat;
    
    @FXML
    
    private ChoiceBox<Stockcategories> catColumn;
    private TextField nomeq2;
    
    @FXML
    private TextField nomq2;
    private List<Stockcategories> types = new ArrayList<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        c = ConnectionDB.getInstance().getConnection();
        
        StockcategoriesCRUD S = new StockcategoriesCRUD();
        
        types = S.Afficherc();

        // Ajout des noms des types dans la choiceBox
        catColumn.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        catColumn.setConverter(new StringConverter<Stockcategories>() {
            @Override
            public String toString(Stockcategories type) {
                return type.getTypecat();
            }
            
            @Override
            public Stockcategories fromString(String string) {
                return catColumn.getItems().stream().filter(type
                        -> type.getTypecat().equals(string)).findFirst().orElse(null);
            }
        });
        
    }
    
    @FXML
    private void StockAdd(ActionEvent event) {
        String nomst = nomeq.getText();
        String description = nomeq1.getText();
        Date dateexpirationst = Date.valueOf(dat.getValue());
        Stockcategories stockcat_id = catColumn.getSelectionModel().getSelectedItem();
        int quantites = Integer.parseInt(nomq2.getText());
        Stock e = new Stock();
        StockCRUD ec = new StockCRUD();
        e.setNomst(nomst);
        e.setDescription(description);
        e.setDateexpirationst(dateexpirationst.toLocalDate()); // Utilisez la valeur dateexpirationst que vous avez définie précédemment
        e.setStockcat_id(stockcat_id);
        e.setQuantites(quantites);
        ec.Ajouter(e);
        BaseController.redirectToPage("StockIndex");
    }
    
}
