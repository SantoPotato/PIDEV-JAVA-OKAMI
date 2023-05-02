/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

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
     *
     * @param url
     * @param rb
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login.fxml"));
            btnConfirmerCode.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setData(String data) {
        this.code = data;

    }

    private void loadResetPasswordPage(ActionEvent event, String data) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ResetPassword.fxml"));

            Parent root = loader.load();
            ResetPasswordController resetPasswordController = loader.getController();
            resetPasswordController.setData(data);

            btnConfirmerCode.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
