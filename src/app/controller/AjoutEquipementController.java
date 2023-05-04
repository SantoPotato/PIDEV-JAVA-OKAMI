/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import entities.Equipement;
import entities.Categoriesequipement;
import services.EquipementCRUD;
import services.CategoriesEquipementCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutEquipementController implements Initializable {

    Connection c;

    @FXML
    private baseController BaseController;

    @FXML
    private TextField nomeq;
    @FXML
    private CheckBox etat;
    @FXML
    private CheckBox Disponible;
    @FXML
    private ComboBox<Categoriesequipement> Categorie;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonAdd;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Categoriesequipement ce = new Categoriesequipement();
        CategoriesEquipementCRUD ct = new CategoriesEquipementCRUD();
        c = ConnectionDB.getInstance().getConnection();
        Categorie.setItems(FXCollections.observableArrayList(ct.afficherCategorie()));

    }

    @FXML
    private void redirectBack(ActionEvent event) {
        BaseController.redirectToPage("EquipementIndex");
    }

    @FXML
    private void equipementAdd(ActionEvent event) {
        String nom = nomeq.getText();
        Boolean etateq = etat.isSelected();
        Boolean dispo = Disponible.isSelected();
        Categoriesequipement type = Categorie.getValue();
        Equipement e = new Equipement(nom, etateq, dispo, type);
        EquipementCRUD ec = new EquipementCRUD();

        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        String ACCOUNT_SID = "AC2d6462eff326ec211eee2f8927df20f6";
        String AUTH_TOKEN = "6da1ecb008696c6e84094b526cb67536";
        String TWILIO_NUMBER = "+15674323540";
        String USER_NUMBER = "+21652953558";
        String messageText = "Your verification code is " + code;

        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

// Send an SMS message to the user
        Message message = Message.creator(
                new PhoneNumber(USER_NUMBER),
                new PhoneNumber(TWILIO_NUMBER),
                messageText
        ).create();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Verification Code");
        dialog.setHeaderText("Please enter the 6-digit verification code sent to your phone:");
        dialog.setContentText("Code:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String codest = result.get();
            ec.ajouterEquipement2(e);
            // do something with the code entered by the user
        }

        BaseController.redirectToPage("EquipementIndex");
    }

}
