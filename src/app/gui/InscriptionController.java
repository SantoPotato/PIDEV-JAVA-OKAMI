/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import entities.user;
import services.userCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class InscriptionController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfemail;
    @FXML
    private TableView<user> table_user; // modifier le type de la TableView
    @FXML
    private TableColumn<user, String> tbnom; // préciser le type des colonnes
    @FXML
    private TableColumn<user, String> tbprenom;
    @FXML
    private TableColumn<user, String> tbpassword;
    @FXML
    private TableColumn<user, String> tbemail;
    @FXML
    private Button btnvalider;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btndelet;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // configurer les colonnes de la table
        tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tbpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tbemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }    

    @FXML
    private void saveuser(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfid.getText());
            String nom = tfnom.getText();
            String prenom = tfprenom.getText();
            String password = tfpassword.getText();
            String email = tfemail.getText();
            user u = new user(id,nom, prenom,password,email);
            userCRUD pcd = new userCRUD();
            pcd.ajouteruser(nom, prenom, password, email);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Detailwindow.fxml"));
            Parent root = loader.load();
            DetailwindowController dwc = loader.getController();
            dwc.setTextid(""+u.getId());
            dwc.setTextnom(u.getNom());
            dwc.setTextprenom(u.getPrenom());
            dwc.setTextpassword(u.getPassword());
            dwc.setTextemail(u.getEmail());
            tfnom.getScene().setRoot(root);
        } catch (IOException ex) {
             System.out.println("Error: " +ex.getMessage());
        }
    }

    @FXML
    private void afficheruser(ActionEvent event) {
          userCRUD pcd = new userCRUD();
    List<user> list = pcd.getAll();
ArrayList<user> arrayList = new ArrayList<>(list);
ObservableList<user> observableList = FXCollections.observableArrayList(arrayList);//permet de manipuler cette liste 
table_user.setItems(observableList);
    /*  ObservableList<user> observableList = FXCollections.observableArrayList();
    for (user u : liste) {
        observableList.add(u);
    }
    table_user.setItems(observableList);*/
    }

    @FXML
    private void deletuser(ActionEvent event) {
        userCRUD pcd = new userCRUD();
    user u = table_user.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        int userId = u.getId();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous supprimer cette publication ?");//demandent à l'utilisateur de confirmer s'il souhaite supprimer une publication.
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            pcd.deleteuser(userId);
            JOptionPane.showMessageDialog(null, "Publication supprimée !");
        } else {
            JOptionPane.showMessageDialog(null, "Suppression annulée.");
        }
    
        pcd.getAll();
    } else {
        JOptionPane.showMessageDialog(null, "Veuillez sélectionner une publication à supprimer.");
    }
        
    }
    
    
}
