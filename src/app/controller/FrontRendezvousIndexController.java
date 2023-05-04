/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import entities.Rendezvous;
import entities.RendezvousType;
import entities.Salle;
import entities.User;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.RendezvousCRUD;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class FrontRendezvousIndexController implements Initializable {

    RendezvousCRUD rc = new RendezvousCRUD();
    UserSession session;

    @FXML
    private Label labelIndex;
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
    private Button buttonDelete;

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
        if (session != null && session.getUser() != null) {
            tableviewRendezvous.setItems(FXCollections.observableArrayList(rc.getRendezvousByUser(LocalDateTime.now(), session.getUser().getId())));
        }

        columnDateStart.setCellValueFactory(dateStartRowData -> new SimpleObjectProperty<>(
                dateStartRowData.getValue().getDaterv().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy à H:mm", Locale.FRENCH)))
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

        session = UserSession.getInstace(null);
        if (session != null && session.getUser() != null) {
            labelIndex.setText("Rendez-vous - " + session.getUser());
        }

    }

    @FXML
    private void rendezvousTextSearch(ActionEvent event) {
        tableviewRendezvous.setItems(FXCollections.observableArrayList(rc.searchRendezvous(textSearch.getText())));
    }

    @FXML
    private void rendezvousButtonSearch(ActionEvent event) {
        tableviewRendezvous.setItems(FXCollections.observableArrayList(rc.searchRendezvous(textSearch.getText())));
    }

    @FXML
    private void rendezvousDelete(ActionEvent event) {
        Rendezvous r = tableviewRendezvous.getSelectionModel().getSelectedItem();

        if (r != null && session != null && session.getUser() != null) {
            rc.removeUser(r.getId(), session.getUser().getId());
            tableviewRendezvous.getItems().remove(r); // remove from the tableview
        }
    }

}
