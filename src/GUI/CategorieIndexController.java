/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import stock.entities.Stock;
import stock.entities.Stockcategories;
import stock.services.StockcategoriesCRUD;
import stock.services.StockCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class CategorieIndexController implements Initializable {

    @FXML
    private Label labelPage;
    @FXML
    private Label labelPath;
    @FXML
    private Label labelIndex;
    @FXML
    private Button buttonCategorie;
    @FXML
    private Button buttonTest;
    @FXML
    private TableView<Stockcategories> tableviewEquipement;
    @FXML
    private TableColumn<Stockcategories, Integer> idColumn;
    @FXML
    private TableColumn<Stockcategories,String> NomColumn;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    private FilteredList<Stockcategories> filteredcategorieList;
    @FXML
    private Button buttonstock;
    @FXML
    private Button Delete;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("typecat"));
        
        StockcategoriesCRUD cc = new StockcategoriesCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(cc.Afficherc()));
        
        ObservableList<Stockcategories> stockcatList = FXCollections.observableArrayList(cc.Afficherc());
filteredcategorieList = new FilteredList<>(stockcatList, p -> true);
tableviewEquipement.setItems(filteredcategorieList);


//search
textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredcategorieList.setPredicate(Stockcategories -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (Stockcategories.getTypecat().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } 

        return false;
    });
});
        
        
        
    }
    
    
    
private void StockSearch(ActionEvent event) {
    textSearch.setText("");
    filteredcategorieList.setPredicate(stock -> true);
}
     

   @FXML
    private void Categorieequipement(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieIndex.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void StockAdd(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajoutcategorie.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void categorieUpdatee(ActionEvent event) throws SQLException {
           Stockcategories e = tableviewEquipement.getSelectionModel().getSelectedItem();
           
        if (e != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("Editcategorie.fxml"));

                Parent root = loader.load();
                EditcategorieController c = loader.getController();

                c.setEquipements(e);

                labelIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void CategorieDelete(ActionEvent event) {
    
        Stockcategories e =  (Stockcategories) tableviewEquipement.getSelectionModel().getSelectedItem();

        if (e != null) {
            StockcategoriesCRUD ec = new StockcategoriesCRUD();
            ec.Supprimerc(e.getId());
            tableviewEquipement.getItems().remove(e); // remove from the tableview
        }
    }

    @FXML
    private void stock(ActionEvent event) {
      
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
            buttonstock.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void stat(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stat.fxml"));
            buttonTest.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    

