package okami.healthherald.app.controller;

/*
 * Property of Okami�
 * Not destined for commercial use
 */
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import okami.healthherald.entities.Rendezvous;
import okami.healthherald.entities.RendezvousType;
import okami.healthherald.entities.Salle;
import okami.healthherald.entities.User;
import okami.healthherald.services.RendezvousCRUD;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousIndexController implements Initializable {

    private ListView<User> listViewTest;
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
    private TableView<Rendezvous> tableviewRendezvous;
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
    private TableColumn<Rendezvous, Integer> idColumn;
    @FXML
    private TableColumn<Rendezvous, Date> dateStartColumn;
    @FXML
    private TableColumn<Rendezvous, Date> dateEndColumn;
    @FXML
    private TableColumn<Rendezvous, Salle> salleColumn;
    @FXML
    private TableColumn<Rendezvous, RendezvousType> typeColumn;
    @FXML
    private TableColumn<Rendezvous, String> listUsersColumn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dateStartColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        dateEndColumn.setCellValueFactory(new PropertyValueFactory<>("Durée"));
        salleColumn.setCellValueFactory(new PropertyValueFactory<>("Salle"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        listUsersColumn.setCellValueFactory(new PropertyValueFactory<>("Membres"));

        RendezvousCRUD rc = new RendezvousCRUD();
        tableviewRendezvous.setItems(FXCollections.observableArrayList(rc.showAll()));

        dateStartColumn.setCellValueFactory(dateStartRowData -> new SimpleObjectProperty<>(dateStartRowData.getValue().getDaterv()));
        dateEndColumn.setCellValueFactory(dateEndRowData -> new SimpleObjectProperty<>(dateEndRowData.getValue().getEndAt()));

        listUsersColumn.setCellValueFactory(cellData -> {
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
    private void rendezvousSearch(ActionEvent event) {
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
            labelIndex.getScene().setRoot(loader.load());

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

                labelIndex.getScene().setRoot(root);

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
    private void redirectRendezvous(ActionEvent event) {
    }

    @FXML
    private void redirectRendezvousType(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeIndex.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectTest(ActionEvent event) {
    }

}
