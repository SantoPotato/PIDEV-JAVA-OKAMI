/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import okami.healthherald.entities.RendezvousType;
import okami.healthherald.services.RendezvousTypeCRUD;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousTypeAddController implements Initializable {

    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonIndex;
    @FXML
    private TextField textNom;

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
        //System.out.println(salle + " " +type + " " + endat + " " + users);
        rc.add(t);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
