package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginEntryController {

    @GetMapping("/loginEntry")
    public String loginEntry() {
        return "loginEntry";
    }

    
}
