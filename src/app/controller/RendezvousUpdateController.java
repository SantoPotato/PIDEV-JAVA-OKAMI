/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import entities.Rendezvous;
import entities.RendezvousType;
import entities.Salle;
import entities.User;
import services.RendezvousCRUD;
import utils.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author ilyes
 */
public class RendezvousUpdateController implements Initializable {

    Connection c;
    int id;
    
    @FXML
    private Label labelPage;
    @FXML
    private Label labelPath;
    @FXML
    private Label labelIndex;
    @FXML
    private ImageView logo;
    @FXML
    private Button buttonRendezvous;
    @FXML
    private Button buttonRendezvousType;
    @FXML
    private Button buttonTest;
    @FXML
    private Button buttonUpdate;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private ComboBox<RendezvousType> Type;
    @FXML
    private ComboBox<Salle> Salle;
    @FXML
    private ListView<User> listViewUser;

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

        listViewUser.getItems().addAll(getUsers(c));
        Salle.setItems(FXCollections.observableArrayList(getSalles(c)));
        Type.setItems(FXCollections.observableArrayList(getTypes(c)));

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

    @FXML
    private void rendezvousUpdate(ActionEvent event) {
        Date daterv = java.sql.Date.valueOf(dateStart.getValue());
        Date endat = java.sql.Date.valueOf(dateEnd.getValue());

        Salle salle = Salle.getValue();
        RendezvousType type = Type.getValue();
        List<User> users = listViewUser.getItems();

        Rendezvous r = new Rendezvous(daterv, endat, true, salle, type, users);
        RendezvousCRUD rc = new RendezvousCRUD();
        //System.out.println(salle + " " +type + " " + endat + " " + users);
        rc.update(r, id);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousIndex.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setRendezvous(Rendezvous r) {
        id = r.getId();
        dateStart.setValue(r.getDaterv().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dateEnd.setValue(r.getEndAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Type.setValue(r.getType());
        Salle.setValue(r.getSalle());
        r.getUserCollection().forEach((user) -> {
            listViewUser.getItems().stream().filter((item) -> (item.toString().equals(user.toString()))).forEachOrdered((item) -> {
                item.setSelected(true);
            });
        });

    }

    
    private List<User> getUsers(Connection c) {

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

    private List<Salle> getSalles(Connection c) {

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

    private List<RendezvousType> getTypes(Connection c) {

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
    
    
    
}


