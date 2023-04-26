/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author SONDESH
 */
public class user {
    private int id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String roles; 
    public user(){}
    
    public user(int id, String nom, String prenom, String password, String email,String roles) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public user(String nom, String prenom, String password, String email,String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String roles) {
        this.email = email;
    }
     public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", roles=" + roles + ", password=" + password + ", email=" + email + '}';
    }
    
    
    
}
