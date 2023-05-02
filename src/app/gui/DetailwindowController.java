/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SONDESH
 */
public class DetailwindowController implements Initializable {

    @FXML
    private TextField textid;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField textpassword;
    @FXML
    private TextField textemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setTextid(String message){
    this.textid.setText(message);
    }
    
      public void setTextnom(String message){
    this.textnom.setText(message);
    }
       public void setTextprenom(String message){
    this.textprenom.setText(message);
    }
        public void setTextpassword(String message){
    this.textpassword.setText(message);
    }
        public void setTextemail(String message){
    this.textemail.setText(message);
    }
    
}
