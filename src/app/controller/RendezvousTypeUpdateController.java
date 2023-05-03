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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.RendezvousType;
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
    private baseController BaseController;
    
    @FXML
    private Button buttonUpdate;
    @FXML
    private TextField textNom;
    @FXML
    private Button buttonBack;
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
    private void rendezvousTypeUpdate(ActionEvent event) {

        RendezvousType t = new RendezvousType(textNom.getText());
        RendezvousTypeCRUD rc = new RendezvousTypeCRUD();
        //System.out.println(salle + " " +type + " " + endat + " " + users);
        rc.update(t, id);

        HistoriqueCRUD hc = new HistoriqueCRUD();
        hc.add(1, "a mis à jours le type de rendez-vous '" + String.valueOf(id) + "'");

        BaseController.redirectToPage("RendezvousTypeIndex");
    }

    public void setRendezvous(RendezvousType r) {
        id = r.getId();
        textNom.setText(r.getType());

    }

    @FXML
    private void redirectBack(ActionEvent event) {
        BaseController.redirectToPage("RendezvousTypeIndex");
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
            props.load(getClass().getClassLoader().getResourceAsStream("app/localisation/ui_" + lang + ".properties"));
            BaseController.renameMenuItems(props);
            
            labelUpdate.setText(props.getProperty("labelRendezvousTypeUpdate"));
            labelDescription.setText(props.getProperty("labelRendezvousTypeUpdateDescription"));
            labelName.setText(props.getProperty("columnRendezvousTypeName"));
            buttonUpdate.setText(props.getProperty("buttonUpdate"));
            menuLanguage.setText(props.getProperty("Language"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
