package com.example.app;

import com.example.app.AdminController;
import com.example.app.AdminRepository;
import com.example.app.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    // Test for the displayAdminPage method
    @Test
    public void testDisplayAdminPage() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("admin");
    }

    // Test for the loginPage method
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/admin/loginPage"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("loginAdmin");
    }

    // Test for the login method (successful login)
    @Test
    public void testLogin_Success() {
        Admin admin = new Admin("adminUser", "password123");
        when(adminRepository.findByUsername("adminUser")).thenReturn(admin);

        ResponseEntity<String> response = adminController.login("adminUser", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful!", response.getBody());
    }

    // Test for the login method (failed login)
    @Test
    public void testLogin_Failure() {
        Admin admin = new Admin("adminUser", "wrongPassword");
        when(adminRepository.findByUsername("adminUser")).thenReturn(admin);

        ResponseEntity<String> response = adminController.login("adminUser", "password123");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials.", response.getBody());
    }

    // Test for the register method (successful registration)
    @Test
    public void testRegister_Success() {
        Admin admin = new Admin("newUser", "password123");
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        ResponseEntity<Boolean> response = adminController.register("newUser", "password123");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    // Test for the register method (registration failure)
    @Test
    public void testRegister_Failure() {
        when(adminRepository.save(any(Admin.class))).thenThrow(new RuntimeException());

        ResponseEntity<Boolean> response = adminController.register("newUser", "password123");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
