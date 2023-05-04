/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

/**
 *
 * @author ilyes
 */
public class UserRole {
    
    private Integer id;
    private String role;
    
    public UserRole(){
    }
    
    public UserRole(Integer id) {
        this.id = id;
    }
    
    public UserRole(Integer id, String role) {
        this.id = id;
        this.role = role;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return role;
    }
    
}
