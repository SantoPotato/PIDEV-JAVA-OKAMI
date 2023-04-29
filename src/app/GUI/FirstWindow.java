/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.GUI;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author
 */
public class FirstWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/GUI/Index.fxml"));
        
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../themes/nord-dark.css").toExternalForm());

            primaryStage.setTitle("TesT");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
