/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import utils.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.UserRole;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author abirk
 */
public class ServiceUser implements Iservice<User> {

    Connection cnx = ConnectionDB.getInstance().getConnection();

    @Override
    public void ajouter(User user) throws SQLException {
        try {
            String request = "INSERT INTO user (id, role_id, email, password, nom, prenom, is_verified, username, phone_number, gender) VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?);";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, user.getRole().getId());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getNom());
            pst.setString(5, user.getPrenom());
            pst.setString(6, user.getUsername());
            pst.setInt(7, user.getPhone_number());
            pst.setString(8, user.getGender());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM user WHERE id =" + id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(String s, String s2, String s3, String s4, int s5, String s6, int s7, int id) throws SQLException {
        String req = "UPDATE user SET prenom = '" + s + "',`nom`= '" + s2 + "',`username`='" + s3 + "',`email`='" + s4 + "',`phone_number`='" + s5 + "',`gender`='" + s6 + "',`role_id`='" + s7 + "' WHERE id = " + id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public User afficher(int id) throws SQLException {
        User p = new User();
        String req = "SELECT u.*,r.* FROM user u INNER JOIN roleuser r ON r.id=u.role_id WHERE u.id = " + id + "";
        PreparedStatement ps = cnx.prepareStatement(req); //preparestatment pour les requette bel parametre 
        ResultSet rs = ps.executeQuery(req);
        while (rs.next()) {
            p.setNom(rs.getString("u.nom"));
            p.setPrenom(rs.getString("u.prenom"));
            p.setUsername(rs.getString("u.username"));
            p.setPassword(rs.getString("u.password"));
            p.setEmail(rs.getString("u.email"));
            p.setPhone_number(rs.getInt("u.phone_number"));
            p.setGender(rs.getString("u.gender"));
            p.setRole(new UserRole(rs.getInt("r.id"), rs.getString("r.role")));

        }
        return p;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select u.*, r.* from user u INNER JOIN roleuser r on u.role_id=r.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User(rs.getInt("u.id"), new UserRole(rs.getInt("r.id"), rs.getString("r.role")), rs.getString("u.email"), rs.getString("u.password"), rs.getString("u.nom"), rs.getString("u.prenom"), rs.getBoolean("u.is_verified"), rs.getString("u.username"), rs.getInt("u.phone_number"), rs.getString("u.gender"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public User getOneById(int id) {
        User p = null;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement(); //pour les requette sans parametre
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    public boolean checkEmailExists(String email) {
        Connection c = ConnectionDB.getInstance().getConnection();
        boolean result = false;

        try {
            String req = "SELECT * FROM user WHERE email = ?";
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    public List<User> selectBy(String attribute, String value) throws SQLException {
        String sql = "SELECT * FROM user u INNER JOIN roleuser r on u.role_id=r.id where u." + attribute + "= ?";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1, value);
        ResultSet rs = pstmt.executeQuery();
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("u.id"));
            user.setNom(rs.getString("u.nom"));
            user.setPrenom(rs.getString("u.prenom"));
            user.setUsername(rs.getString("u.username"));
            user.setPassword(rs.getString("u.password"));
            user.setEmail(rs.getString("u.email"));
            user.setPhone_number(rs.getInt("u.phone_number"));
            user.setGender(rs.getString("u.gender"));
            user.setRole(
                    new UserRole(rs.getInt("r.id"), rs.getString("r.role"))
            );

        }
        return userList;
    }

    public void updatePassword(User user) throws SQLException {
        String sql = "UPDATE user SET password=? WHERE id=?";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1, user.getPassword());
        pstmt.setInt(2, user.getId());
        pstmt.executeUpdate();
    }
//mail et mot de passe. Elle tente de mettre à jour le mot de passe d'un utilisateur dans une table de base de données

    public void ResetPaswword(String email, String password) {
        try {

            String req = "UPDATE user SET password = ? WHERE email = ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int ChercherMail(String email) {
        try {
            String req = "SELECT * from user WHERE user.`email` ='" + email + "'  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
//To change body of generated methods, choose Tools | Templates.
    }

    public List<User> searchUserByemail(String email) {
        List<User> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM User WHERE email=?";
            PreparedStatement st = ConnectionDB.getInstance().getConnection().prepareStatement(requete);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setGender(rs.getString("gender"));
                u.setPhone_number(rs.getInt("phone_number"));
                myList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public Map<String, Integer> countByGender() {
        Map<String, Integer> countMap = new HashMap<>();

        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            String query = "SELECT gender, COUNT(*) AS count FROM user GROUP BY gender";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String gender = rs.getString("gender");
                int count = rs.getInt("count");
                countMap.put(gender, count);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return countMap;
    }

}
