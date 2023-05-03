
package tests;

import entities.User;
import services.OAuthAuthenticator;
import services.OAuthGoogleAuthenticator;
import services.ServiceUser;
import utils.ConnectionUtils;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author abdelazizmezri
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../app/gui/login_1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion des users");
        primaryStage.show();
   
        
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Connection cnx = ConnectionUtils.getInstance().getCnx();
        launch(args);
    }
    
}
    
   