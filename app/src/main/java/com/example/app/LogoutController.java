
package com.example.app;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController{

    /**
     * Directs to log out page
     * @return log out html
     */
    @GetMapping("/logout")
    public String logout() {
        return "loginEntry"; // prompt user to re-log in
    }
}
