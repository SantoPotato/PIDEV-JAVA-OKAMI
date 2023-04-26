/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categoriesequipement;
import Services.CategoriesEquipementCRUD;
import Services.EquipementCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    private TextField textSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonStatistique;
    @FXML
    private TableView<Categoriesequipement> tableviewCategorie;
    @FXML
    private TableColumn<Categoriesequipement,String> columnNom;
    @FXML
    private Button buttonEquipement;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomcate"));
       
        CategoriesEquipementCRUD cc = new CategoriesEquipementCRUD();
        tableviewCategorie.setItems(FXCollections.observableArrayList(cc.afficherCategorie()));
    }    

   

    @FXML
    private void redirectIndex(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void redirectStatistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
            buttonStatistique.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void equipementTextSearch(ActionEvent event) {
    }

    @FXML
    private void equipementButtonSearch(ActionEvent event) {
    }

    @FXML
    private void CatAdd(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
            buttonAdd.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void CatUpdate(ActionEvent event) throws SQLException {
         Categoriesequipement ce = tableviewCategorie.getSelectionModel().getSelectedItem();
           
        if (ce != null) {
            
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("EditCategorie.fxml"));

                Parent root = loader.load();
                EditCategorieController c = loader.getController();

                c.setCategorie(ce);

                buttonUpdate.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void CatDelete(ActionEvent event) {
         Categoriesequipement ce =  (Categoriesequipement) tableviewCategorie.getSelectionModel().getSelectedItem();

        if (ce != null) {
            CategoriesEquipementCRUD cc = new CategoriesEquipementCRUD();
            cc.supprimerCategorie(ce.getId());
            tableviewCategorie.getItems().remove(ce);
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
            "Your categorie "+ce.getNomcate()+ " has been added successfully!"
        ).create();  

// Print the message SID for debugging purposes
        System.out.println(message.getSid());// remove from the tableview
        }
    }

    @FXML
    private void redirectEquipement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipementIndex.fxml"));
            buttonEquipement.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

    
    

