/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 *
 * @author saada
 */
public class Notification extends Application {
private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Label label = new Label();
        label.setTextFill(Color.WHITE);
        root.getChildren().add(label);

        Scene scene = new Scene(root, 200, 50);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);

        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        root.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 10;");
        root.setAlignment(Pos.CENTER);

        primaryStage.show();
    }

   public static void showNotification(String title, String message) {
    Platform.runLater(() -> {
        Stage notificationStage = new Stage();
        notificationStage.initStyle(StageStyle.TRANSPARENT);

        Label label = new Label(message);
        label.setTextFill(Color.WHITE);

        StackPane notificationPane = new StackPane(label);
        notificationPane.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 10;");
        notificationPane.setPadding(new Insets(10));

        // Création de l'image view
       
        Scene scene = new Scene(notificationPane);
        scene.setFill(Color.TRANSPARENT);

        notificationStage.setScene(scene);

        // center on screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        notificationStage.setX((screenBounds.getWidth() - scene.getWidth()) /2.3);
        notificationStage.setY((screenBounds.getHeight() - scene.getHeight())/12);

        notificationStage.show();

        // auto hide after 3 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> notificationStage.close());
        delay.play();
    });
    
}
 
}



