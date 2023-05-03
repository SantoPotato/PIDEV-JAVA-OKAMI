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
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.Categorievehicules;
import services.VehiculescategoriesCRUD;
import javafx.scene.control.TextField;
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutcategorieControllervehicules implements Initializable {

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
    private Button buttonAdd;
   @FXML
    private TextField categorie;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         c = MyDB.getInstance().getCnx();
        
        
        
        
        
    }

@FXML
    private void stockac(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/CategorieIndexvehicules.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
@FXML
private void StockAdd(ActionEvent event) {
    String typecatv = categorie.getText();
    
    Categorievehicules e = new Categorievehicules();
    VehiculescategoriesCRUD ec = new VehiculescategoriesCRUD();
    e.setTypecatv(typecatv);
    
    ec.Ajouterc(e);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/CategorieIndexvehicules.fxml"));
    try {
        Parent root = loader.load();
        buttonAdd.getScene().setRoot(root);   // Change the scene to another one
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}

}

   
    


    
    

