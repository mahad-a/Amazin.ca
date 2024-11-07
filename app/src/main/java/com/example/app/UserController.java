package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for managing user-related operations.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Default constructor.
     */
    public UserController() {}

    /**
     * Endpoint to register a new user.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return ResponseEntity with the created user and HTTP status
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String username, @RequestParam String password){
        try{
            // create new user and save it
            User newUser = new User(username, password);
            User savedUser = userRepository.save(newUser);
            System.out.println(username + " registered!");
            // return ResponseEntity with status
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for user login.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return ResponseEntity with the user if authentication is successful, or HTTP status code
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password){
        // find user by username
        User user = userRepository.findByUserName(username);

        // check password; can make it more secure by adding hashing i guess
        if(user != null && user.getPassword().equals(password)){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
