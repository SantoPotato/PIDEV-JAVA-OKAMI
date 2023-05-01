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

    private Integer id_user;
    private String first_Name;
    private String last_Name;
    private String user_Name;
    private String password;
    private String email;
    private Integer phone_number;
    private String gender;
    private String role;
    private Boolean selected;

    public User() {
    }

    public User(Integer id, String first_Name, String last_Name, String user_Name, String email) {
        this.id_user = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.user_Name = user_Name;
        this.email = email;
    }

    public User(String first_Name, String last_Name, String user_Name, String password, String email) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.user_Name = user_Name;
        this.password = password;
        this.email = email;
    }

    public User(Integer id) {
        this.id_user = id;
    }

    public User(Integer id, String nom, String prenom) {
        this.id_user = id;
        this.last_Name = nom;
        this.first_Name = prenom;
        this.selected = false;
    }

    public User(Integer id_user, String first_Name, String last_Name, String user_Name, String password, String email, int phone_number, String gender, String role) {
        this.id_user = id_user;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.user_Name = user_Name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
        this.role = role;
    }
    //liste des evenement accedeé par l'utilisateur

    //private List<Evenement> evenementList;
    public User(String first_Name, String last_Name, String user_Name, String password, String email, int phone_number, String gender) {

        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.user_Name = user_Name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;

    }

    public User(int id_user, String first_Name, String last_Name, String user_Name, String password) {
        this.id_user = id_user;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.user_Name = user_Name;
        this.password = password;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public String getUser_Name() {
        return user_Name;
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

    public String getRole() {
        return role;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
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

    public void setRole(String role) {
        this.role = role;
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
        hash += (id_user != null ? id_user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.id_user == null && other.id_user != null) || (this.id_user != null && !this.id_user.equals(other.id_user)));
    }

    @Override
    public String toString() {
        return last_Name + " " + first_Name;
    }

}
