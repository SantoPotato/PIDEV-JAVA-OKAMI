/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import utils.MailerAPI;
import services.AuthentificationService;
import utils.SendMail;
import static com.sun.javafx.animation.TickCalculation.sub;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
import static java.lang.ProcessBuilder.Redirect.to;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Transport;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authentificationService = new AuthentificationService();
        // TODO

    }

    // public void envoyerSMS(){
    //Twilio.init("ACe3140c7406949824edc08d44bddf6e51", "445fce8e72df408d1e7b8543df2071bd");
    //Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    // Message message = Message.creator(
    //new com.twilio.type.PhoneNumber(input_phone_number.getText()),
    // new com.twilio.type.PhoneNumber("+15124003293"),
    // "Votre paiement a été reçu avec succés sous l'addresse mail "+input_lastName.getText()+", Merci pour votre générosité")
    //.create();
    // System.out.println(message.getSid());
    //}
    /*  String user = "abir.khlifi@esprit.tn";
            String pass = "esprit20202021 ";
            String to = "abir.khlifi@esprit.tn";  */
    @FXML
    public void handleSignupButtonClick(MouseEvent event) throws AddressException, MessagingException {
        String first_name = input_firstName.getText();
        String last_name = input_lastName.getText();
        String username = input_username.getText();
        //String username = input_username.getText();
        String email = input_email.getText();
        String password = input_password.getText();
        String gender = input_gender.getText();

        System.out.println("Préparation de l envoie du mail");
           // String host = "smtp.gmail.com";
           // String user = "abir.khlifi@esprit.tn";
           // String pass = "chle9emesprit20202021";
           // String to = "abir.khlifi@esprit.tn";

        //configuration
        SendMail sm = new SendMail();
        String emailSubject = "Account creation";
        String emailMessage = "votre compte a été ajoute !";

        sm.send(email, emailSubject, emailMessage);

        //  InputValidation.notificationsucces("succés", "le mail a été envoyer avec succés");
        //String phone_number = input_phone_number.getText();
        // String password_check= input_password_check.getText();
        authentificationService.signup(first_name, last_name, username, email, password, gender);
        /*         SendMail m = new SendMail();
        String subject = "Félicitations!";
        String message = "Vous etes inscrit sur notre platforme ! Bienvenue ";
                m.sendMail("mohamedaziz.mhatli@esprit.tn", "Demande Approuvé", "approuvée");
        String UN ="mohamedaziz.mhatli@esprit.tn";
           String PW = "213JMT4852";  
           String mto = "mohamedaziz.mhatli@gmail.com";
           String msub = "Nouvelle user";
           String cTEXT ="Le Client ,  a ajouter une nouvelle user ";
           MailerAPI.Mail(UN, PW, mto, msub, cTEXT);
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
         */
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
    //String gender = input_gender.getText();
   // if (!input_gender.getText="femme" || !gender = "homme") {
        
   // }
    
    }

    @FXML
    private void handleLoginButtonClick(ActionEvent event) {

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
    private void handleSignupButtonClick(ActionEvent event) {
    }
}