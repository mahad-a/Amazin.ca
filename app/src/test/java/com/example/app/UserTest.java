package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Firas", "password123");
    }

    @Test
    void testGettersAndSetters() {
        // Test initial values
        assertEquals("Firas", user.getUsername());
        assertEquals("password123", user.getPassword());

        // Set new values
        user.setUsername("Firas");
        user.setPassword("password123");
        user.setId(1L);

        // Test updated values
        assertEquals("Firas", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(1L, user.getId());
    }

    @Test
    void testHomeMethod() {
        assertEquals("loginUser", user.home());
    }

    @Test
    void testDefaultConstructor() {
        User emptyUser = new User();
        assertNull(emptyUser.getUsername());
        assertNull(emptyUser.getPassword());
        assertNull(emptyUser.getId());
    }

    @Test
    void testSetId() {
        user.setId(10L);
        assertEquals(10L, user.getId());
    }
}
