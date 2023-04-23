/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.app.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author
 */
public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("RendezvousIndex.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../themes/nord-dark.css").toExternalForm());

            primaryStage.setMinWidth(720);
            primaryStage.setMinHeight(480);
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