/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

import java.util.Date;

/**
 *
 * @author
 */
public class ResetPasswordRequest {

    private Integer id;
    private String selector;
    private String hashedToken;
    private Date requestedAt;
    private Date expiresAt;
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
        return "entities.ResetPasswordRequest[ id=" + id + " ]";
    }

}
