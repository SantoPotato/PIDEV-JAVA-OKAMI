/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.RendezvousType;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.HistoriqueCRUD;
import services.RendezvousTypeCRUD;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousTypeAddController implements Initializable {

    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonIndex;
    @FXML
    private TextField textNom;
    @FXML
    private MenuItem buttonRendezvousStatistique;
    @FXML
    private Button buttonBack;
    @FXML
    private MenuItem buttonRendezvousCalendrier;
    @FXML
    private MenuButton menuLanguage;
    @FXML
    private MenuItem menuEnglish;
    @FXML
    private MenuItem menuFrench;
    @FXML
    private Label labelAdd;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelName;
    @FXML
    private MenuItem menuJapanese;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeLanguage(Locale.getDefault().toString());
    }

    @FXML
    private void redirectRendezvous(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvousType(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rendezvousTypeAdd(ActionEvent event) {

        RendezvousType t = new RendezvousType(textNom.getText());
        RendezvousTypeCRUD rc = new RendezvousTypeCRUD();
        rc.add(t);

        HistoriqueCRUD hc = new HistoriqueCRUD();
        hc.add(1, "a ajouté un nouveau type de rendez-vous");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvousStatistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousStats.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectIndex(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Index.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        redirectRendezvousType(event);
    }

    @FXML
    private void redirectRendezvousCalendrier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousCalendar.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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

    private void changeLanguage(String lang) {
        Locale.setDefault(new Locale(lang));
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/app/localisation/ui_" + lang + ".properties"));
            labelAdd.setText(props.getProperty("labelRendezvousTypeAdd"));
            labelDescription.setText(props.getProperty("labelRendezvousTypeAddDescription"));
            labelName.setText(props.getProperty("columnRendezvousTypeName"));
            buttonAdd.setText(props.getProperty("buttonAdd"));
            buttonRendezvous.setText(props.getProperty("menuRendezvous"));
            buttonRendezvousType.setText(props.getProperty("menuRendezvousType"));
            buttonRendezvousStatistique.setText(props.getProperty("menuStats"));
            buttonRendezvousCalendrier.setText(props.getProperty("menuCalendar"));
            menuLanguage.setText(props.getProperty("Language"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
