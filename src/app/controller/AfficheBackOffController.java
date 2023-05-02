/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import services.ServiceUser;
import utils.ConnectionUtils;
import utils.UserSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Collections;
import java.util.Comparator;
/**
 * FXML Controller class
 *
 * @author abirk
 */
public class AfficheBackOffController implements Initializable {

    @FXML
    private TableView<User> afficher;
    @FXML
    private TableColumn<User, String> id_userColumn;
    @FXML
    private TableColumn<User, String> first_nameColumn;
    @FXML
    private TableColumn<User, String> last_nameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phone_numberColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private Button btnLogout;
    //private ComboBox<?> changer_id;
    @FXML
    private TextField Recherche_User;
    @FXML
    private TableColumn<?, ?> tvUsers;
    @FXML
    private Button btnsearch;
    @FXML
    private Button tffilter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //User u=su.afficher(6);
        //User u=su.getALL();
        //User u=su.afficher("abir",);
        ServiceUser userService = new ServiceUser();
        //changer_id.setPromptText("changer type utilisateur");
        id_userColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_numberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // récupère les données des utilisateurs depuis la base de données
        List<User> userList = userService.getAll();
// affiche les données dans le tableau
       afficher.getItems().setAll(userList);
        afficher.getColumns().addAll(id_userColumn, first_nameColumn, last_nameColumn, usernameColumn, passwordColumn, emailColumn, phone_numberColumn, genderColumn, roleColumn);
    }

    @FXML
    private void handleLogoutButtonClick(ActionEvent event) {

        UserSession userSession = UserSession.getInstace(null);
        userSession.cleanUserSession();

        //redirect to captcha page
        String pageName = "../gui/login.fxml";
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
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
    private void filtrer(ActionEvent event) {
       try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/stat.fxml"));
           afficher.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
