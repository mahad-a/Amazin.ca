package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CartController {

    @GetMapping("/cart")
    public String displayCart() {
        return "cart";
    }
    
    
}
