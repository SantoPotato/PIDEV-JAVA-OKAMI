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
import Entities.Equipement;
import Entities.Categoriesequipement;
import Services.EquipementCRUD;
import Services.CategoriesEquipementCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.SQLException;
import javafx.scene.control.TextField;
import utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutEquipementController implements Initializable {

  Connection c;
    
  @FXML
    private TextField nomeq;
  @FXML
    private CheckBox etat;
  @FXML
    private CheckBox Disponible;
  @FXML
    private ComboBox<Categoriesequipement> Categorie;
    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonEquipement;
    @FXML
    private Button buttonCat;
    @FXML
    private Button buttonStatistique;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonAdd;
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Categoriesequipement ce= new Categoriesequipement();
        CategoriesEquipementCRUD ct= new CategoriesEquipementCRUD ();
         c = MyConnection.getInstance().getConn();
        Categorie.setItems(FXCollections.observableArrayList(ct.afficherCategorie()));
       
        
    }    


    @FXML
    private void redirectIndex(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/GUI/Index.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectEquipement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/GUI/EquipementIndex.fxml"));
            buttonEquipement.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectCat(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/GUI/CategorietIndex.fxml"));
            buttonCat.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectStatistique(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/GUI/StatistiqueIndex.fxml"));
            buttonStatistique.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        redirectEquipement(event);
    }

  @FXML
    private void equipementAdd(ActionEvent event) {
        String nom = nomeq.getText();
        Boolean etateq = etat.isSelected();
        Boolean dispo = Disponible.isSelected();
        Categoriesequipement type = Categorie.getValue();
        System.out.println(type + " " );
        Equipement e = new Equipement(nom, etateq, dispo,  type);
        EquipementCRUD ec = new EquipementCRUD();
        System.out.println(e + " " );
        ec.ajouterEquipement2(e);
        String ACCOUNT_SID = "AC2d6462eff326ec211eee2f8927df20f6";
        String AUTH_TOKEN = "87e7b77c190b95c90cd841110549c27d";
        String TWILIO_NUMBER = "+15674323540";
        String USER_NUMBER = "+21652953558";
         
        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

// Send an SMS message to the user
        Message message = Message.creator(
            new PhoneNumber(USER_NUMBER),
            new PhoneNumber(TWILIO_NUMBER),
            "Your equipment "+e.getNomeq()+ " has been added successfully!"
        ).create();

// Print the message SID for debugging purposes
        System.out.println(message.getSid());
         
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/GUI/EquipementIndex.fxml"));
        try {
            
            Parent root = loader.load();
           buttonIndex.getScene().setRoot(root);   // Change the scene to another one
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

   

   

}
   
    

