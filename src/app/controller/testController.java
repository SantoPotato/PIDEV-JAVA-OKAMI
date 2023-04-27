/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author NiggaBruh
 */
public class testController implements Initializable {

    @FXML
    private Label labelTest;
    @FXML
    private baseController baseController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        changeLanguage(Locale.getDefault().toString());
    }    

    @FXML
    protected Properties changeLanguage(String lang) {
        Properties props = baseController.changeLanguage(lang);
        labelTest.setText(props.getProperty("buttonSearch"));
        return props;
    }

        @FXML
    private void changeLanguageEnglish(ActionEvent event) {
        changeLanguage("en");
    }

    @FXML
    private void changeLanguageFrench(ActionEvent event) {
        changeLanguage("fr");
    }

    @FXML
    private void changeLanguageJapanese(ActionEvent event) {
        changeLanguage("jp");
    }
    
}
