package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginEntryController {

    
    /** 
     * The Entry point to either login as a user or admin
     * @return String
     */
    @GetMapping("/loginEntry")
    public String loginEntry() {
        return "loginEntry";
    }

    
}
