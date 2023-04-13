/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import okami.healthherald.entities.RendezvousType;
import okami.healthherald.services.RendezvousTypeCRUD;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousTypeIndexController implements Initializable {

    @FXML
    private Label labelPage;
    @FXML
    private Label labelPath;
    @FXML
    private Label labelIndex;
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    @FXML
    private Button buttonTest;
    @FXML
    private TableView<RendezvousType> tableviewRendezvousType;
    @FXML
    private TableColumn<RendezvousType, Integer> idColumn;
    @FXML
    private TableColumn<RendezvousType, String> typeColumn;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
        RendezvousTypeCRUD rc = new RendezvousTypeCRUD();
        tableviewRendezvousType.setItems(FXCollections.observableArrayList(rc.showAll()));
        
        typeColumn.setCellValueFactory(typeRowData -> new SimpleObjectProperty<>(typeRowData.getValue().getType()));

    }    

    @FXML
    private void redirectRendezvous(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousIndex.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvousType(ActionEvent event) {
    }

    @FXML
    private void redirectTest(ActionEvent event) {
    }

    @FXML
    private void rendezvousSearch(ActionEvent event) {
    }

    @FXML
    private void rendezvousAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeAdd.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rendezvousUpdate(ActionEvent event) {
               RendezvousType t = tableviewRendezvousType.getSelectionModel().getSelectedItem();

        if (t != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeUpdate.fxml"));

                Parent root = loader.load();
                RendezvousTypeUpdateController c = loader.getController();

                c.setRendezvous(t);

                labelIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void rendezvousDelete(ActionEvent event) {
        RendezvousType t = tableviewRendezvousType.getSelectionModel().getSelectedItem();

        if (t != null) {
            RendezvousTypeCRUD rc = new RendezvousTypeCRUD();
            rc.remove(t.getId());
            tableviewRendezvousType.getItems().remove(t); // remove from the tableview
        }
    }
    
}
