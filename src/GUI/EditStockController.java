/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import stock.entities.Stock;
import stock.entities.Stockcategories;
import stock.services.StockcategoriesCRUD;
import stock.services.StockCRUD;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import stock.utils.MyDB;


/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EditStockController implements Initializable {

  Connection c;
    @FXML
    private Label labelPage;
    @FXML
    private Label labelPath;
    @FXML
    private Label labelIndex;
    @FXML
    private ImageView logo;
    @FXML
    private Button buttonTest;
    @FXML
    private TextField nomeq;
    @FXML
    private TextField nomeq1;
    @FXML
    private DatePicker dat;
    @FXML
    private TextField nomq2;
    
    
    @FXML
    private ChoiceBox<Stockcategories>  catColumn;
    
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    
    
    private List<Stockcategories> types = new ArrayList<>();
    
    
    private int id;
    @FXML
    private Button buttonAdd;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
        
        StockcategoriesCRUD S = new StockcategoriesCRUD();

        types = S.Afficherc();

        // Ajout des noms des types dans la choiceBox
        catColumn.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        catColumn.setConverter(new StringConverter<Stockcategories>() {
            public String toString(Stockcategories type) {
                return type.getTypecat();
            }

            public Stockcategories fromString(String string) {
                return catColumn.getItems().stream().filter(type ->
                        type.getTypecat().equals(string)).findFirst().orElse(null);
            }
        });
    }    
 
    @FXML
    private void stockac(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    void setEquipement(Stock e) throws SQLException {
    nomeq.setText(e.getNomst());
    nomeq1.setText(e.getDescription());
    dat.setValue(e.getDateexpirationst()); // Utilisez la valeur dateexpirationst que vous avez définie précédemment
    catColumn.setValue(e.getStockcat_id());
    nomq2.setText(String.valueOf(e.getQuantites()));
    id = e.getId();   
}
    
   @FXML
private void EquipementEdit(ActionEvent event) throws SQLException {
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
    ec.modifier(e,id);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
    try {
        Parent root = loader.load();
        buttonAdd.getScene().setRoot(root);   // Change the scene to another one
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    
    
    
       
        


          
    
   
            

            
    }


    



   
    
