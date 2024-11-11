package com.example.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Admin Class (Admin Entity) representing Admin user in the system
 */
@Entity
public class Admin {

    String password;
    String username;

    /**
     * Constructor
     */
    public Admin(){

    }
    /**
     * Param Constructor 
     * @param username
     * @param password
     */
    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }

    
    
    /** 
     * return Adming password
     * @return String
     */
    public String getPassword() {
        return this.password;
    }
    
    
    /** 
     * set Admin Password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /** 
     * return Admin user
     * @return String
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * set Admin user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Admin Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long adminId;

    /**
     * return Admin id
     * @return
     */
    public Long getAdminId() {
        return this.adminId;
    }
    /**
     * set Admin id
     * @param adminId
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    
}
