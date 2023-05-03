/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.gui;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author
 */
public class MainWindow extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
            Locale.setDefault(new Locale("fr"));
            Parent root = FXMLLoader.load(getClass().getResource("app/gui/Index.fxml"));

            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("/app/themes/nord-dark.css").toExternalForm());

            primaryStage.setMinWidth(720);
            primaryStage.setMinHeight(480);
            primaryStage.setTitle("HealthHerald");
            primaryStage.getIcons().add(new Image("/app/images/hh.png"));
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
