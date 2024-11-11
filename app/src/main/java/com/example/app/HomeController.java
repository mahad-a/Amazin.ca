package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    
    /** 
     * Return's the URL to the homepage of the webapp
     * @return String
     */
    @GetMapping("/")
    public String home(){
        return "index";
    }
    
}
