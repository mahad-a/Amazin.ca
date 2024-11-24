package com.example.app;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

public class SettingsControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private SettingsController settingsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(settingsController).build();
    }

    // Test for the settings page method
    @Test
    public void testSettingsPage() throws Exception {
        mockMvc.perform(get("/settings/settingsEntry"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("settingsEntry");
    }

    // Test for changeUsername method (successful user change)
    @Test
    public void testChangeUsername_UserSuccess() {
        User user = new User("oldUsername", "password123");
        when(userRepository.findByUsername("oldUsername")).thenReturn(user);

        ResponseEntity<String> response = settingsController.changeUsername("oldUsername", "newUsername");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Username updated successfully!", response.getBody());
        verify(userRepository).save(user);
    }

    // Test for changeUsername method (successful admin change)
    @Test
    public void testChangeUsername_AdminSuccess() {
        Admin admin = new Admin("oldUsername", "password123");
        when(adminRepository.findByUsername("oldUsername")).thenReturn(admin);

        ResponseEntity<String> response = settingsController.changeUsername("oldUsername", "newUsername");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Username updated successfully!", response.getBody());
        verify(adminRepository).save(admin);
    }

    // Test for changeUsername method (user/admin not found)
    @Test
    public void testChangeUsername_NotFound() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(null);
        when(adminRepository.findByUsername("nonexistent")).thenReturn(null);

        ResponseEntity<String> response = settingsController.changeUsername("nonexistent", "newUsername");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User or Admin not found.", response.getBody());
    }

    // Test for changePassword method (successful user change)
    @Test
    public void testChangePassword_UserSuccess() {
        User user = new User("testUser", "currentPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<String> response = settingsController.changePassword("testUser", "currentPassword", "newPassword");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password updated successfully!", response.getBody());
        verify(userRepository).save(user);
    }

    // Test for changePassword method (successful admin change)
    @Test
    public void testChangePassword_AdminSuccess() {
        Admin admin = new Admin("testAdmin", "currentPassword");
        when(adminRepository.findByUsername("testAdmin")).thenReturn(admin);

        ResponseEntity<String> response = settingsController.changePassword("testAdmin", "currentPassword", "newPassword");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password updated successfully!", response.getBody());
        verify(adminRepository).save(admin);
    }

    // Test for changePassword method (incorrect password)
    @Test
    public void testChangePassword_IncorrectPassword() {
        User user = new User("testUser", "correctPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<String> response = settingsController.changePassword("testUser", "wrongPassword", "newPassword");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect username or password.", response.getBody());
    }

    // Test for deleteAccount method (successful user deletion)
    @Test
    public void testDeleteAccount_UserSuccess() {
        User user = new User("testUser", "password123");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<String> response = settingsController.deleteAccount("testUser", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account has been deleted.", response.getBody());
        verify(userRepository).delete(user);
    }

    // Test for deleteAccount method (successful admin deletion)
    @Test
    public void testDeleteAccount_AdminSuccess() {
        Admin admin = new Admin("testAdmin", "password123");
        when(adminRepository.findByUsername("testAdmin")).thenReturn(admin);

        ResponseEntity<String> response = settingsController.deleteAccount("testAdmin", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account has been deleted.", response.getBody());
        verify(adminRepository).delete(admin);
    }

    // Test for deleteAccount method (incorrect credentials)
    @Test
    public void testDeleteAccount_IncorrectCredentials() {
        User user = new User("testUser", "correctPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        ResponseEntity<String> response = settingsController.deleteAccount("testUser", "wrongPassword");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect username or password, account deletion aborted.", response.getBody());
    }
}