/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 */
@Entity
@Table(name = "[user]")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByNom", query = "SELECT u FROM User u WHERE u.nom = :nom")
    , @NamedQuery(name = "User.findByPrenom", query = "SELECT u FROM User u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "User.findByIsVerified", query = "SELECT u FROM User u WHERE u.isVerified = :isVerified")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "is_verified")
    private boolean isVerified;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Rendezvous> rendezvousCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ResetPasswordRequest> resetPasswordRequestCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Roleuser roleId;
    @OneToMany(mappedBy = "userId")
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

    @XmlTransient
    public Collection<Rendezvous> getRendezvousCollection() {
        return rendezvousCollection;
    }

    public void setRendezvousCollection(Collection<Rendezvous> rendezvousCollection) {
        this.rendezvousCollection = rendezvousCollection;
    }

    @XmlTransient
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

    @XmlTransient
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
