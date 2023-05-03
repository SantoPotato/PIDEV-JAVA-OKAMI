/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import services.ServiceUser;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class ResetPasswordController implements Initializable {
    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfConfirm;
    @FXML
    private Button btnReset;
    String code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnResetAction(ActionEvent event) {
        //code vérifie si les deux champs ne sont pas vides et correspondent l'un à l'autre. Si c'est le cas,
        //il appelle une méthode "ResetPassword" de la classe
        Alert A = new Alert(Alert.AlertType.INFORMATION);
        if (!tfPassword.getText().equals("") && tfPassword.getText().equals(tfConfirm.getText())) {
            ServiceUser su = new ServiceUser();
            su.ResetPaswword(ForgetPasswordController.EmailReset, tfPassword.getText());
            A.setContentText("Mot de passe modifié avec succes ! ");
            A.show();
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        } else {
            A.setContentText("veuillez saisir un mot de passe conforme !");
            A.show();
        }

        
        
        
        
        
    }

    @FXML
    private void btnAnnulerResetAction(ActionEvent event) {
    }
  
    
  
  
          
   public void setData(String data) {
      //  System.out.println("this function was called"+data);
      
      this.code = data;
        
    } 
    
    
    
    
    
    
    
    
    
    
}
