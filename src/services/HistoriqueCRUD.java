/*
 * Property of Okami�
 * Not destined for commercial use
 */
package services;

import entities.Historique;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionDB;

/**
 *
 * @author 
 */
public class HistoriqueCRUD implements HistoriqueInterface {
        Connection c;

    public HistoriqueCRUD() {
        c = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void add(Integer user_id, String description) {
       try {
            String request = "INSERT INTO historique (id, user_id, description, date) VALUES (DEFAULT, ?, ?, DEFAULT);";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setInt(1, user_id);
            pst.setString(2, description);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void remove(Integer id) {
         try {
            String request = "DELETE FROM historique WHERE id=?;";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Historique> showAll() {
        List<Historique> historique = new ArrayList<>();
       try {
            String request = "SELECT h.*,u.* FROM historique h "
                    + "INNER JOIN user u ON u.id=h.user_id;";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                Historique h = new Historique(rs.getInt("h.id"), new User(rs.getInt("u.id"), rs.getString("u.nom"), rs.getString("u.prenom")), rs.getString("h.description"), rs.getTimestamp("h.date").toLocalDateTime());
                historique.add(h);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return historique;
    }
}
