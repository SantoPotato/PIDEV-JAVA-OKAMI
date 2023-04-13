/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "reset_password_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResetPasswordRequest.findAll", query = "SELECT r FROM ResetPasswordRequest r")
    , @NamedQuery(name = "ResetPasswordRequest.findById", query = "SELECT r FROM ResetPasswordRequest r WHERE r.id = :id")
    , @NamedQuery(name = "ResetPasswordRequest.findBySelector", query = "SELECT r FROM ResetPasswordRequest r WHERE r.selector = :selector")
    , @NamedQuery(name = "ResetPasswordRequest.findByHashedToken", query = "SELECT r FROM ResetPasswordRequest r WHERE r.hashedToken = :hashedToken")
    , @NamedQuery(name = "ResetPasswordRequest.findByRequestedAt", query = "SELECT r FROM ResetPasswordRequest r WHERE r.requestedAt = :requestedAt")
    , @NamedQuery(name = "ResetPasswordRequest.findByExpiresAt", query = "SELECT r FROM ResetPasswordRequest r WHERE r.expiresAt = :expiresAt")})
public class ResetPasswordRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "selector")
    private String selector;
    @Basic(optional = false)
    @Column(name = "hashed_token")
    private String hashedToken;
    @Basic(optional = false)
    @Column(name = "requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedAt;
    @Basic(optional = false)
    @Column(name = "expires_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(Integer id) {
        this.id = id;
    }

    public ResetPasswordRequest(Integer id, String selector, String hashedToken, Date requestedAt, Date expiresAt) {
        this.id = id;
        this.selector = selector;
        this.hashedToken = hashedToken;
        this.requestedAt = requestedAt;
        this.expiresAt = expiresAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getHashedToken() {
        return hashedToken;
    }

    public void setHashedToken(String hashedToken) {
        this.hashedToken = hashedToken;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof ResetPasswordRequest)) {
            return false;
        }
        ResetPasswordRequest other = (ResetPasswordRequest) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.ResetPasswordRequest[ id=" + id + " ]";
    }
    
}
