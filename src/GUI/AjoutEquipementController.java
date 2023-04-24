/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
    private CheckBox etat;
    @FXML
    private CheckBox Disponible;
    @FXML
    private ComboBox<Categoriesequipement> Categorie;
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;

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
    private void EquipementAdd(ActionEvent event) {
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
        String AUTH_TOKEN = "faf4715bb6360d51301f648384e6b5fa";
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
         
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        try {
            
            Parent root = loader.load();
            buttonAdd.getScene().setRoot(root);   // Change the scene to another one
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

   

}
   
    

