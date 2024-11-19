package com.example.app;

import org.springframework.web.bind.annotation.GetMapping;

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
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Cart cart;

    /**
     * Default constructor.
     */
    public User(){}


    
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

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setCart(Cart cart){
        this.cart = cart;

    }

    public Cart getCart(){
        return this.cart;
    }
}
