/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import salle.entities.Salle;
import salle.services.SalleCRUD;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class SalleIndexController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private Button buttonSalle;
    @FXML
    private Button buttonPlannification;
    @FXML
    private TableView<Salle> tableviewSalle;
    @FXML
    private TableColumn<Salle, Integer> columnNumsa;
    @FXML
    private TableColumn<Salle, Integer> columnEtagesa;
    @FXML
    private TableColumn<Salle, String> columnTypesa;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    private FilteredList<Salle> filteredSalleList;
    @FXML
    private Button buttonGeneratePDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnNumsa.setCellValueFactory(new PropertyValueFactory<>("numsa"));
    columnEtagesa.setCellValueFactory(new PropertyValueFactory<>("etagesa"));
    columnTypesa.setCellValueFactory(new PropertyValueFactory<>("typesa"));

    SalleCRUD sc = new SalleCRUD();
    tableviewSalle.setItems(FXCollections.observableArrayList(sc.AfficherSalle()));
ObservableList<Salle> salleList = FXCollections.observableArrayList(sc.AfficherSalle());
filteredSalleList = new FilteredList<>(salleList, p -> true);
tableviewSalle.setItems(filteredSalleList);


//search
textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredSalleList.setPredicate(salle -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (salle.getTypesa().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (String.valueOf(salle.getEtagesa()).contains(lowerCaseFilter)) {
            return true;
        } else if (String.valueOf(salle.getNumsa()).contains(lowerCaseFilter)) {
            return true;
        }

        return false;
    });
});
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

    @FXML
    private void redirectPlannification(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/PlannificationIndex.fxml"));
        buttonIndex.getScene().setRoot(loader.load());

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }



    @FXML
    private void AddSalle(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleAdd.fxml"));
        buttonIndex.getScene().setRoot(loader.load());

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void UpdateSalle(ActionEvent event) {
        Salle s = tableviewSalle.getSelectionModel().getSelectedItem();

        if (s != null) {
            // RendezvousCRUD rc = new RendezvousCRUD();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SalleUpdate.fxml"));

                Parent root = loader.load();
                SalleUpdateController c = loader.getController();

                c.setSalle(s);

                buttonIndex.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
private void DeleteSalle(ActionEvent event) {
    Salle s = tableviewSalle.getSelectionModel().getSelectedItem();

    if (s != null) {
        SalleCRUD sc = new SalleCRUD();
        sc.SupprimerSalle(s.getId());
        tableviewSalle.getItems().remove(s); // remove from the tableview
        tableviewSalle.refresh(); // refresh the tableview to update the display
        System.out.println("Salle Supprimé !");
    }
}


private Document createPDF(List<Salle> salleList) throws DocumentException, FileNotFoundException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("Salles.pdf"));
    document.open();
    // Add content to the PDF document
    document.add(new Paragraph("Liste des salles : \n"));

    // create table
    PdfPTable table = new PdfPTable(3); // 3 columns
    table.setWidthPercentage(100);

   // add header row with green background color
PdfPCell cell1 = new PdfPCell(new Paragraph("Numéro"));
cell1.setBackgroundColor(BaseColor.GREEN);
PdfPCell cell2 = new PdfPCell(new Paragraph("Étage"));
cell2.setBackgroundColor(BaseColor.GREEN);
PdfPCell cell3 = new PdfPCell(new Paragraph("Type"));
cell3.setBackgroundColor(BaseColor.GREEN);
table.addCell(cell1);
table.addCell(cell2);
table.addCell(cell3);

// add data rows with grey background color
for(Salle salle : salleList){
    PdfPCell cell4 = new PdfPCell(new Paragraph(Integer.toString(salle.getNumsa())));
    PdfPCell cell5 = new PdfPCell(new Paragraph(Integer.toString(salle.getEtagesa())));
    PdfPCell cell6 = new PdfPCell(new Paragraph(salle.getTypesa()));
    cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
    cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell4);
    table.addCell(cell5);
    table.addCell(cell6);
}



    document.add(table);
    document.close();
    return document;
}


    @FXML
    private void generatePDF(ActionEvent event) {
        try {
        SalleCRUD sc = new SalleCRUD();
        List<Salle> salleList = sc.AfficherSalle();
        createPDF(salleList);
        System.out.println("PDF créé avec succès !");
    } catch (IOException | DocumentException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
    @FXML
private void handleButtonGeneratePDF(ActionEvent event) {
    generatePDF(event);
}



    
}