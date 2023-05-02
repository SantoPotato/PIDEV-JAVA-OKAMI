/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author abirk
 */
public class AuthentificationService implements IAuthentificationService {

    ServiceUser userService = null;

    @Override
    public void signup(String first_name, String last_name, String username, String email, String password, String gender) {

        User user = new User(first_name, last_name, username, hashPasswd(password), email, 1245, gender);
        user.setRole("Client");
        checkIfUserCanBeAdded(user);

        try {
            userService.ajouter(user);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public AuthentificationService() {
        userService = new ServiceUser();

    }

    public boolean checkEmailExists(String email) {
        Connection cnx = ConnectionUtils.getInstance().getCnx();
        boolean result = false;

        try {
            String req = "SELECT * FROM user WHERE email = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    public boolean checkIfUserCanBeAdded(User u) {
        // Vérifier que l'adresse email n'est pas déjà utilisée par un autre utilisateur
        Connection cnx = ConnectionUtils.getInstance().getCnx();
        String checkEmailQuery = "SELECT COUNT(*) as nbr FROM user WHERE email=?";
        try (PreparedStatement checkEmailStmt = cnx.prepareStatement(checkEmailQuery)) {
            checkEmailStmt.setString(1, u.getEmail());
            ResultSet rs = checkEmailStmt.executeQuery();
            if (rs.next() && rs.getInt("nbr") > 0) {
                System.err.println(rs.getInt("nbr") + "++++++++++++++++++++");

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("entrer une nouvelle email.");
                alert.showAndWait();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public void login(String username, String email, String password) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUserExist(String username, String email) {

        return false;
        //To change body of generated methods, choose Tools | Templates.
    }

    private String hashPasswd(String plainTextPassword) {

        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean checkPasswd(String plainPassword, String hashedPassword) {
        return (BCrypt.checkpw(plainPassword, hashedPassword)) ? true : false;
    }

}
