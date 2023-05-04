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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author
 */
public class baseController implements Initializable {

    UserSession session;
    
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
    @FXML
    private Button buttonDisconnect;
    @FXML
    private MenuItem buttonUser;
    @FXML
    private MenuItem buttonUserStats;
    @FXML
    private MenuItem buttonSalle;
    @FXML
    private MenuItem buttonPlannification;
    @FXML
    private MenuItem buttonStock;
    @FXML
    private MenuItem buttonStockCategorie;
    @FXML
    private MenuItem buttonStockStats;
    @FXML
    private MenuItem buttonVehicules;
    @FXML
    private MenuItem buttonVehiculesCategorie;
    @FXML
    private MenuItem buttonVehiculesStats;
    @FXML
    private Label labelUserName;
    @FXML
    private Label labelUserType;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        session = UserSession.getInstace(null);
        if (session != null && session.getUser() != null) {
            labelUserName.setText(session.getUser().toString());
            labelUserType.setText(session.getUser().getRole().toString());
        }
    }

    public void redirectToPage(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/" + name + ".fxml"));
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
    private void handleDisconnection(ActionEvent event) {
        session.cleanUserSession();
        redirectToPage("login");
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
        redirectToPage("CategorieEquipementIndex");
    }

    @FXML
    private void redirectEquipementStats(ActionEvent event) {
        redirectToPage("EquipementStats");
    }

    @FXML
    private void redirectUser(ActionEvent event) {
        redirectToPage("userIndex");
    }

    @FXML
    private void redirectUserStats(ActionEvent event) {
        redirectToPage("userStats");
    }

    @FXML
    private void redirectSalle(ActionEvent event) {
        redirectToPage("SalleIndex");
    }

    @FXML
    private void redirectPlannification(ActionEvent event) {
        redirectToPage("PlannificationIndex");
    }

    @FXML
    private void redirectStock(ActionEvent event) {
        redirectToPage("StockIndex");
    }

    @FXML
    private void redirectStockCategorie(ActionEvent event) {
        redirectToPage("CategorieStockIndex");
    }

    @FXML
    private void redirectStockStats(ActionEvent event) {
        redirectToPage("StockStats");
    }

    @FXML
    private void redirectVehicules(ActionEvent event) {
        redirectToPage("VehiculesIndex");
    }

    @FXML
    private void redirectVehiculesCategorie(ActionEvent event) {
        redirectToPage("CategorieVehiculesIndex");
    }

    @FXML
    private void redirectVehiculesStats(ActionEvent event) {
        redirectToPage("VehiculesStats");
    }

}
