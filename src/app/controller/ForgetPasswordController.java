/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import utils.MailerAPI;
import services.ControleSaisieTextFields;
import services.ServiceUser;
import utils.SendMail;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class ForgetPasswordController implements Initializable {

    public static int code;
    public static String EmailReset;
    @FXML
    private Button handleButtonReturn;
    @FXML
    private Button BtnCode;
    @FXML
    private TextField tfMailO;

    ControleSaisieTextFields cs;

    private int generateVerificationCode() {
        // Générer un code de vérification aléatoire à 6 chiffres
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonReturn(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("../gui/login.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void btnCodeAction(ActionEvent event) {
        code = generateVerificationCode();
        Alert A = new Alert(Alert.AlertType.WARNING);
        ServiceUser su = new ServiceUser();

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean verifMail = tfMailO.getText().matches(emailRegex);

        if (!tfMailO.getText().equals("") && verifMail) {
            if (su.ChercherMail(tfMailO.getText()) == 1) {
                EmailReset = tfMailO.getText();
                SendMail.send(tfMailO.getText(), "Verification code", "Votre code est : " + code);

                loadVerifCodePage(event, Integer.toString(code));
            } else {
                A.setContentText("pas de compte lié avec cette adresse ! ");
                A.show();
            }
        } else {
            A.setContentText("Veuillez saisir une adresse mail valide ! ");
            A.show();
        }
    }
    
    
    
    
    @FXML
    private void loadVerifCodePage(ActionEvent event, String data) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/VerifCode.fxml"));
            Parent page2
                   = loader.load();
            VerifCodeController verifCodeController= loader.getController();
            verifCodeController.setData(data);
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) { 
       
        } 
        
       
  
          
    }

}