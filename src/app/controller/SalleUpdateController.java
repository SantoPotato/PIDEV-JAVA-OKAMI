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

