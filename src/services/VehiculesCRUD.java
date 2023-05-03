/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vehicules;
import entities.Categorievehicules;
import utils.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class VehiculesCRUD implements InterfaceVehiculesCRUD {

    Statement ste;
    Connection cnx;

    public VehiculesCRUD() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void Ajouter(Vehicules t) {
        try {
            // Si la valeur existe dans la liste, vous pouvez exécuter la requête INSERT
            String insertQuery = "INSERT INTO `vehicules` ( `nomvh`, `dispovh`, `etatvh`, `descvh`,`imagesvh`,`date`,`Categoriesvehicules`) "
                    + "VALUES ('" + t.getNomvh() + "','" + t.isDispovh() + "','" + t.getEtatvh() + "','" + t.getDescvh() + "','" + t.getImagesvh() + "','" + t.getDate() + "','" + t.getCatv_id().getId() + "')";
            Statement insertStm = cnx.createStatement();
            insertStm.executeUpdate(insertQuery);

        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du Vehicule : " + ex.getMessage());
        }
    }

    @Override
    public List<Vehicules> Afficher() {
        List<Vehicules> Vehicules = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `vehicules` ";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Vehicules p = new Vehicules();
                p.setId(rs.getInt(1));

                p.setNomvh(rs.getString(2));

                p.setDispovh(rs.getInt(3));

                p.setEtatvh(rs.getString(4));

                p.setDescvh(rs.getString(5));

                p.setImagesvh(rs.getString(6));

                p.setDate(rs.getString(7));

                int Categoriesvehicules = rs.getInt(8); // Modifier pour récupérer le stockcat_id à partir du ResultSet

                // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
                Categorievehicules ca = new Categorievehicules();

                ca.setTypecatv(GetDoctorById(Categoriesvehicules).getTypecatv()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
                p.setCatv_id(ca);
                Vehicules.add(p);

            }

            return Vehicules;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Vehicules;

    }

    @Override
    public Categorievehicules GetDoctorById(int Id) {
        Categorievehicules h = new Categorievehicules();
        String req = "SELECT typecatv FROM categoriesvehicules WHERE id = " + Id;

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);

            if (rs.next()) {

                h.setTypecatv(rs.getString(1));
            } else {
                System.out.println("Aucune catégorie de vehicule trouvée pour l'ID " + Id);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de la catégorie de vehicule pour l'ID " + Id + ": " + ex.getMessage());
        }

        return h;
    }

    @Override
    public void Supprimer(int id) {
        try {
            String qry = "DELETE from vehicules where id = " + id + ";";

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            String recipient = "mouhamedkhaled.baoueb@esprit.tn";
            try {
                utils.Mailvehicules.envoyer(recipient);
                System.out.println("Le message a été envoyé avec succès.");
            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Vehicules e, int id) {
        try {
            String req = "UPDATE vehicules SET  nomvh=?, dispovh=?, etatvh=?, descvh=?,imagesvh=? , date=?,Categoriesvehicules=? WHERE id=?";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, e.getNomvh());

            pre.setInt(2, e.isDispovh());
            pre.setString(3, e.getEtatvh());
            pre.setString(4, e.getDescvh());

            pre.setString(5, e.getImagesvh());
            pre.setString(6, e.getDate());

            if (e.getCatv_id() != null) {
                pre.setInt(7, e.getCatv_id().getId());
            } else {
                // Handle the case when the stockcat_id is null
            }

            pre.setInt(8, id);

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("vehicule vous modifié !");
    }

    @Override
    public List<Vehicules> AfficherParId(int id) {
        List<Vehicules> Vehicules = new ArrayList<>();
        try {
            String qry = "Select * from vehicules  where id =" + id;
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Vehicules p = new Vehicules();
                p.setId(rs.getInt(1));

                p.setNomvh(rs.getString(2));

                p.setDispovh(rs.getInt(3));

                p.setEtatvh(rs.getString(4));

                p.setDescvh(rs.getString(5));

                p.setImagesvh(rs.getString(6));

                p.setDate(rs.getString(7));

                int Categoriesvehicules = rs.getInt(8); // Modifier pour récupérer le stockcat_id à partir du ResultSet

                // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
                Categorievehicules ca = new Categorievehicules();

                ca.setTypecatv(GetDoctorById(Categoriesvehicules).getTypecatv()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
                p.setCatv_id(ca);
                Vehicules.add(p);
            }
            return Vehicules;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Vehicules;

    }

    @Override
    public List<Vehicules> AfficherParNom() {
        List<Vehicules> Vehicules = new ArrayList<>();
        try {
            String qry = "Select * from vehicules ";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Vehicules p = new Vehicules();
                p.setId(rs.getInt(1));

                p.setNomvh(rs.getString(2));

                p.setDispovh(rs.getInt(3));

                p.setEtatvh(rs.getString(4));

                p.setDescvh(rs.getString(5));

                p.setImagesvh(rs.getString(6));

                p.setDate(rs.getString(7));

                int Categoriesvehicules = rs.getInt(8); // Modifier pour récupérer le stockcat_id à partir du ResultSet

                // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
                Categorievehicules ca = new Categorievehicules();

                ca.setTypecatv(GetDoctorById(Categoriesvehicules).getTypecatv()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
                p.setCatv_id(ca);

                Vehicules.add(p);
            }
            return Vehicules;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Vehicules;

    }

}
