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
public class RendezvousTypeUpdateController implements Initializable {

    int id;

    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private Button buttonUpdate;
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
    private Label labelUpdate;
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
    private void rendezvousTypeUpdate(ActionEvent event) {

        RendezvousType t = new RendezvousType(textNom.getText());
        RendezvousTypeCRUD rc = new RendezvousTypeCRUD();
        //System.out.println(salle + " " +type + " " + endat + " " + users);
        rc.update(t, id);

        HistoriqueCRUD hc = new HistoriqueCRUD();
        hc.add(1, "a mis à jours le type de rendez-vous '" + String.valueOf(id) + "'");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setRendezvous(RendezvousType r) {
        id = r.getId();
        textNom.setText(r.getType());

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
            labelUpdate.setText(props.getProperty("labelRendezvousTypeUpdate"));
            labelDescription.setText(props.getProperty("labelRendezvousTypeUpdateDescription"));
            labelName.setText(props.getProperty("columnRendezvousTypeName"));
            buttonUpdate.setText(props.getProperty("buttonUpdate"));
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
