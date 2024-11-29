package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;
    
    
    /** 
     * Default home of the Admin 
     * @return String
     */
    @GetMapping("/home")
    public String displayAdminPage() {
        return "admin";
    }
    /**
     * Login Page for Admin
     * @return loginAdmin.html
     */

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginAdmin";
    }
    
    /**
     * Admin Login Verification
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String strippedUsername = username.strip();
        System.out.println(strippedUsername);
        Admin admin = adminRepository.findByUsername(strippedUsername);
    
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("Login Success!");
            adminRepository.save(admin);
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }
    /**
     * Admin Registration 
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestParam String username, @RequestParam String password){
        try{
            username.strip();
            
            Admin newAdmin = new Admin();
            if (newAdmin.setPassword(password)){
                System.out.println(username + " registered!");
                adminRepository.save(newAdmin);
                return ResponseEntity.ok(true);
            }

            return ResponseEntity.ok(false);
            
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
