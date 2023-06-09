/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import entities.Rendezvous;
import entities.RendezvousType;
import entities.Salle;
import entities.User;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import jfxtras.scene.control.LocalDateTimeTextField;
import services.HistoriqueCRUD;
import services.RendezvousCRUD;
import utils.ConnectionDB;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousUpdateController implements Initializable {

    Connection c;
    int id;

    HistoriqueCRUD hc = new HistoriqueCRUD();
    UserSession session;

    @FXML
    private baseController BaseController;

    @FXML
    private LocalDateTimeTextField dateStart;
    @FXML
    private JFXTimePicker dateEnd;
    @FXML
    private ComboBox<RendezvousType> Type;
    @FXML
    private ComboBox<Salle> Salle;
    @FXML
    private ListView<User> listViewUser;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Label errorDateStart;
    @FXML
    private Label errorType;
    @FXML
    private Label errorSalle;
    @FXML
    private Label errorUsers;
    @FXML
    private MenuButton menuLanguage;
    @FXML
    private MenuItem menuEnglish;
    @FXML
    private MenuItem menuFrench;
    @FXML
    private Label labelUpdate;
    @FXML
    private Label labelDescription;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelDuree;
    @FXML
    private Label labelType;
    @FXML
    private Label labelSalle;
    @FXML
    private Label labelUsers;
    @FXML
    private MenuItem menuJapanese;
    @FXML
    private Label errorDateEnd;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        c = ConnectionDB.getInstance().getConnection();
        listViewUser.setCellFactory(param -> new ListCell<User>() {
            private CheckBox checkBox;

            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (checkBox == null) {
                        checkBox = new CheckBox();
                    }
                    checkBox.setSelected(user.isSelected());
                    checkBox.setOnAction(event -> user.setSelected(checkBox.isSelected()));
                    setText(user.toString());
                    setGraphic(checkBox);
                }
            }
        });

        listViewUser.getItems().addAll(getUsers());
        Salle.setItems(FXCollections.observableArrayList(getSalles()));
        Type.setItems(FXCollections.observableArrayList(getTypes()));

        dateStart.setLocale(Locale.FRENCH);
        dateStart.setDateTimeFormatter(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy à H:mm", Locale.FRENCH));
        dateEnd.set24HourView(true);

        changeLanguage(Locale.getDefault().toString());
    }

    @FXML
    private void rendezvousUpdate(ActionEvent event) {

        int check = 0;

        Salle salle = Salle.getValue();
        RendezvousType type = Type.getValue();
        List<User> users = listViewUser.getItems().filtered(user -> user.isSelected());

        LocalDateTime daterv = null;
        LocalDateTime endat = null;

        if (dateStart.getLocalDateTime() == null) {
            errorDateStart.setText("Une date est requise");
            check++;
        } else {
            daterv = dateStart.getLocalDateTime();

            if (dateEnd.getValue() == null) {
                endat = daterv.plusMinutes(30);
            } else {
                endat = daterv.plusHours(dateEnd.getValue().getHour()).plusMinutes(dateEnd.getValue().getMinute());
            }

            if (daterv.isBefore(LocalDateTime.now())) {
                errorDateStart.setText("Il est impossible d'avoir un rendez-vous dans le passé (mais ce serait sympa quand même)");
                check++;
            } else {
                errorDateStart.setText("");
            }

        }

        if (salle == null) {
            errorSalle.setText("Une salle une requise");
            check++;
        } else {
            if (daterv != null && endat != null && checkDisponibility(salle.getId(), daterv, endat)) {
                errorSalle.setText("Cette salle est déjà occupée lors de cet horaire");
                check++;
            } else {
                errorSalle.setText("");
            }
        }
        if (type == null) {
            errorType.setText("Un type est requis");
            check++;
        } else {
            errorType.setText("");
        }
        if (users.size() < 2) {
            errorUsers.setText("Un rendez-vous nécessite au moins deux membres");
            check++;
        } else {
            errorUsers.setText("");
        }

        if (check > 0) {
            return;
        }

        Rendezvous r = new Rendezvous(daterv, endat, true, salle, type, users);
        RendezvousCRUD rc = new RendezvousCRUD();
        rc.update(r, id);

        if (session != null && session.getUser() != null) {
            hc.add(session.getUser().getId(), "a mis à jours le rendez-vous '" + String.valueOf(id) + "'");
        }

        BaseController.redirectToPage("RendezvousIndex");
    }

    private Boolean checkDisponibility(Integer Salle_id, LocalDateTime start, LocalDateTime end) {
        try {
            String query = "SELECT * FROM rendezvous WHERE Salle=? AND daterv BETWEEN ? AND ?;";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, Salle_id);
            statement.setTimestamp(2, Timestamp.valueOf(start));
            statement.setTimestamp(3, Timestamp.valueOf(end));
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void setRendezvous(Rendezvous r) {
        id = r.getId();
        dateStart.setLocalDateTime(r.getDaterv());
        dateEnd.setValue(r.getEndAt().minusHours(r.getDaterv().getHour()).minusMinutes(r.getDaterv().getMinute()).toLocalTime());
        Type.setValue(r.getType());
        Salle.setValue(r.getSalle());
        r.getUserCollection().forEach((user) -> {
            listViewUser.getItems().stream().filter((item) -> (item.getId().equals(user.getId()))).forEachOrdered((item) -> {
                item.setSelected(true);
            });
        });

    }

    private List<User> getUsers() {

        List<User> data = new ArrayList<>();

        try {

            String query = "SELECT * FROM user";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                data.add(new User(
                        set.getInt("id"), set.getString("nom"), set.getString("prenom")
                ));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

    private List<Salle> getSalles() {

        List<Salle> data = new ArrayList<>();

        try {

            String query = "SELECT * FROM salle";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                data.add(new Salle(
                        set.getInt("id"), set.getInt("numsa"), set.getInt("etagesa"), set.getString("typesa")
                ));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

    private List<RendezvousType> getTypes() {

        List<RendezvousType> data = new ArrayList<>();

        try {

            String query = "SELECT * FROM rendezvous_type";
            PreparedStatement statement = c.prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                data.add(new RendezvousType(
                        set.getInt("id"), set.getString("type")
                ));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        BaseController.redirectToPage("RendezvousIndex");
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

            labelUpdate.setText(props.getProperty("labelRendezvousUpdate"));
            labelDescription.setText(props.getProperty("labelRendezvousUpdateDescription"));
            labelDate.setText(props.getProperty("columnRendezvousDateStart"));
            labelDuree.setText(props.getProperty("columnRendezvousDateEnd"));
            labelType.setText(props.getProperty("columnRendezvousDateType"));
            labelSalle.setText(props.getProperty("columnRendezvousSalle"));
            labelUsers.setText(props.getProperty("columnRendezvousUsers"));
            buttonUpdate.setText(props.getProperty("buttonUpdate"));
            menuLanguage.setText(props.getProperty("Language"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
