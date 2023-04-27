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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author MSI GAMMING
 */
public class PrimaryController implements Initializable {

    @FXML
    private Label captchahere;
    @FXML
    private Label messagebtn;
    @FXML
    private TextField textarea;
    @FXML
    private Button checkbtn;
    @FXML
    private Button newcaptchabtn;

    /**
     * Initializes the controller class.
     */
    @FXML
    void setcaptcha()
    {
        char data[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        int max = 61;
        String captchastring="";
        int min = 0;
        for(int i =0;i<6;i++)
        {
            int randomnumber = (int)Math.floor(Math.random()*(max-min+1)+min);
            captchastring+=data[randomnumber];
        }
        System.out.println(captchastring);
        captchahere.setText(captchastring);
        captchahere.setTextFill(Color.GREEN);
    }
    @FXML
    void captchachecker()
    {
        String userinput = textarea.getText();
        if(userinput.equals(String.valueOf(captchahere.getText())))
        {
            if(!(userinput.equals("captcha"))){
            messagebtn.setText("Success");
            messagebtn.setTextFill(Color.GREEN);
            }
            else{
                messagebtn.setText("please click on new captcha");
                messagebtn.setTextFill(Color.RED);
            }
        }
        else
        {
            messagebtn.setText("Fail");
            messagebtn.setTextFill(Color.RED);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
