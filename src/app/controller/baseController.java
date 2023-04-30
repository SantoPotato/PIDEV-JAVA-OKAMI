/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author
 */
public class baseController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private MenuItem buttonRendezvousStatistique;
    @FXML
    private MenuItem buttonRendezvousCalendrier;
    @FXML
    private MenuItem buttonEquipementIndex;
    @FXML
    private MenuItem buttonEquipementCategorie;
    @FXML
    private MenuItem buttonEquipementStats;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void redirectToPage(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/" + name + ".fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void renameMenuItems(Properties props) {
        buttonRendezvous.setText(props.getProperty("menuRendezvous"));
        buttonRendezvousType.setText(props.getProperty("menuRendezvousType"));
        buttonRendezvousStatistique.setText(props.getProperty("menuStats"));
        buttonRendezvousCalendrier.setText(props.getProperty("menuCalendar"));
    }

    @FXML
    private void redirectIndex(ActionEvent event) {
        redirectToPage("Index");
    }

    @FXML
    private void redirectRendezvous(ActionEvent event) {
        redirectToPage("RendezvousIndex");
    }

    @FXML
    private void redirectRendezvousType(ActionEvent event) {
        redirectToPage("RendezvousTypeIndex");
    }

    @FXML
    private void redirectRendezvousStatistique(ActionEvent event) {
        redirectToPage("RendezvousStats");
    }

    @FXML
    private void redirectRendezvousCalendrier(ActionEvent event) {
        redirectToPage("RendezvousCalendar");
    }

    @FXML
    private void redirectEquipement(ActionEvent event) {
        redirectToPage("EquipementIndex");
    }

    @FXML
    private void redirectEquipementCategorie(ActionEvent event) {
        redirectToPage("CategorieIndex");
    }

    @FXML
    private void redirectEquipementStats(ActionEvent event) {
        redirectToPage("EquipementStats");
    }

}
