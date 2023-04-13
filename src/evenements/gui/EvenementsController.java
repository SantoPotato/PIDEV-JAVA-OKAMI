/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenements.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EvenementsController implements Initializable {

    @FXML
    private TableView<?> tableEvenements;
    @FXML
    private TableColumn<?, ?> colIdcat;
    @FXML
    private TableColumn<?, ?> colcate;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btninscription;
    @FXML
    private TextField tftypecatv;
    @FXML
    private Button btnenvoyer;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherEvenementsSelected(MouseEvent event) {
    }

    @FXML
    private void editeven(ActionEvent event) {
    }

    @FXML
    private void deleteeven(ActionEvent event) {
    }

    @FXML
    private void inscription(ActionEvent event) {
    }

    @FXML
    private void addevn(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }
    
}
