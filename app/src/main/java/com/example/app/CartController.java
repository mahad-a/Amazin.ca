package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CartController {

    
    /** 
     * Return's the Cart page
     * @return String
     */
    @GetMapping("/cart")
    public String displayCart() {
        return "cart";
    }
    
    
}
