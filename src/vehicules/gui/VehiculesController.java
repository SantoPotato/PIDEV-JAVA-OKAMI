/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules.gui;
//k//
import vehicules.entities.Vehicules;
import vehicules.services.VehiculesCRUD;
import vehicules.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class VehiculesController implements Initializable {

    @FXML
    private DatePicker tfdate;
   

    private VehiculesCRUD ecrud = new VehiculesCRUD();
   
    @FXML
    private TableColumn<?, ?> colId;
   
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colDescription;
    public ObservableList<Vehicules> data = FXCollections.observableArrayList();
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnenvoyer;
    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnretour;
    @FXML
    private TableView<Vehicules> tableEvenements;
   
    private TableColumn<?, ?> colPhoto;
    private TextField tfimage;
    private TextField tftitre;
    @FXML
    private TextArea tfdescription;
    @FXML
    private TextField tfphoto;
    @FXML
    private Button btnchoisir;
    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> colDispo;
    @FXML
    private TableColumn<?, ?> colEtat;
    @FXML
    private TableColumn<?, ?> colCategorie;
    @FXML
    private TableColumn<?, ?> colImages;
    @FXML
    private TextField tfnom;
    @FXML
    private TextArea tfet;
    @FXML
    private TextArea tfcat;
    @FXML
    private Label etdispo;
    @FXML
    private TextArea tfdispo;
    @FXML
    private Button btninscriptions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  btnchoisir.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File fileImageV = fileChooser.showOpenDialog(btnchoisir.getScene().getWindow());
            if (fileImageV != null) {
                tfphoto.setText(fileImageV.getPath());
            }
        });
        // Initialiser la date par défaut du DatePicker à aujourd'hui
        tfdate.setValue(LocalDate.now());
        show();
        
     
    }
    MyConnection cnx = null;
    Statement st = null;
    VehiculesCRUD rcd = new VehiculesCRUD();

    

    public void show() {
        try {
            String requete = "SELECT * FROM Vehicules";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
     Vehicules r = new Vehicules(rs.getInt("id"), rs.getInt("catv_id"), rs.getString("nomvh"), rs.getInt("dispovh"),rs.getString("etatvh"), rs.getString("imagesvh"), rs.getString("descvh"), rs.getString("date"));
     data.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("catv_id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomvh"));
        colDispo.setCellValueFactory(new PropertyValueFactory<>("dispovh"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etatvh"));
          colImages.setCellValueFactory(new PropertyValueFactory<>("imagesvh"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descvh"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableEvenements.setItems(data);
    }

   
    /**
     * Modifie les informations de la réclamation sélectionnée dans la table
     * view. Récupère les nouvelles valeurs des champs type, date et
     * description, puis exécute une requête de mise à jour dans la base de
     * données. Affiche un message de confirmation si la mise à jour a réussi.
     * Rafraîchit la table view pour afficher les nouvelles données.
     */
   
  

    private void gererEvenements(ActionEvent event) {
        try {
            Parent evenetparent = FXMLLoader.load(getClass().getResource("Vehicules.fxml"));
            Scene eventscene = new Scene(evenetparent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(eventscene);
            window.show();
        } catch (IOException e) {
        }
    }

 

    
    

    @FXML
    private void retour(ActionEvent event) {
           try {
        Parent reclamationsParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene reclamationsScene = new Scene(reclamationsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(reclamationsScene);
        window.show();
    } catch (IOException e) {
    }
    }

   @FXML
private void addevn(ActionEvent event) {
     // Vérifier que tous les champs sont remplis
        if (tfnom.getText() == null ||tfet.getText() == null  || tfdate.getValue() == null || tfdescription.getText().isEmpty() || tfphoto.getText().isEmpty()) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Aucune de ces informations ne doit être vide. Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;

        }

        // Vérifier que la longueur de la description est supérieure à 5 caractères
        if (tfdescription.getText().length() < 6) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("La description doit contenir au moins 6 caractères");
            alert.showAndWait();
            return;
        }
        // Vérifier que la date est comprise entre il y a deux jours et aujourd'hui
        LocalDate selectedDate = tfdate.getValue();
        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
        if (selectedDate.isBefore(twoDaysAgo) || selectedDate.isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("La date doit être comprise entre il y a deux jours et aujourd'hui");
            alert.showAndWait();
            return;
        }
       
        // Récupérer les valeurs des champs
        int catv_id = Integer.parseInt(tfcat.getText());
        String nomvh = tftitre.getText();
        int dispovh = Integer.parseInt(tfdispo.getText());
        String etatvh =tfet.getText();
        String descvh = tfdescription.getText();
        String imagesvh = tfphoto.getText();
        String date = tfdate.getValue().toString();
        
        
        
        //
        
        
                
                
                
        rcd.ajouterEvenements(new Vehicules(catv_id,nomvh,dispovh,etatvh,descvh,imagesvh,date));
        // Rafraîchir la liste de données
        data.clear();
        show();
        tableEvenements.refresh();
      
    }






    @FXML
    private void editeven(ActionEvent event) {
         Vehicules vehicules = tableEvenements.getSelectionModel().getSelectedItem();
        if (vehicules == null) {
            // Aucune réclamation sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un évenement à modifier.");
            alert.showAndWait();
        } else {
            // Vérifier que tous les champs sont remplis
            if (tfnom.getText().isEmpty() || tfdescription.getText().isEmpty() || tfdate.getValue()== null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs avant de modifier un évenement.");
                alert.showAndWait();
                return;
            }

            // Vérifier que la longueur de la description est supérieure à 5 caractères
            if (tfdescription.getText().length() < 6) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("La description doit contenir au moins 6 caractères");
                alert.showAndWait();
                return;
            }

            // Récupérer les nouvelles valeurs
         int catv_id = Integer.parseInt(tfcat.getText());
        String nom = tftitre.getText();
        int dispovh = Integer.parseInt(tfdispo.getText());
        String etatvh =tfet.getText();
        String description = tfdescription.getText();
        String photo = tfphoto.getText();
        String date = tfdate.getValue().toString();
        
        
        
            // Mettre à jour la réclamation dans la base de données
            rcd.modifierEvenements(new Vehicules(vehicules.getId(),catv_id,nom,dispovh,etatvh,description,photo,date));

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussie");
            alert.setHeaderText(null);
            alert.setContentText("votre évenement a été modifiée avec succès.");
            alert.showAndWait();

            // Rafraîchir la table view pour afficher les nouvelles données
            data.clear();
            show();
        }
    }

    @FXML
    private void deleteeven(ActionEvent event) {
        
         // Vérifier si une réclamation est sélectionnée
        if (tableEvenements.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner un vehicule à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet vehicule ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la réclamation sélectionnée dans la vue de la table
            int id = tableEvenements.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            rcd.supprimerEvenements(id);
// Rafraîchir la liste de données
            data.clear();
            show();
            // Rafraîchir la vue de la table
            tableEvenements.refresh();
        }
    }


    @FXML
    private void afficherEvenementsSelected(MouseEvent event) {
         Vehicules vehicules = tableEvenements.getSelectionModel().getSelectedItem();
        if (vehicules != null) {
        tfnom.setText(vehicules.getNomvh());
        tfcat.setText(Integer.toString(vehicules.getCatv_id())); 
        tfdispo.setText(Integer.toString(vehicules.getDispovh()));
        tfet.setText(vehicules.getEtatvh());
        tfdescription.setText(vehicules.getDescvh());
        tfphoto.setText(vehicules.getImagesvh());
        tfdate.setValue(LocalDate.parse(vehicules.getDate()));
     


        } else {
           
            tfnom.setText("");
            tfcat.setText("");
            tfdispo.setText("");
            tfet.setText("");
            tfdescription.setText("");
            tfphoto.setText("");
            tfdate.setValue(null);
            
         
        }
    }

   /* private void inscription(ActionEvent event) {
Vehicules selectedEvent = tableEvenements.getSelectionModel().getSelectedItem();
    if (selectedEvent != null) {
        // Récupérer le nom et l'ID de l'événement sélectionné
        String nomTable = selectedEvent.getNomvh();
        int idTable = selectedEvent.getId();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscriptions.fxml"));
            Parent messageParent = loader.load();
            InscriptionsController messageController = loader.getController();
            
            // Passer le nom et l'ID de la table sélectionnée au contrôleur InscriptionsController
            messageController.setNomTable(nomTable);
            messageController.setIdTable(String.valueOf(idTable));
            
            Scene messageScene = new Scene(messageParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(messageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }*/

    private void gererinscription(ActionEvent event) {
          try {
        Parent inscrParent = FXMLLoader.load(getClass().getResource("inscriptionsback.fxml"));
        Scene inscrscene = new Scene(inscrParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(inscrscene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
    private void inscription(ActionEvent event) {
    }

   



}