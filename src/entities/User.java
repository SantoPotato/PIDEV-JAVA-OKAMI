/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author abirk
 */
public class User {

    private Integer id;
    private UserRole role_id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private Boolean is_verified;

    private String username;
    private Integer phone_number;
    private String gender;

    private Boolean selected;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.selected = false;
    }

    public User(String nom, String prenom, String email, String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(UserRole role_id, String email, String password, String nom, String prenom, String username, Integer phone_number, String gender) {
        this.role_id = role_id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.phone_number = phone_number;
        this.gender = gender;
    }

    public User(Integer id, UserRole role_id, String email, String password, String nom, String prenom, Boolean is_verified, String username, Integer phone_number, String gender) {
        this.id = id;
        this.role_id = role_id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.is_verified = is_verified;
        this.username = username;
        this.phone_number = phone_number;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public String getGender() {
        return gender;
    }

    public UserRole getRole() {
        return role_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(UserRole role_id) {
        this.role_id = role_id;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

}
