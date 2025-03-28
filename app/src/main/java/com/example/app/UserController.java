package com.example.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private BookInventory bookInventory;

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
    public ResponseEntity<Boolean> register(@RequestParam String username, @RequestParam String password){
        try{
            
            User newUser = new User();
            if (newUser.setPassword(password)){
                newUser.setUsername(username);
                User savedUser = userRepository.save(newUser);
                System.out.println(username + " registered!");
                userRepository.save(savedUser);
                return ResponseEntity.ok(true);
                
            }
            
            
          System.out.println("Use LUDS...");
          return ResponseEntity.ok(false);
            
            
        } catch(Exception e){
            System.out.println("ERROR");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginUser";
    }

    /**
     * Endpoint for user login.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return ResponseEntity with the user if authentication is successful, or HTTP status code
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        
        User user = userRepository.findByUsername(username);
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login Success!");
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/get")
    public ResponseEntity<User> getMethodName(@RequestParam String username) {
        Iterable<User> users = userRepository.findAll();

        for (User user : users){
            if (user.getUsername().equals(username)){
                return ResponseEntity.ok(user);
            }
        }
        return null;
    }

    /**
     * Verify user/admin password
     * @param username username of the user/admin
     * @param password passsword of the user/admin
     * @return ResponseEntity with the user if verification is successful, or HTTP status code
     */
    @PostMapping("/verifyPassword")
    public ResponseEntity<String> verifyPassword(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return ResponseEntity.ok("Password verified successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
    }


    @GetMapping("/bookrecommendations")
    public ResponseEntity<List<Book>> recommendedBooks(@RequestParam String username) {
        User user = userRepository.findByUsername(username);

        List<Book> userBooks = user.getCart().getBooks();
        Set<String> authors = new HashSet<>();
        for (Book book : userBooks) {
            authors.add(book.getAuthor());
        }
        System.out.println("authours" + authors);
        List<Book> recommendedBooks = bookInventory.findBooksByAuthorIn(authors);
        System.out.println("Recommended Books: " + recommendedBooks);
        recommendedBooks.removeAll(userBooks);

        

        return ResponseEntity.ok(recommendedBooks);
       
        
    }

}
