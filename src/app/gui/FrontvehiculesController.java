/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;
import app.entities.Vehicules;


import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import app.services.VehiculesCRUD;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class FrontvehiculesController implements Initializable {
         
    @FXML
    private VBox tfpostlist;
    private VBox p;
    private VBox du;
    static String hash;
    @FXML
    private Circle cir1;
    @FXML
    private Label labelIndex;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        VehiculesCRUD service=new VehiculesCRUD();
        List<Vehicules> list = new ArrayList<Vehicules>();
        list=service.Afficher();
        //Collections.sort(list,Collections.reverseOrder());
        
        System.out.println(this.getHash());
       
        for(Vehicules e : list)
        {  
           VBox du = new VBox();
           Label date= new Label(diffdate(e.getDate()));
              // Label nomvh= new Label(diffdate(e.getNomvh()));
          //  Label username= new Label("Username");
         
        
           
           //System.out.println(service.OneUser(e.getNomvh()));
           
          // du.getChildren().add(nomvh); 
           du.getChildren().add(date);
           du.setTranslateX(15);
           du.setTranslateY(15);
           
           HBox imgu = new HBox();
          
           Circle cir = new Circle();
           cir.setRadius(35);

           
          
       
           cir.setTranslateX(5);
           cir.setTranslateY(5);

         
           
      
           
        
           imgu.getChildren().add(cir);
           imgu.getChildren().add(du);
           
          
           
          // VBox p = new VBox();
         
            //System.out.println("/image/"+e.getImageP());
           
           
           Label desc= new Label(e.getDescvh());
           Label nomvh= new Label(e.getNomvh());
           Label etatvh= new Label(e.getEtatvh());
           desc.setTranslateX(10);
           desc.setTranslateY(20);
           nomvh.setTranslateX(10);
           nomvh.setTranslateY(20);
           etatvh.setTranslateX(10);
           etatvh.setTranslateY(20);
           
           
           
           Hyperlink hashtag = new Hyperlink();
          
           
           HBox lc = new HBox();
           
          
          // p.getChildren().add(lc);
            VBox post= new VBox();
          //  post.getChildren().add(imgu);
           post.getChildren().add(nomvh); 
          post.getChildren().add(desc);
           
             post.getChildren().add(etatvh);

           
            post.getChildren().add(lc);
            post.setTranslateY(20);
            tfpostlist.getChildren().add(post);
            tfpostlist.setSpacing(45);
            
           //tfpostlist.getChildren().add(p);
       
        }
        
    }    

    private void redirect(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("AddPost.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
    }
    private void redirectstat(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("LikePostStat.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
    }
    private void redirectstat2(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("likestat.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
    }
    public String diffdate (String d) {
        
       SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
       LocalDateTime now = LocalDateTime.now();  
       String dc= dtf.format(now);
      // System.out.println(dtf.format(now));
       String date = null;
        try {
                        Date d1 = sdf.parse(d);
			Date d2 = sdf.parse(dc);

			// Calucalte time difference
			// in milliseconds
			long difference_In_Time
				= d2.getTime() - d1.getTime();

			// Calucalte time difference in
			// seconds, minutes, hours, years,
			// and days
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
                        
                        if (difference_In_Years != 0)
                           date= String.valueOf("il ya "+difference_In_Years+" années");
                        else if (difference_In_Days != 0)
                            date= String.valueOf("il ya "+difference_In_Days+" jours");    
		        else if (difference_In_Hours != 0)
                            date= String.valueOf("il ya "+difference_In_Hours+" heures");
                        else if (difference_In_Minutes != 0)
                            date= String.valueOf("il ya "+difference_In_Minutes+" minutes");
                        else if (difference_In_Seconds != 0)
                            date= String.valueOf("il ya "+difference_In_Seconds+" secondes");
                        else 
                            date="Now";
                        
                        
      } catch (ParseException ex) {
           // Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
        return date ;
    }
      
}