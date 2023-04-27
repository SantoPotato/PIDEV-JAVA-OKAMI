/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import entities.RendezvousType;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.HistoriqueCRUD;
import services.RendezvousTypeCRUD;
import utils.HistoriqueMenuItem;

/**
 * FXML Controller class
 *
 * @author
 */
public class RendezvousTypeIndexController implements Initializable {

    RendezvousTypeCRUD rc = new RendezvousTypeCRUD();

    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private TableView<RendezvousType> tableviewRendezvousType;
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
    private Button buttonIndex;
    @FXML
    private TableColumn<RendezvousType, String> columnNom;
    @FXML
    private MenuItem buttonRendezvousStatistique;
    @FXML
    private MenuButton historique;
    @FXML
    private MenuItem buttonRendezvousCalendrier;
    @FXML
    private MenuButton menuLanguage;
    @FXML
    private MenuItem menuEnglish;
    @FXML
    private MenuItem menuFrench;
    @FXML
    private Label labelType;
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
        columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));

        tableviewRendezvousType.setItems(FXCollections.observableArrayList(rc.showAll()));

        columnNom.setCellValueFactory(typeRowData -> new SimpleObjectProperty<>(typeRowData.getValue().getType()));

        HistoriqueCRUD hc = new HistoriqueCRUD();
        hc.showAll().forEach(item -> {
            historique.getItems().add(new HistoriqueMenuItem(item));
        });

        changeLanguage(Locale.getDefault().toString());

    }

    @FXML
    private void redirectRendezvous(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rendezvousAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeAdd.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rendezvousUpdate(ActionEvent event) {
        RendezvousType t = tableviewRendezvousType.getSelectionModel().getSelectedItem();

        if (t != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeUpdate.fxml"));

                Parent root = loader.load();
                RendezvousTypeUpdateController c = loader.getController();

                c.setRendezvous(t);

                buttonIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void rendezvousDelete(ActionEvent event) {
        RendezvousType t = tableviewRendezvousType.getSelectionModel().getSelectedItem();

        if (t != null) {
            rc.remove(t.getId());
            tableviewRendezvousType.getItems().remove(t); // remove from the tableview
            HistoriqueCRUD hc = new HistoriqueCRUD();
            hc.add(1, "a supprimé le type de rendez-vous '" + String.valueOf(t.getId()) + "'");
        }
    }

    @FXML
    private void rendezvousTextSearch(ActionEvent event) {
        tableviewRendezvousType.setItems(FXCollections.observableArrayList(rc.searchRendezvousType(textSearch.getText())));
    }

    @FXML
    private void rendezvousButtonSearch(ActionEvent event) {
        tableviewRendezvousType.setItems(FXCollections.observableArrayList(rc.searchRendezvousType(textSearch.getText())));
    }

    @FXML
    private void redirectRendezvousStatistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousStats.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectIndex(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Index.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvousCalendrier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousCalendar.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
            props.load(new FileInputStream("src/app/localisation/ui_" + lang + ".properties"));
            labelType.setText(props.getProperty("labelRendezvousType"));
            columnNom.setText(props.getProperty("columnRendezvousTypeName"));
            buttonSearch.setText(props.getProperty("buttonSearch"));
            buttonAdd.setText(props.getProperty("buttonAdd"));
            buttonUpdate.setText(props.getProperty("buttonUpdate"));
            buttonDelete.setText(props.getProperty("buttonDelete"));
            buttonRendezvous.setText(props.getProperty("menuRendezvous"));
            buttonRendezvousType.setText(props.getProperty("menuRendezvousType"));
            buttonRendezvousStatistique.setText(props.getProperty("menuStats"));
            buttonRendezvousCalendrier.setText(props.getProperty("menuCalendar"));
            menuLanguage.setText(props.getProperty("Language"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
