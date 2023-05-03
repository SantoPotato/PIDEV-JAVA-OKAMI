/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import entities.Vehicules;
import entities.Categorievehicules;
import services.VehiculesCRUD;
import services.VehiculescategoriesCRUD;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class VehiculesIndexController implements Initializable {

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
    private TableView<Vehicules> tableviewEquipement;
    @FXML
    private TableColumn<Vehicules, Integer> nomColumn;
  //  private TableColumn<Vehicules, String> desColumn;
    @FXML
    private TableColumn<Vehicules, Date> datColumn;
   
    @FXML
    private TableColumn<Vehicules, Categorievehicules> catColumn;
     @FXML
    private TableColumn<Vehicules, String> quantColumn;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonStock;
    private FilteredList<Vehicules> filteredStockList;
    @FXML
    private Button btnstat;
    @FXML
    private Button BtnQr;
    @FXML
    private ImageView qrcodee;

private ObservableList<Vehicules> vehiculesList = FXCollections.observableArrayList();
    @FXML
    private Button Btnfront;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        catColumn.setCellValueFactory(new PropertyValueFactory<>("catv_id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomvh"));
       // desColumn.setCellValueFactory(new PropertyValueFactory<>("dispovh"));
        
        datColumn.setCellValueFactory(new PropertyValueFactory<>("etatvh"));
        
        quantColumn.setCellValueFactory(new PropertyValueFactory<>("descvh"));
        
        
        
        nomColumn.setPrefWidth(100);
     //   desColumn.setPrefWidth(500);
        datColumn.setPrefWidth(300);
        catColumn.setPrefWidth(600);
        quantColumn.setPrefWidth(60);

        VehiculesCRUD ec = new VehiculesCRUD();
        tableviewEquipement.setItems(FXCollections.observableArrayList(ec.Afficher()));
        
        ObservableList<Vehicules> stockList = FXCollections.observableArrayList(ec.Afficher());
filteredStockList = new FilteredList<>(stockList, p -> true);
tableviewEquipement.setItems(filteredStockList);


//search
textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredStockList.setPredicate(Vehicules -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (Vehicules.getNomvh().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (Vehicules.getEtatvh().toLowerCase().contains(lowerCaseFilter) ){
            return true;
        } else if (String.valueOf(Vehicules.getDescvh()).contains(lowerCaseFilter)) {
            return true;
        }

        return false;
    });
});
        
        
        
    }
    
    
    
private void StockSearch(ActionEvent event) {
    textSearch.setText("");
    filteredStockList.setPredicate(Vehicules -> true);
}
    
     @FXML
    private void Categorieequipement(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/CategorieIndexvehicules.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

 @FXML
    private void stockac(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/AjoutVehicules.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   


    @FXML
    private void StockAdd(ActionEvent event) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/AjoutVehicules.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
     @FXML
    private void StockUpdate(ActionEvent event) throws SQLException {
           Vehicules e = tableviewEquipement.getSelectionModel().getSelectedItem();
           
        if (e != null) {
           // System.out.println("aaaaaaaaaaaaa");
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/app/gui/Editvehicules.fxml"));

                Parent root = loader.load();
                EditvehiculesController c = loader.getController();

                c.setEquipement(e);

                labelIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
  
private void refreshTable() {
    tableviewEquipement.refresh();
}

@FXML
private void StockDelete(ActionEvent event) {
    Vehicules e = (Vehicules) tableviewEquipement.getSelectionModel().getSelectedItem();

    if (e != null) {
        VehiculesCRUD ec = new VehiculesCRUD();

        ec.Supprimer(e.getId());
        vehiculesList.remove(e); // remove from the ObservableList
         tableviewEquipement.refresh(); // refresh the tableview
    }
}

@FXML
      private void BtnQr(ActionEvent event) {
        Vehicules p = tableviewEquipement.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
String etatDispo;
if (p.isDispovh() == 1) {
    etatDispo = "oui";
} else {
    etatDispo = "non";
}


 String Information = "nom  : "+p.getNomvh()+"\n"+"Description : "+p.getDescvh()+"\n"+"etat : "+p.getEtatvh()+"\n"+"disponible (oui/non): : "+etatDispo;
       // String Information = "nom  : "+p.getNomvh()+"\n"+"Description : "+p.getDescvh()+;
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }


    }
 @FXML
    private void btnstat(ActionEvent event) {
        try {
        Parent reclamationsParent = FXMLLoader.load(getClass().getResource("/app/gui/statvehicule.fxml"));
        Scene reclamationsScene = new Scene(reclamationsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(reclamationsScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
    private void Btnfront(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/frontvehicules.fxml"));
            labelIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
}
