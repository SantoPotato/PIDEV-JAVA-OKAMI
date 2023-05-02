/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Categoriesequipement;
import services.CategoriesEquipementCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class AjoutCategorieEquipementController implements Initializable {

    @FXML
    private baseController BaseController;

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonAdd;
    @FXML
    private TextField nomcate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        BaseController.redirectToPage("CategorieEquipementIndex");
    }

    @FXML
    private void cateAdd(ActionEvent event) {
        String nom = nomcate.getText();
        Categoriesequipement ce = new Categoriesequipement(nom);
        CategoriesEquipementCRUD ec = new CategoriesEquipementCRUD();

        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        String ACCOUNT_SID = "AC2d6462eff326ec211eee2f8927df20f6";
        String AUTH_TOKEN = "943632682413b6dc8d29704ba91292a7";
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
            ec.ajouterCategorie2(ce);

        }

        BaseController.redirectToPage("CategorieEquipementIndex");
    }
}
