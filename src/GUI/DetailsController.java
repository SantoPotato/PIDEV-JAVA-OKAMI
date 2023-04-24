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

/**
 * FXML Controller class
 *
 * @author belkn
 */
public class DetailsController implements Initializable {

   
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
    private TableView<Equipement> tableviewEquipement;
    @FXML
    private TableColumn<Equipement, Integer> idColumn;
    @FXML
    private TableColumn<Equipement, String> NomColumn;
    @FXML
    private TableColumn<Equipement, Boolean> EtatColumn;
    @FXML
    private TableColumn<Equipement, Boolean> DispoColumn;
    @FXML
    private TableColumn<Equipement, Categoriesequipement> typeColumn;
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
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nomeq"));
        EtatColumn.setCellValueFactory(new PropertyValueFactory<>("etateq"));
        DispoColumn.setCellValueFactory(new PropertyValueFactory<>("dispoeq"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Categoriesequipement"));
        

        EquipementCRUD ec = new EquipementCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.afficherEquipement()));
        
    }

 @FXML
    private void Categorieequipement(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieIndexController.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void EquipementSearch(ActionEvent event) {
    }

    @FXML
    private void EquipementAdd(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutEquipement.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void EquipementUpdate(ActionEvent event) throws SQLException {
           Equipement e = tableviewEquipement.getSelectionModel().getSelectedItem();
           
        if (e != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("EditEquipement.fxml"));

                Parent root = loader.load();
                EditEquipementController c = loader.getController();

                c.setEquipement(e);

                labelIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void EquipementDelete(ActionEvent event) {
        Equipement e =  (Equipement) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            EquipementCRUD ec = new EquipementCRUD();
            ec.supprimerequipement(e.getId());
            String ACCOUNT_SID = "AC2d6462eff326ec211eee2f8927df20f6";
        String AUTH_TOKEN = "faf4715bb6360d51301f648384e6b5fa";
        String TWILIO_NUMBER = "+15674323540";
        String USER_NUMBER = "+21652953558";
       
        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

// Send an SMS message to the user
        Message message = Message.creator(
            new PhoneNumber(USER_NUMBER),
            new PhoneNumber(TWILIO_NUMBER),
            "Your equipment  "+e.getNomeq()+ " has been deleted successfully!"
        ).create();

// Print the message SID for debugging purposes
        System.out.println(message.getSid());
            tableviewEquipement.getItems().remove(e); // remove from the tableview
        }
    }
    
    
}
