/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class UserFXMLController implements Initializable {

    @FXML
    private TextField tf_fn;
    @FXML
    private TextField tf_ln;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        String firstname = tf_fn.getText();
         String lastname = tf_ln.getText();
//         int temp = authentification (firstname,lastname);
//         if(temp!=0)
//         {
//             if(temp==1)
//             {
//                 pane_cluient.t
//             }
//             if(temp==2)
//             {
//                 
//             }
//         }else{
//             label.setta*est("pw or usrname incorrect ")
//         }
    }
    
}
