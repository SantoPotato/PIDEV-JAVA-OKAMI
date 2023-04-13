/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import okami.healthherald.entities.RendezvousType;
import okami.healthherald.utils.ConnectionDB;

/**
 *
 * @author
 */
public class RendezvousTypeCRUD {

    Connection c;

    public RendezvousTypeCRUD() {
        c = ConnectionDB.getInstance().getConnection();
    }

    public void add() {
        try {
            String request = "INSERT INTO rendezvous_type (id, type) VALUES (DEFAULT, NULL)";
            Statement st = c.createStatement();
            st.executeUpdate(request);
            // System.out.println("Rendez vous type added successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void add(RendezvousType R) {
        try {
            System.out.println(R.getType());
            String request = "INSERT INTO rendezvous_type (id, type) VALUES (DEFAULT, ?)";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setString(1, R.getType());
            pst.executeUpdate();
             System.out.println("Rendez vous type added successfully.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void update(RendezvousType R, int id) {
        try {
            String request = "UPDATE rendezvous_type SET type=? WHERE id=?";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setString(1, R.getType());
            pst.setInt(2, id);
            pst.executeUpdate();
            // System.out.println("Rendez vous type " + R.getId() + " updated successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void remove(int id) {
        try {
            String request = "DELETE FROM rendezvous_type WHERE id=?";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setInt(1, id);
            pst.executeUpdate();
            // System.out.println("Rendez vous type " + id + " removed successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<RendezvousType> showAll() {

        List<RendezvousType> rendezvousTypeList = new ArrayList<>();

        try {

            String request = "SELECT * FROM rendezvous_type";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(request);

            while (rs.next()) {
                rendezvousTypeList.add(new RendezvousType(rs.getInt("id"), rs.getString("type")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return rendezvousTypeList;
    }

}
