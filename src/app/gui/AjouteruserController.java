/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;
//X
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.userCRUD;

/**
 * FXML Controller class
 *
 * @author SONDESH
 */
public class AjouteruserController implements Initializable {

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
    @FXML
    private TextField tfroles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveuser(ActionEvent event) {
      try {
        String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String password = tfpassword.getText();
        String email = tfemail.getText();
        String roles = tfroles.getText();

        // Contrôle de saisie pour nom et prénom
        if (!nom.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom ne doit contenir que des caractères alphabétiques.");
            alert.showAndWait();
            return;
        }

        if (!prenom.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prénom ne doit contenir que des caractères alphabétiques.");
            alert.showAndWait();
            return;
        }

        // Contrôle de saisie pour l'email
        if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("L'adresse email n'est pas valide.");
            alert.showAndWait();
            return;
        }

        // Contrôle de saisie pour le mot de passe (8 caractères minimum)
        if (password.length() < 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe doit contenir au moins 8 caractères.");
            alert.showAndWait();
            return;
        }

        user u = new user(nom, prenom, password, email,roles);
        userCRUD pcd = new userCRUD();
        pcd.ajouteruser(nom, prenom, password, email,roles);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Detailwindow.fxml"));
        Parent root = loader.load();
        DetailwindowController dwc = loader.getController();
        dwc.setTextid("" + u.getId());
        dwc.setTextnom(u.getNom());
        dwc.setTextprenom(u.getPrenom());
        dwc.setTextpassword(u.getPassword());
        dwc.setTextemail(u.getEmail());
        tfnom.getScene().setRoot(root);
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    }
    
     
}
