/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import stock.entities.Categorievehicules;
import stock.services.VehiculescategoriesCRUD;
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
public class EditcategorieController implements Initializable {

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
    private TextField categorie;
    
    
    
    
    
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    
    
    private List<Categorievehicules> types = new ArrayList<>();
    
    
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
          
        
        c = MyDB.getInstance().getCnx();
    }    
 
    @FXML
    private void stockac(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieIndex.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    void setEquipements(Categorievehicules e) throws SQLException {
    categorie.setText(e.getTypecatv());
    id = e.getId();
    
}
    
   @FXML
private void CategoriesUpdate(ActionEvent event) throws SQLException {
    String typecat = categorie.getText();
    
    Categorievehicules e = new Categorievehicules();
    VehiculescategoriesCRUD ec = new VehiculescategoriesCRUD();
    e.setTypecatv(typecat);
    
    ec.modifier(e,id);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieIndex.fxml"));
    try {
        Parent root = loader.load();
        buttonAdd.getScene().setRoot(root);   // Change the scene to another one
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}


    
    
    
    
       
        


          
    
   
            

            
    }


    



   
    


