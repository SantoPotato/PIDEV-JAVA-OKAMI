package vehicules.services;
import vehicules.entities.Categorie;
import vehicules.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vehicules.intefaces.InterfaceCategorie;

public class CategoriesCRUD implements InterfaceCategorie {

 @Override
    public void ajouterCategorie(Categorie r) {
    try {

            String requete1 = "INSERT INTO categoriesvehicules(typecatv) VALUES(?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, r.getTypecatv());
            
            pst.executeUpdate();
            System.out.println("Categorie ajoutee avec succes !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifierCategorie(Categorie r) {
        try {
            String requete4;
            requete4 = " UPDATE categoriesvehicules SET typecatv=? WHERE id=?" ;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1, r.getTypecatv());
            pst.setInt(2, r.getId());
            pst.executeUpdate();
            System.out.println("Categorie modifiee");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM categoriesvehicules WHERE id = " + id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie supprimee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> afficherCategorie() {
       ArrayList<Categorie> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM categoriesvehicules";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            
              while (rs.next()) {
                Categorie r = new Categorie(rs.getInt("id"), rs.getString("typecatv"));
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        return myList;        
        
        
        
    }

    
}
