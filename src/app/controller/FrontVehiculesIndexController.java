/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import services.VehiculesCRUD;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class FrontVehiculesIndexController implements Initializable {

    static String hash;
    @FXML
    private Label labelIndex;
    @FXML
    private VBox container;
    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VehiculesCRUD service = new VehiculesCRUD();

        service.Afficher().stream().map((e) -> {
            VBox box = new VBox();
            Label date = new Label(diffdate(e.getDate()));
            box.getChildren().add(date);
            box.setTranslateX(15);
            box.setTranslateY(15);
            HBox imgu = new HBox();
            Circle cir = new Circle();
            cir.setRadius(35);
            cir.setTranslateX(5);
            cir.setTranslateY(5);
            imgu.getChildren().add(cir);
            imgu.getChildren().add(box);
            Label desc = new Label(e.getDescvh());
            Label nomvh = new Label(e.getNomvh());
            Label etatvh = new Label(e.getEtatvh());
            desc.setTranslateX(10);
            desc.setTranslateY(20);
            nomvh.setTranslateX(10);
            nomvh.setTranslateY(20);
            etatvh.setTranslateX(10);
            etatvh.setTranslateY(20);
            Hyperlink hashtag = new Hyperlink();
            HBox lc = new HBox();
            VBox post = new VBox();
            post.getChildren().add(nomvh);
            post.getChildren().add(desc);
            post.getChildren().add(etatvh);
            post.getChildren().add(lc);
            return post;
        }).map((post) -> {
            post.setTranslateY(20);
            return post;
        }).map((post) -> {
            container.getChildren().add(post);
            return post;
        }).forEachOrdered((_item) -> {
            container.setSpacing(45);
        });
       
        
    }

    public String diffdate(String d) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dc = dtf.format(now);
        String date = null;
        try {
            Date d1 = sdf.parse(d);
            Date d2 = sdf.parse(dc);

            long difference_In_Time
                    = d2.getTime() - d1.getTime();
            
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            if (difference_In_Years != 0) {
                date = String.valueOf("il ya " + difference_In_Years + " années");
            } else if (difference_In_Days != 0) {
                date = String.valueOf("il ya " + difference_In_Days + " jours");
            } else if (difference_In_Hours != 0) {
                date = String.valueOf("il ya " + difference_In_Hours + " heures");
            } else if (difference_In_Minutes != 0) {
                date = String.valueOf("il ya " + difference_In_Minutes + " minutes");
            } else if (difference_In_Seconds != 0) {
                date = String.valueOf("il ya " + difference_In_Seconds + " secondes");
            } else {
                date = "Now";
            }

        } catch (ParseException ex) {
            // Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

}
