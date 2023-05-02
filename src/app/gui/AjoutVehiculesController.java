/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

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
import app.entities.Vehicules;
import app.entities.Categorievehicules;
import app.services.VehiculescategoriesCRUD;
import app.services.VehiculesCRUD;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import app.utils.MyDB;


/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutVehiculesController implements Initializable {

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
    private TextField nomeq;
    @FXML
    private TextField nomeq1;
    @FXML
    private CheckBox dat;
    
    
    @FXML
    
    
    
    private ChoiceBox<Categorievehicules>  catColumn;
    private TextField nomeq2;
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    
    @FXML
    private TextField nomq2;
    private List<Categorievehicules> types = new ArrayList<>();
    @FXML
    private Button btnretour;
    @FXML
    private TextField nomq3;
    @FXML
    private Button btnchoisir;
    @FXML
    private DatePicker nomq4;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         c = MyDB.getInstance().getCnx();
        
        
        VehiculescategoriesCRUD S = new VehiculescategoriesCRUD();

        types = S.Afficherc();

        // Ajout des noms des types dans la choiceBox
        catColumn.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        catColumn.setConverter(new StringConverter<Categorievehicules>() {
            public String toString(Categorievehicules type) {
                return type.getTypecatv();
            }

            public Categorievehicules fromString(String string) {
                return catColumn.getItems().stream().filter(type ->
                        type.getTypecatv().equals(string)).findFirst().orElse(null);
            }
        });
        
        
    }

@FXML
    private void stockac(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsVehicules.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
@FXML
private void StockAdd(ActionEvent event) {
   Categorievehicules Categoriesvehicules = catColumn.getSelectionModel().getSelectedItem();
    String nomvh = nomeq.getText(); 
    int dispovh = dat.isSelected() ? 1 : 0;
    String etatvh = nomeq1.getText();
     
  
    String descvh =nomq2.getText();
     String imagesvh = nomq3.getText();
       String date = nomq4.getValue().toString();
     
        Vehicules e = new Vehicules(nomvh,dispovh, etatvh, descvh,imagesvh ,date ,Categoriesvehicules);
        VehiculesCRUD ec = new VehiculesCRUD();
        System.out.println(e + " " );
        ec.Ajouter(e);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsVehicules.fxml"));
        try {
            Parent root = loader.load();
            buttonAdd.getScene().setRoot(root);   // Change the scene to another one
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}

    @FXML
    private void btnretour(ActionEvent event) {
         try {
        Parent reclamationsParent = FXMLLoader.load(getClass().getResource("DetailsVehicules.fxml"));
        Scene reclamationsScene = new Scene(reclamationsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(reclamationsScene);
        window.show();
    } catch (IOException e) {
    }
    }





}

   
    

