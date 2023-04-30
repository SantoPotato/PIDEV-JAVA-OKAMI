/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import static app.controller.ForgetPasswordController.code;
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
public class VerifCodeController implements Initializable {

    @FXML
    private com.gluonhq.charm.glisten.control.TextField tfCode;
    @FXML
    private Button btnConfirmerCode;
    String code;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnConfirmerCodeAction(ActionEvent event) {

        if (Integer.parseInt(tfCode.getText()) == ForgetPasswordController.code) {
            loadResetPasswordPage(event, code);
        } else {
            Alert A = new Alert(Alert.AlertType.WARNING);
            A.setContentText("Code erroné ! ");
            A.show();

        }

    }

    @FXML
    private void btnAnnulerCode(ActionEvent event) {
    }

    
  public void setData(String data) {
      //  System.out.println("this function was called"+data);
      
       this.code = data;
        
    } 
    
   @FXML
    private void loadResetPasswordPage(ActionEvent event, String data) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ResetPassword.fxml"));
            Parent page1
                   = loader.load();
            ResetPasswordController resetPasswordController= loader.getController();
            resetPasswordController.setData(data);
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) { 
        System.out.println(ex);
        } 
        
        
    } 
    
}
