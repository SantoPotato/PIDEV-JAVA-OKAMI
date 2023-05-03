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
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class FrontBaseController implements Initializable {

    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonVehicules;
    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonDisconnect;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void redirectToPage(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/" + name + ".fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvous(ActionEvent event) {
        redirectToPage("FrontRendezvousIndex");
    }

    @FXML
    private void redirectVehicules(ActionEvent event) {
        redirectToPage("FrontVehiculesIndex");
    }

    @FXML
    private void redirectIndex(ActionEvent event) {
        redirectToPage("FrontIndex");
    }

    @FXML
    private void handleDisconnection(ActionEvent event) {
        UserSession userSession = UserSession.getInstace(null);
        userSession.cleanUserSession();
        redirectToPage("login");
    }
    
}
