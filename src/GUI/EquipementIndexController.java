/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Equipement;
import Entities.Categoriesequipement;
import Services.EquipementCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author belkn
 */
public class EquipementIndexController implements Initializable {

  
    @FXML
    private Button buttonEquipement;
    @FXML
    private TableView<Equipement> tableviewEquipement;
    
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
    private Button buttonCat;
    @FXML
    private Button buttonStatistique;
    @FXML
    private TableColumn<Equipement, String> columnNom;
    @FXML
    private TableColumn<Equipement, Boolean> columnEtat;
    @FXML
    private TableColumn<Equipement, Boolean> columnDispo;
    @FXML
    private TableColumn<Equipement, Categoriesequipement> columnCat;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nomeq"));
        columnEtat.setCellValueFactory(new PropertyValueFactory<>("etateq"));
        columnDispo.setCellValueFactory(new PropertyValueFactory<>("dispoeq"));
        columnCat.setCellValueFactory(new PropertyValueFactory<>("Categoriesequipement"));
        

        EquipementCRUD ec = new EquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.afficherEquipement()));
        
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
    private void redirectCat(ActionEvent event) {
  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieIndex.fxml"));
            buttonCat.getScene().setRoot(loader.load());
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
         EquipementCRUD ec = new EquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList( ec.searchEquipement( textSearch.getText() ) ));
    }

    @FXML
    private void equipementButtonSearch(ActionEvent event) {
         EquipementCRUD ec = new EquipementCRUD();
       tableviewEquipement.setItems(FXCollections.observableArrayList( ec.searchEquipement( textSearch.getText() ) ));
    }

    @FXML
    private void equipementAdd(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutEquipement.fxml"));
            buttonAdd.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void equipementUpdate(ActionEvent event) throws SQLException {
        Equipement e = tableviewEquipement.getSelectionModel().getSelectedItem();
           
        if (e != null) {
            
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("EditEquipement.fxml"));

                Parent root = loader.load();
                EditEquipementController c = loader.getController();

                c.setEquipement(e);

                buttonUpdate.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void equipementDelete(ActionEvent event) {
        Equipement e =  (Equipement) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            EquipementCRUD ec = new EquipementCRUD();
            ec.supprimerequipement(e.getId());
            tableviewEquipement.getItems().remove(e);
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
        
        }
    }

    @FXML
    private void redirectEquipement(ActionEvent event) {
    }
    
    
}
