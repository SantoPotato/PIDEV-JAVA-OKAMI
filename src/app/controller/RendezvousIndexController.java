package app.controller;

/*
 * Property of Okami�
 * Not destined for commercial use
 */
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.Rendezvous;
import entities.RendezvousType;
import entities.Salle;
import entities.User;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.MenuItem;
import services.RendezvousCRUD;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousIndexController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private TableView<Rendezvous> tableviewRendezvous;
    @FXML
    private TableColumn<Rendezvous, String> columnDateStart;
    @FXML
    private TableColumn<Rendezvous, String> columnDateEnd;
    @FXML
    private TableColumn<Rendezvous, Salle> columnSalle;
    @FXML
    private TableColumn<Rendezvous, RendezvousType> columnType;
    @FXML
    private TableColumn<Rendezvous, String> columnUsers;
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
    @FXML
    private MenuItem buttonRendezvousStatistique;
    
    RendezvousCRUD rc = new RendezvousCRUD();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        columnDateStart.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnDateEnd.setCellValueFactory(new PropertyValueFactory<>("Durée"));
        columnSalle.setCellValueFactory(new PropertyValueFactory<>("Salle"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        columnUsers.setCellValueFactory(new PropertyValueFactory<>("Membres"));
        tableviewRendezvous.setItems(FXCollections.observableArrayList(rc.showAll()));

        columnDateStart.setCellValueFactory(dateStartRowData -> new SimpleObjectProperty<>(
                dateStartRowData.getValue().getDaterv().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy à h:mm")))
        );
        columnDateEnd.setCellValueFactory(dateEndRowData -> new SimpleObjectProperty<>(dateEndRowData.getValue().showDuree()));

        columnUsers.setCellValueFactory(cellData -> {
            Collection<User> users = cellData.getValue().getUserCollection();
            if (users == null || users.isEmpty()) {
                return new SimpleStringProperty("");
            } else {
                List<String> userStrings = users.stream()
                        .map(User::toString)
                        .collect(Collectors.toList());
                return new SimpleStringProperty(String.join("\n", userStrings));
            }
        });

    }

    @FXML
    private void rendezvousAdd(ActionEvent event) {

        try {
            // Code below switch scenes
            // The second one seems better, I guess, so I'll stick with it

//            Parent root = FXMLLoader.load(getClass().getResource("../gui/RendezvousAdd.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousAdd.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void rendezvousUpdate(ActionEvent event) {
        Rendezvous r = tableviewRendezvous.getSelectionModel().getSelectedItem();

        if (r != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousUpdate.fxml"));

                Parent root = loader.load();
                RendezvousUpdateController c = loader.getController();

                c.setRendezvous(r);

                buttonIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    @FXML
    private void rendezvousDelete(ActionEvent event) {
        Rendezvous r = tableviewRendezvous.getSelectionModel().getSelectedItem();

        if (r != null) {
            RendezvousCRUD rc = new RendezvousCRUD();
            rc.remove(r.getId());
            tableviewRendezvous.getItems().remove(r); // remove from the tableview
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
    private void rendezvousTextSearch(ActionEvent event) {
        tableviewRendezvous.setItems(FXCollections.observableArrayList( rc.searchRendezvous( textSearch.getText() ) ));
    }

    @FXML
    private void rendezvousButtonSearch(ActionEvent event) {
        tableviewRendezvous.setItems(FXCollections.observableArrayList( rc.searchRendezvous( textSearch.getText() ) ));
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

}
