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
    
    @GetMapping("/home")
    public String displayAdminPage() {
        return "admin";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginAdmin";
    }
    

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Iterable<Admin> admins = adminRepository.findAll();
    
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("Login Success!");
                return ResponseEntity.ok("Login successful!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }
    

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestParam String username, @RequestParam String password){
        try{
            
            Admin newAdmin = new Admin(username, password);
            Admin savedAdmin = adminRepository.save(newAdmin);
            System.out.println(username + " registered!");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
