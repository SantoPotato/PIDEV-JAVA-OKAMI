/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import services.AuthentificationService;
import utils.SendMail;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class Signup_pageController implements Initializable {

    public static final String ACCOUNT_SID = "AC652f43806e3fbc03f53fccd5fdaa9212";
    public static final String AUTH_TOKEN = "2b9eb9158e11e5cbaede12228616354b";

    @FXML
    private Button btn_signup;

    AuthentificationService authentificationService = null;
    @FXML
    private TextField input_firstName;
    @FXML
    private TextField input_lastName;
    @FXML
    private TextField input_phone_number;
    @FXML
    private TextField input_gender;
    @FXML
    private TextField input_username;
    @FXML
    private TextField input_password;
    @FXML
    private TextField input_email;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authentificationService = new AuthentificationService();
    }

    @FXML
    private void handleLoginButtonClick(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login.fxml"));
            btnLogin.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleSignupButtonClick(ActionEvent event) {
        String first_name = input_firstName.getText();
        String last_name = input_lastName.getText();
        String username = input_username.getText();
        //String username = input_username.getText();
        String email = input_email.getText();
        String password = input_password.getText();
        String gender = input_gender.getText();

        if (input_firstName.getText().isEmpty() || input_lastName.getText().isEmpty() || input_username.getText().isEmpty()
                || input_email.getText().isEmpty() || input_password.getText().isEmpty()
                || input_gender.getText().isEmpty()) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!input_email.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un email valide !");
            alert.showAndWait();
            return;
        }

        String motdepasse = input_password.getText();
        if (!motdepasse.matches(".*[a-z].*") || !motdepasse.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe doit contenir au moins une lettre majuscule et un chiffre.");
            alert.showAndWait();
            return;
        }

        String num = input_phone_number.getText();
        if (!num.matches("\\d{8}")) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numéro de téléphone invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres exactement !");
            alert.showAndWait();
            return;
        }

        authentificationService.signup(first_name, last_name, username, email, password, gender);
        // String host = "smtp.gmail.com";
        // String user = "abir.khlifi@esprit.tn";
        // String pass = "chle9emesprit20202021";
        // String to = "abir.khlifi@esprit.tn";
        //configuration
        String emailSubject = "Account creation";
        String emailMessage = "votre compte a été ajoute !";

        SendMail.send(email, emailSubject, emailMessage);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compte");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a été créé avec succés.");
        alert.showAndWait();

        handleLoginButtonClick(event);

    }

}
