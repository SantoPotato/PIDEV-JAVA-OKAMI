/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.User;
import services.AuthentificationService;
import services.OAuthAuthenticator;
import services.OAuthGoogleAuthenticator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import utils.ConnectionDB;
import utils.UserSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class LoginController implements Initializable {

    Connection cnx;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private TextField txtuname;
    @FXML
    private TextField txtpass;
    @FXML
    private Button signup;
    @FXML
    private Button btnok;
    @FXML
    private Button ForgetPassword;
    @FXML
    private Button btngoogle;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signup(ActionEvent event) {

        loadPage("signup_page");
    }

    private void loadPage(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/" + name + ".fxml"));
            btnok.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    @FXML
    private void Login(ActionEvent event) throws SQLException {

        AuthentificationService authService = new AuthentificationService();
        Connection c = ConnectionDB.getInstance().getConnection();
        String user_Name = txtuname.getText();
        String password = txtpass.getText();

        if (user_Name.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "Nom de compte ou mot de passe manquant");
        } else {

            String requete = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = c.prepareStatement(requete);
            statement.setString(1, user_Name);
            rs = statement.executeQuery();
            if (rs.next()) {
                if (!authService.checkPasswd(password, rs.getString(5))) {
                    JOptionPane.showMessageDialog(null, "Nom de compte ou mot de passe incorrect");

                } else {
                    User user = new User();
                    user.setId_user(rs.getInt(1));
                    user.setFirst_Name(rs.getString(2));
                    user.setLast_Name(rs.getString(3));
                    user.setUser_Name(rs.getString(4));
                    user.setPassword(rs.getString(5));
                    user.setEmail(rs.getString(6));
                    user.setPhone_number(rs.getInt(7));
                    user.setGender(rs.getString(8));
                    user.setRole(rs.getString(9));

                    UserSession userSession = UserSession.getInstace(user);
                    loadPage("captcha");

                }

            } else {

                JOptionPane.showMessageDialog(null, " Imposse de se connecter  ");
                txtuname.setText("");
                txtpass.setText("");
                txtuname.requestFocus();

            }
        }

    }

    @FXML
    private void ForgetPassword(ActionEvent event) {
        loadPage("ForgetPassword");
    }

    @FXML
    private void logingoogle(ActionEvent event) {

        String gClientId = "154087676852-22lvca03ua3rvcesj5gdllps2qm28oda.apps.googleusercontent.com";
        String gRedir = "##########";
        String gScope = "https://www.googleapis.com/auth/";
        String gSecret = "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";
        OAuthAuthenticator auth = new OAuthGoogleAuthenticator(gClientId, gRedir, gSecret, gScope);
        auth.startLogin();
    }

}
