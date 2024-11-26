package com.example.app;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    // Test for the loginPage method
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/user/loginPage"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("loginUser");
    }

    // Test for the register method (successful registration)
    @Test
    public void testRegister_Success() {
        User user = new User("newUser", "password123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<Boolean> response = userController.register("newUser", "password123");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    // Test for the register method (registration failure)
    @Test
    public void testRegister_Failure() {
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        ResponseEntity<Boolean> response = userController.register("newUser", "password123");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Test for the login method (successful login)
    @Test
    public void testLogin_Success() {
        User user = new User("testUser", "password123");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<User> response = userController.login("testUser", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    // Test for the login method (failed login)
    @Test
    public void testLogin_Failure() {
        User user = new User("testUser", "wrongPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<User> response = userController.login("testUser", "password123");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    // Test for the get method (successful)
    @Test
    public void testGet_Success() {
        User user = new User("testUser", "password123");
        when(userRepository.findAll()).thenReturn(java.util.Arrays.asList(user));

        ResponseEntity<User> response = userController.getMethodName("testUser");

        assertEquals(user, response.getBody());
    }

    // Test for the get method (user not found)
    @Test
    public void testGet_UserNotFound() {
        when(userRepository.findAll()).thenReturn(java.util.Collections.emptyList());

        ResponseEntity<User> response = userController.getMethodName("nonexistentUser");

        assertEquals(null, response);
    }

    // Test for verifyPassword method (successful verification)
    @Test
    public void testVerifyPassword_Success() {
        User user = new User("testUser", "password123");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<String> response = userController.verifyPassword("testUser", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password verified successfully", response.getBody());
    }

    // Test for verifyPassword method (failed verification)
    @Test
    public void testVerifyPassword_Failure() {
        User user = new User("testUser", "wrongPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<String> response = userController.verifyPassword("testUser", "password123");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Incorrect username or password", response.getBody());
    }
}