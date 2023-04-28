/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
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
    // Créer un nouveau document PDF
Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream("salleList.pdf"));
document.open();


// Ajouter une image
try {
    Image image = Image.getInstance("C:\\Users\\Oussama\\Downloads\\PIDEV-JAVA-OKAMI-Gestion_Salle\\PIDEV-JAVA-OKAMI-Gestion_Salle\\src\\app\\images\\hh.png");
    image.scaleToFit(150, 150); // Redimensionner l'image
    image.setAbsolutePosition(document.left(), document.bottom()); // Positionner l'image en bas à gauche
    document.add(image);
} catch (IOException e) {
    e.printStackTrace();
}



// Ajouter un titre
Paragraph title = new Paragraph("Liste des Salles\n\n");
title.setAlignment(Element.ALIGN_CENTER);
document.add(title);

// Ajouter une table avec les données de la liste des salles
PdfPTable table = new PdfPTable(3);
table.setWidthPercentage(100);
table.setSpacingBefore(10f);
table.setSpacingAfter(10f);

// Ajouter les en-têtes de colonnes
PdfPCell numsa = new PdfPCell(new Paragraph("Numero Salle"));
numsa.setBorderColor(BaseColor.BLACK);
numsa.setPaddingLeft(10);
numsa.setHorizontalAlignment(Element.ALIGN_CENTER);
numsa.setVerticalAlignment(Element.ALIGN_MIDDLE);

PdfPCell etagesa = new PdfPCell(new Paragraph("Etage"));
etagesa.setBorderColor(BaseColor.BLACK);
etagesa.setPaddingLeft(10);
etagesa.setHorizontalAlignment(Element.ALIGN_CENTER);
etagesa.setVerticalAlignment(Element.ALIGN_MIDDLE);

PdfPCell typesa = new PdfPCell(new Paragraph("Type"));
typesa.setBorderColor(BaseColor.BLACK);
typesa.setPaddingLeft(10);
typesa.setHorizontalAlignment(Element.ALIGN_CENTER);
typesa.setVerticalAlignment(Element.ALIGN_MIDDLE);

table.addCell(numsa);
table.addCell(etagesa);
table.addCell(typesa);

// Ajouter les données de la liste des salles à la table
for (Salle salle : salleList) {
    PdfPCell numsaValue = new PdfPCell(new Paragraph(String.valueOf(salle.getNumsa())));
    numsaValue.setBorderColor(BaseColor.BLACK);
    numsaValue.setPaddingLeft(10);
    numsaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
    numsaValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
    
    PdfPCell etagesaValue = new PdfPCell(new Paragraph(String.valueOf(salle.getEtagesa())));
    etagesaValue.setBorderColor(BaseColor.BLACK);
    etagesaValue.setPaddingLeft(10);
    etagesaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
    etagesaValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
    
    PdfPCell typesaValue = new PdfPCell(new Paragraph(salle.getTypesa()));
    typesaValue.setBorderColor(BaseColor.BLACK);
    typesaValue.setPaddingLeft(10);
    typesaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
    typesaValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
    
    table.addCell(numsaValue);
    table.addCell(etagesaValue);
    table.addCell(typesaValue);
}

// Ajouter la table au document
document.add(table);

// Fermer le document
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
    
private void handleButtonGeneratePDF(ActionEvent event) {
    generatePDF(event);
}



    
}