/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.userCRUD;

/**
 * FXML Controller class
 *
 * @author SONDESH
 */
public class EditeuserController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfemail;
    @FXML
    private Button btnvalider;
      private int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     void setuser(user u) throws SQLException {
    tfnom.setText(u.getNom());
    tfprenom.setText(u.getPrenom());
    tfpassword.setText(u.getPassword());
      tfemail.setText(u.getEmail());
   
    id=u.getId();
}

    @FXML
    private void editeuser(ActionEvent event) {
         String nom = tfnom.getText();
         String prenom = tfprenom.getText();
         String password = tfpassword.getText();
         String email = tfemail.getText();
    user u = new user( id,nom, prenom, password, email,roles);
    userCRUD pcd = new userCRUD();
        
    pcd.updateuser(id, nom, prenom, password, email);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
     try {
            Parent root = loader.load();
            btnvalider.getScene().setRoot(root);   // Change the scene to another one
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
