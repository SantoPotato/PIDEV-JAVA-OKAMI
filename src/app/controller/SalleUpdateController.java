package app.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import salle.entities.Salle;
import salle.utils.MyDB;

public class SalleUpdateController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Label labelNum;
    @FXML
    private TextField champNum;
    @FXML
    private Label labelEtage;
    @FXML
    private TextField champEtage;
    @FXML
    private Label labelType;
    @FXML
    private ChoiceBox<String> champType;

    private Connection c;

    private Salle selectedSalle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = MyDB.getInstance().getCnx();
        champType.setItems(FXCollections.observableArrayList(getTypes(c)));
        champType.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String type) {
                return type;
            }

            @Override
            public String fromString(String string) {
                return string;
            }
        });
    }

   @FXML
private void SalleUpdate(ActionEvent event) {
    if (selectedSalle == null) {
        return;
    }

    // Vérifier les champs saisis
    String errorMessage = "";
    try {
        int num = Integer.parseInt(champNum.getText());
        if (num > 10) {
            errorMessage += "Le numéro de la salle ne doit pas dépasser 10.\n";
        }
    } catch (NumberFormatException e) {
        errorMessage += "Le numéro de la salle doit être un entier.\n";
    }
    try {
        int etage = Integer.parseInt(champEtage.getText());
        if (etage > 6) {
            errorMessage += "L'étage de la salle ne doit pas dépasser 6.\n";
        }
    } catch (NumberFormatException e) {
        errorMessage += "L'étage de la salle doit être un entier.\n";
    }
    if (champType.getValue() == null || champType.getValue().isEmpty()) {
        errorMessage += "Le type de la salle ne doit pas être vide.\n";
    }

    if (errorMessage.isEmpty()) {
        String sql = "UPDATE salle SET numsa=?, etagesa=?, typesa=? WHERE id=?";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(champNum.getText()));
            stmt.setInt(2, Integer.parseInt(champEtage.getText()));
            stmt.setString(3, champType.getValue());
            stmt.setInt(4, selectedSalle.getId());
            stmt.executeUpdate();
            System.out.println("Salle mise à jour");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleIndex.fxml"));
                buttonIndex.getScene().setRoot(loader.load());

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        // Afficher une boîte de dialogue modale avec les messages d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Il y a des erreurs de saisie");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}



    public void setSelectedSalle(Salle salle) {
        this.selectedSalle = salle;
        champNum.setText(String.valueOf(salle.getNumsa()));
        champEtage.setText(String.valueOf(salle.getEtagesa()));
        champType.setValue(salle.getTypesa());
    }

    private List<String> getTypes(Connection c) {
        List<String> types = new ArrayList<>();
        String sql = "SELECT DISTINCT typesa FROM salle";

        try (PreparedStatement stmt = c.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                types.add(rs.getString("typesa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return types;
    }
    
    public void setSalle(Salle salle) {
    selectedSalle = salle;
    champNum.setText(String.valueOf(salle.getNumsa()));
    champEtage.setText(String.valueOf(salle.getEtagesa()));
    champType.setValue(salle.getTypesa());
}
    @FXML
    private void redirectSalle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    

    }

