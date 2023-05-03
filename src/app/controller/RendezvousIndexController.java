package app.controller;

/*
 * Property of Okami�
 * Not destined for commercial use
 */
import utils.HistoriqueMenuItem;
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
import java.util.Locale;
import java.util.Properties;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.HistoriqueCRUD;
import services.RendezvousCRUD;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousIndexController implements Initializable {

    RendezvousCRUD rc = new RendezvousCRUD();
    HistoriqueCRUD hc = new HistoriqueCRUD();
    UserSession session;

    @FXML
    private baseController BaseController;

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
    private MenuButton historique;
    @FXML
    private MenuButton menuLanguage;
    @FXML
    private MenuItem menuEnglish;
    @FXML
    private MenuItem menuFrench;
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

        columnDateStart.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnDateEnd.setCellValueFactory(new PropertyValueFactory<>("Durée"));
        columnSalle.setCellValueFactory(new PropertyValueFactory<>("Salle"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        columnUsers.setCellValueFactory(new PropertyValueFactory<>("Membres"));
        tableviewRendezvous.setItems(FXCollections.observableArrayList(rc.showAll()));

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

        hc.showAll().forEach(item -> {
            historique.getItems().add(new HistoriqueMenuItem(item));
        });

        session = UserSession.getInstace(null);

        changeLanguage(Locale.getDefault().toString());

    }

    @FXML
    private void rendezvousAdd(ActionEvent event) {
        BaseController.redirectToPage("RendezvousAdd");
    }

    @FXML
    private void rendezvousUpdate(ActionEvent event) {
        Rendezvous r = tableviewRendezvous.getSelectionModel().getSelectedItem();

        if (r != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/RendezvousUpdate.fxml"));

                Parent root = loader.load();
                RendezvousUpdateController c = loader.getController();

                c.setRendezvous(r);

                tableviewRendezvous.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    @FXML
    private void rendezvousDelete(ActionEvent event) {
        Rendezvous r = tableviewRendezvous.getSelectionModel().getSelectedItem();

        if (r != null) {
            rc.remove(r.getId());
            tableviewRendezvous.getItems().remove(r); // remove from the tableview
            if (session != null && session.getUser() != null) {
                hc.add(session.getUser().getId_user(), "a supprimé le rendez-vous '" + String.valueOf(r.getId()) + "'");
                hc.showAll().forEach(item -> {
                    historique.getItems().add(new HistoriqueMenuItem(item));
                });
            }
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

            columnDateStart.setText(props.getProperty("columnRendezvousDateStart"));
            columnDateEnd.setText(props.getProperty("columnRendezvousDateEnd"));
            columnSalle.setText(props.getProperty("columnRendezvousSalle"));
            columnType.setText(props.getProperty("columnRendezvousType"));
            columnUsers.setText(props.getProperty("columnRendezvousUsers"));
            buttonSearch.setText(props.getProperty("buttonSearch"));
            buttonAdd.setText(props.getProperty("buttonAdd"));
            buttonUpdate.setText(props.getProperty("buttonUpdate"));
            buttonDelete.setText(props.getProperty("buttonDelete"));
            menuLanguage.setText(props.getProperty("Language"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
