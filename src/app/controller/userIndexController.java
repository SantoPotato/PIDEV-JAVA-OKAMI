/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceUser;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class userIndexController implements Initializable {

    @FXML
    private baseController BaseController;

    @FXML
    private TableView<User> afficher;
    @FXML
    private TableColumn<User, String> first_nameColumn;
    @FXML
    private TableColumn<User, String> last_nameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phone_numberColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TextField Recherche_User;
    @FXML
    private TableColumn<?, ?> tvUsers;
    @FXML
    private Button btnsearch;
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
        ServiceUser userService = new ServiceUser();
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_numberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role_id"));

        List<User> userList = userService.getAll();
        afficher.getItems().setAll(userList);
    }

    @FXML
    private void searchuser(ActionEvent event) {
        String emailSearch = Recherche_User.getText().trim().toLowerCase();

        if (emailSearch.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez saisir un email à rechercher");
            alert.showAndWait();
            return;
        }

        // Get the existing table data and create a filtered list
        ObservableList<User> userList = afficher.getItems();
        FilteredList<User> filteredData = new FilteredList<>(userList, u -> true);

        // Apply a filter to the filtered list to only show rows where the gender column matches the search value
        filteredData.setPredicate(user -> {
            String email = user.getEmail().toLowerCase();
            return email.equals(emailSearch);
        });

        if (filteredData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Aucun utilisateur ne correspond à cet email");
            alert.showAndWait();
            return;
        }

        // Convert the filtered list to a sorted list and set it as the new items list for the table view
        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(afficher.comparatorProperty());
        afficher.setItems(sortedData);

    }

    @FXML
    private void deleteUser(ActionEvent event) {
        User u = afficher.getSelectionModel().getSelectedItem();
        ServiceUser userService = new ServiceUser();
        if (u != null) {
            try {
                userService.supprimer(u.getId());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            afficher.getItems().remove(u); // remove from the tableview
        }
    }
}
