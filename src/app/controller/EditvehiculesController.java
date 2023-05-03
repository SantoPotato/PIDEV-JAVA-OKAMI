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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import entities.Categorievehicules;
import entities.Vehicules;
import services.VehiculesCRUD;
import services.VehiculescategoriesCRUD;


/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EditvehiculesController implements Initializable {

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
    private CheckBox dat;
    @FXML
    private TextField nomq2;
    
    
    @FXML
    private ChoiceBox<Categorievehicules>  catColumn;
    
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    
    
    private List<Categorievehicules> types = new ArrayList<>();
    
    
    private int id;
    private Button buttonAdd;
    @FXML
    private TextField nomq3;
    @FXML
    private Button btnchoisir;
    @FXML
    private DatePicker nomq4;
    @FXML
    private Button buttonAdd1;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
        
        VehiculescategoriesCRUD S = new VehiculescategoriesCRUD();

        types = S.Afficherc();

        // Ajout des noms des types dans la choiceBox
        catColumn.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
          catColumn.setConverter(new StringConverter<Categorievehicules>() {
            @Override
            public String toString(Categorievehicules type) {
                return type.getTypecatv();
            }

            @Override
            public Categorievehicules fromString(String string) {
                return catColumn.getItems().stream().filter(type ->
                        type.getTypecatv().equals(string)).findFirst().orElse(null);
            }
        });
    }    
 
    @FXML
    private void stockac(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/VehiculesIndex.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    void setEquipement(Vehicules e) throws SQLException {
   nomeq.setText(e.getNomvh());
     nomeq1.setText(e.getEtatvh());
       dat.setText(String.valueOf(e.isDispovh()));
         catColumn.setValue(e.getCatv_id());
           nomq2.setText(e.getDescvh());
             id = e.getId(); 
            // datedebut.setValue(d.getAppointment_date().toLocalDate());
      
}
    
    
    @FXML
    private void EquipementEdit(ActionEvent event) throws SQLException {
    Categorievehicules Categoriesvehicules = catColumn.getSelectionModel().getSelectedItem();
    String nomvh = nomeq.getText(); 
    int dispovh = dat.isSelected() ? 1 : 0;
    String etatvh = nomeq1.getText();
     
  
    String descvh =nomq2.getText();
     String imagesvh = nomq3.getText();
       String date = nomq4.getValue().toString();
     
        Vehicules e = new Vehicules(nomvh,dispovh, etatvh, descvh,imagesvh ,date ,Categoriesvehicules);
        VehiculesCRUD ec = new VehiculesCRUD();
    e.setNomvh(nomvh);
    e.setEtatvh(etatvh);
    e.setDispovh(dispovh); // Utilisez la valeur dateexpirationst que vous avez définie précédemment
    
    e.setDescvh(descvh);
    e.setImagesvh(imagesvh);
    e.setDate(date);
    e.setCatv_id(Categoriesvehicules);
    ec.modifier(e,id);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/VehiculesIndex.fxml"));
    try {
        Parent root = loader.load();
        buttonAdd1.getScene().setRoot(root);   // Change the scene to another one
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }}


}
  
    
   
            

            
    


    



   
    

