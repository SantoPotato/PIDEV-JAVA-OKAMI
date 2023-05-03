/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import entities.user;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.userCRUD;

/**
 * FXML Controller class
 *
 * @author SONDESH
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button btnsignin;
    @FXML
    private Hyperlink suLink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

@FXML
private void loginuser(ActionEvent event) {
    userCRUD userCrud = new userCRUD();
    String email = this.email.getText();
    String password = this.password.getText();
    user u = userCrud.getUser(email, password);
    if (u != null) {
        if (u.getRoles().equals("admin")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("front.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText(null);
        alert.setContentText("Email ou mot de passe incorrect");
        alert.showAndWait();
    }
}
   /* @FXML
    private void loginuser(ActionEvent event) {
            userCRUD userCrud = new userCRUD();
    String email = this.email.getText();
    String password = this.password.getText();
     boolean isAuth = userCrud.login(password, email);
     if (isAuth) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
} else {
    // Afficher un message d'erreur ou une notification indiquant que les informations d'identification sont incorrectes
}
   }*/


    @FXML
    private void signUp(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouteruser.fxml"));
           password.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
