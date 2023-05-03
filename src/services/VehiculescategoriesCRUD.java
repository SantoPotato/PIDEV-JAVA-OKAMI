/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorievehicules;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import utils.ConnectionDB;

/**
 *
 *
 */
public class VehiculescategoriesCRUD implements InterfaceCategorievehicules {

    Connection cnx;

    public VehiculescategoriesCRUD() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void Ajouterc(Categorievehicules t) {
        try {
            String qry = "INSERT INTO `categoriesvehicules`(`id`,`typecatv`) VALUES ('" + t.getId() + "','" + t.getTypecatv() + "')";

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Categorievehicules> Afficherc() {
        List<Categorievehicules> Categorievehicules = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `categoriesvehicules` ";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Categorievehicules p = new Categorievehicules();

                p.setTypecatv(rs.getString("typecatv"));
                p.setId(rs.getInt(1));

                Categorievehicules.add(p);

            }
            return Categorievehicules;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categorievehicules;

    }

    @Override
    public void Supprimerc(int id) {
        try {
            String qry = "DELETE from categoriesvehicules where id = " + id + ";";

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorievehicules t, int id) {
        try {
            String qry = "UPDATE categoriesvehicules SET `typecatv`='" + t.getTypecatv() + "' WHERE id=" + t.getId() + ";";

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorievehicules> AffichercParId(int id) {
        List<Categorievehicules> Categorievehicules = new ArrayList<>();
        try {
            String qry = "Select * from categoriesvehicules  where id =" + id;
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Categorievehicules p = new Categorievehicules();
                p.setId(rs.getInt(1));
                p.setTypecatv(rs.getString(2));

                Categorievehicules.add(p);

            }
            return Categorievehicules;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categorievehicules;

    }

    @Override
    public List<Categorievehicules> AffichercParNom() {
        List<Categorievehicules> Categorievehicules = new ArrayList<>();
        try {
            String qry = "Select * from categoriesvehicules  where typecat =?";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Categorievehicules p = new Categorievehicules();
                p.setId(rs.getInt(1));
                p.setTypecatv(rs.getString(2));

                Categorievehicules.add(p);

            }
            return Categorievehicules;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categorievehicules;

    }
}
