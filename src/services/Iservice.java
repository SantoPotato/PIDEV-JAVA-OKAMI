package services;

import entities.User;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author abdelazizmezri
 */
public interface Iservice <T>{
  public void ajouter(T user) throws SQLException;
   public void supprimer(int id) throws SQLException;
   public void modifier(String s,String s2,String s3,String s4,int s5 ,String s6,String s7,int id) throws SQLException;
   public User afficher(int id) throws SQLException;
   public List< T> getAll();
   
}
