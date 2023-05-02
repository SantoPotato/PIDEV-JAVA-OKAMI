/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import utils.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class CaptchaController implements Initializable {

    @FXML
    private Label captchahere;
    @FXML
    private Label messagebtn;
    @FXML
    private TextField textarea;
    @FXML
    private Button checkbtn;
    @FXML
    private Button newcaptchabtn;
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
        // TODO
    }
// vérifie si la saisie de l'utilisateur correspond au captcha généré précédemment.

    @FXML
    private void captchachecker(ActionEvent event) {
        String userinput = textarea.getText();
        if (userinput.equals(String.valueOf(captchahere.getText()))) {
            if (!(userinput.equals("captcha"))) {
                messagebtn.setText("Succés");
                messagebtn.setTextFill(Color.GREEN);

                UserSession userSession = UserSession.getInstace(null);
                if (userSession.getUser().getRole().equals("ADMIN")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/index.fxml"));
                        btnLogin.getScene().setRoot(loader.load());

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            } else {
                messagebtn.setText("Veillez cliquer sur un nouveau Captcha");
                messagebtn.setTextFill(Color.RED);
            }
        } else {
            messagebtn.setText("Code erroné");
            messagebtn.setTextFill(Color.RED);
        }
    }

    ////est appelée lorsqu'un utilisateur souhaite générer un nouveau captcha.
    //Elle utilise un tableau de caractères contenant des lettres majuscules et minuscules, 
    @FXML
    private void setcaptcha(ActionEvent event) {

        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int max = 61;
        String captchastring = "";
        int min = 0;
        for (int i = 0; i < 6; i++) {
            int randomnumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
            captchastring += data[randomnumber];
        }
        captchahere.setText(captchastring);
        captchahere.setTextFill(Color.GREEN);
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

}
