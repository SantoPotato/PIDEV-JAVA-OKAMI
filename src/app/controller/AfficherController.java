/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.User;
import services.ServiceUser;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class AfficherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ServiceUser su = new ServiceUser();
    @FXML
    private TextField lusername;
    @FXML
    private TextField lfname;
    @FXML
    private TextField llname;
    @FXML
    private TextField lemail;
     @FXML
    private TextField lphone_number;
      @FXML
    private TextField lgender;
       @FXML
    private TextField lrole;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            User u=su.afficher(21);
            lusername.setText(u.getUser_Name());
            lfname.setText(u.getFirst_Name());
            llname.setText(u.getLast_Name());
            lemail.setText(u.getEmail());
            String tmp =String.valueOf(u.getPhone_number());
            lphone_number.setText(tmp);
            lgender.setText(u.getGender());
            lrole.setText(u.getRole());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void set(ActionEvent event) throws SQLException {
        String lusername2 = lusername.getText();
        String lfname2 = lfname.getText();
        String llname2 = llname.getText();
        String lemail2 = lemail.getText();
        String lphone_number2 = lphone_number.getText();
        int tmp =Integer.parseInt(lphone_number2);
        String lgender2 = lgender.getText();
        String lrole2 = lrole.getText();
        su.modifier(lusername2, lfname2, llname2, lemail2, tmp,lgender2, lrole2, 21);


    }
    
}
