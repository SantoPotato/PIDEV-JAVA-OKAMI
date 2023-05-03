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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
    @FXML
    private Button buttonupdate;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnsearch;
    @FXML
    private TextField tfsearchname;
    @FXML
    private TextField tfroles;
    @FXML
    private TableColumn<user, String> tbroles;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // configurer les colonnes de la table
        tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tbpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tbemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }   
     private void addButtonToTable() {
        TableColumn<user, Void> BlockBtn = new TableColumn("Block");

        Callback<TableColumn<user, Void>, TableCell<user, Void>> cellFactory = new Callback<TableColumn<user, Void>, TableCell<user, Void>>() {
            @Override
            public TableCell<user, Void> call(final TableColumn<user, Void> param) {
                final TableCell<user, Void> cell = new TableCell<user, Void>() {

                    private final Button btn = new Button("Block");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TableColumn<user, String> firstColumn = (TableColumn<user, String>) getTableView().getColumns().get(0);
                            String email = firstColumn.getCellData(getIndex());
                            System.out.println("selectedData: " + email);
                           try {
                                userCRUD.getInstance().BlockUser(email);
                            } catch (SQLException ex) {
                                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        BlockBtn.setCellFactory(cellFactory);

       table_user.getColumns().add(BlockBtn);

    }

   @FXML
    private void saveuser(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfid.getText());
            String nom = tfnom.getText();
            String prenom = tfprenom.getText();
            String password = tfpassword.getText();
            String email = tfemail.getText();
            String roles = tfroles.getText();
            user u = new user(id,nom, prenom,password,email,roles);
            userCRUD pcd = new userCRUD();
            pcd.ajouteruser(nom, prenom, password, email,roles);
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
    /* ObservableList<user> observableList = FXCollections.observableArrayList();
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

    @FXML
    private void updateuser(ActionEvent event) throws SQLException{
              user u = table_user.getSelectionModel().getSelectedItem();

    if (u != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Editeuser.fxml"));
            Parent root = loader.load();
            EditeuserController c = loader.getController();
            c.setuser(u);
            tfnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    }

    @FXML
    private void adduser(ActionEvent event) {
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouteruser.fxml"));
           tfnom.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void searchuser(ActionEvent event) {
        String searchNom = tfsearchname.getText().trim();
    if (searchNom.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer un nom pour la recherche.");
        alert.showAndWait();
        return;
    }
    userCRUD pcd = new userCRUD();
    List<user> userList = pcd.searchUserByName(searchNom);
    if (userList.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Aucun utilisateur trouvé pour ce nom.");
        alert.showAndWait();
        return;
    }
    ObservableList<user> observableList = FXCollections.observableArrayList(userList);
    table_user.setItems(observableList);
    
    }

    void setuser(user user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
