package com.example.app;

import org.springframework.web.bind.annotation.GetMapping;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing a user in the system.
 */
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /**
     * Default constructor.
     */
    public User(){
        this.cart = new Cart();  
        this.cart.setUser(this);
    }


    
    /** 
     * @return String
     */
    @GetMapping("/home")
    public String home(){
        return "loginUser";
    }
    /**
     * Constructor to create a User with specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new Cart();  
        this.cart.setUser(this);
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName the username to set
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    public boolean registrationValidation(String password) {
        // Check if username or password is null or empty
        if (
            password == null || password.trim().isEmpty()) {
            return false;
        }
    
        // LUDS Validation Criteria
        boolean hasLength = password.length() >= 8; // Minimum 8 characters
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    
        // Return true only if ALL criteria are met
        return hasLength && 
               hasUppercase && 
               hasLowercase && 
               hasDigit && 
               hasSpecialChar;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public boolean setPassword(String password) {
        if (registrationValidation(password)){
            this.password = password;
            return true;
        }
        return false;
       
    }

    /**
     * Sets cart to user
     * @param cart cart to be set
     */
    public void setCart(Cart cart){
        this.cart = cart;

    }

    /**
     * Gets user's cart
     * @return user's cart
     */
    public Cart getCart(){
        return this.cart;
    }
}
