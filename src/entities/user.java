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
    
    public user(){}
    
    public user(int id, String nom, String prenom, String password, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
    }

    public user(String nom, String prenom, String password, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + '}';
    }
    
    
    
}
