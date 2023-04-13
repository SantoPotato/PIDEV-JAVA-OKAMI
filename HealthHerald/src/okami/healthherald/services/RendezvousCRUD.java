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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okami.healthherald.entities.Rendezvous;
import okami.healthherald.entities.RendezvousType;
import okami.healthherald.entities.Salle;
import okami.healthherald.entities.User;
import okami.healthherald.utils.ConnectionDB;

/**
 *
 * @author
 */
public class RendezvousCRUD {

    Connection c;

    public RendezvousCRUD() {
        c = ConnectionDB.getInstance().getConnection();
    }

    public void add() {
        try {
            String request = "INSERT INTO rendezvous (id, daterv, rappel, end_at, Salle, Type) VALUES (DEFAULT, DEFAULT, DEFAULT, DEFAULT, NULL, NULL)";
            Statement st = c.createStatement();
            st.executeUpdate(request);
            // System.out.println("Rendez vous added successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void add(Rendezvous R) {
        try {
            String request = "INSERT INTO rendezvous (id, daterv, rappel, end_at, Salle, Type) VALUES (DEFAULT, ?, DEFAULT, ?, ?, ?)";
            PreparedStatement pst = c.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setDate(1, new java.sql.Date(R.getDaterv().getTime()));
            pst.setDate(2, new java.sql.Date(R.getEndAt().getTime()));
            pst.setInt(3, R.getSalle().getId());
            pst.setInt(4, R.getType().getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    int rendezvous_id = rs.getInt(1);
                    R.getUserCollection().forEach((u) -> {
                        // System.out.print("rdv id : " + rendezvous_id + " \nUser Id : " + u.getId());
                        addRelation(rendezvous_id, u.getId());
                    });
                }
            }
            // System.out.println("Rendez vous added successfully.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void addRelation(Integer idR, Integer idU) {
        try {
            String request = "INSERT INTO rendezvous_user (rendezvous_id, user_id) VALUES (?, ?)";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setInt(1, idR);
            pst.setInt(2, idU);
            pst.executeUpdate();
            // System.out.println("Relation between Rendezvous " + idR + " and User " +  idU + " added succssefully." );
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void update(Rendezvous R, Integer id) {
        try {
            String request = "UPDATE rendezvous SET daterv=?, end_at=?, Salle=?, Type=? WHERE id=?";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setDate(1, new java.sql.Date(R.getDaterv().getTime()));
            pst.setDate(2, new java.sql.Date(R.getEndAt().getTime()));
            pst.setInt(3, R.getSalle().getId());
            pst.setInt(4, R.getType().getId());
            pst.setInt(5, id);
            pst.executeUpdate();
            // System.out.println("Rendez vous " + R.getId() + " updated successfully.");

            R.getUserCollection().forEach((u) -> {
                // System.out.print("rdv id : " + rendezvous_id + " \nUser Id : " + u.getId());
                if (u.isSelected()) {
                    updateRelation(id, u.getId(), false);
                } else {
                    updateRelation(id, u.getId(), true);
                }
            });
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    private Boolean checkExist(Integer idR, Integer idU) {
        String checkQuery = "SELECT COUNT(*) FROM rendezvous_user WHERE rendezvous_id= ? AND user_id = ?";
        try {
            PreparedStatement pst = c.prepareStatement(checkQuery);
            pst.setInt(1, idR);
            pst.setInt(2, idU);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    
    private void updateRelation(Integer idR, Integer idU, Boolean remove) {
        Boolean doExist = checkExist(idR, idU);
        try {
            if (remove && doExist) {
                String request = "DELETE FROM rendezvous_user WHERE rendezvous_id = ? AND user_id = ?";
                PreparedStatement pst = c.prepareStatement(request);
                pst.setInt(1, idR);
                pst.setInt(2, idU);
                pst.executeUpdate();
                // System.out.println("Relation between Rendezvous " + idR + " and User " +  idU + " removed successfully." );
            } else if (!remove && !doExist) {
                String request = "INSERT INTO rendezvous_user (rendezvous_id, user_id) VALUES (?, ?)";
                PreparedStatement pst = c.prepareStatement(request);
                pst.setInt(1, idR);
                pst.setInt(2, idU);
                pst.executeUpdate();
                // System.out.println("Relation between Rendezvous " + idR + " and User " +  idU + " updated successfully." );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void remove(Integer id) {
        try {
            String request = "DELETE FROM rendezvous WHERE id=?";
            PreparedStatement pst = c.prepareStatement(request);
            pst.setInt(1, id);
            pst.executeUpdate();
            // System.out.println("Rendez vous " + id + " removed successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Rendezvous> showAll() {

        try {

            String request = "SELECT r.*, u.*, s.*, t.* FROM rendezvous r "
                    + "INNER JOIN rendezvous_user ru ON r.id = ru.rendezvous_id "
                    + "INNER JOIN user u ON  u.id = ru.user_id "
                    + "INNER JOIN salle s ON s.id = r.Salle "
                    + "INNER JOIN rendezvous_type t ON t.id = r.Type "
                    + "ORDER BY r.id";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(request);
            Map<Integer, Rendezvous> rendezvousMap = new HashMap<>();
            while (rs.next()) {
                int rendezvousId = rs.getInt("r.id");

                Rendezvous rendezvous = rendezvousMap.getOrDefault(rendezvousId,
                        new Rendezvous(rs.getInt("r.id"), rs.getTimestamp("r.daterv"), rs.getBoolean("r.rappel"), rs.getTimestamp("r.end_at"))
                );

                rendezvous.addUser(new User(
                        rs.getInt("u.id"), rs.getString("u.email"), rs.getString("u.password"), rs.getString("u.nom"), rs.getString("u.prenom"), rs.getBoolean("u.is_verified")
                ));
                rendezvous.setSalle(new Salle(
                        rs.getInt("s.id"), rs.getInt("s.numsa"), rs.getInt("s.etagesa"), rs.getString("s.typesa")
                ));
                rendezvous.setType(new RendezvousType(
                        rs.getInt("t.id"), rs.getString("t.type")
                ));

                rendezvousMap.put(rendezvousId, rendezvous);
            }

            List<Rendezvous> rendezvousList = new ArrayList<>(rendezvousMap.values());
            return rendezvousList;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

}
