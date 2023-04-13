/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

import java.util.Collection;

/**
 *
 * @author
 */
public class User {

    private Integer id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private boolean isVerified;
    private Collection<Rendezvous> rendezvousCollection;
    private Collection<ResetPasswordRequest> resetPasswordRequestCollection;
    private Roleuser roleId;
    private Collection<Historique> historiqueCollection;

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

    public User(Integer id, String email, String password, String nom, String prenom, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.isVerified = isVerified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Collection<Rendezvous> getRendezvousCollection() {
        return rendezvousCollection;
    }

    public void setRendezvousCollection(Collection<Rendezvous> rendezvousCollection) {
        this.rendezvousCollection = rendezvousCollection;
    }

    public Collection<ResetPasswordRequest> getResetPasswordRequestCollection() {
        return resetPasswordRequestCollection;
    }

    public void setResetPasswordRequestCollection(Collection<ResetPasswordRequest> resetPasswordRequestCollection) {
        this.resetPasswordRequestCollection = resetPasswordRequestCollection;
    }

    public Roleuser getRoleId() {
        return roleId;
    }

    public void setRoleId(Roleuser roleId) {
        this.roleId = roleId;
    }

    public Collection<Historique> getHistoriqueCollection() {
        return historiqueCollection;
    }

    public void setHistoriqueCollection(Collection<Historique> historiqueCollection) {
        this.historiqueCollection = historiqueCollection;
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
