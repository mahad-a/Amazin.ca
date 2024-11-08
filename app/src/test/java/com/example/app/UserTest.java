package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "testPassword");
    }

    @Test
    void testGettersAndSetters() {
        // Test initial values
        assertEquals("testUser", user.getUserName());
        assertEquals("testPassword", user.getPassword());

        // Set new values
        user.setUserName("newUser");
        user.setPassword("newPassword");
        user.setId(1L);

        // Test updated values
        assertEquals("newUser", user.getUserName());
        assertEquals("newPassword", user.getPassword());
        assertEquals(1L, user.getId());
    }

    @Test
    void testHomeMethod() {
        // Test the home() method
        assertEquals("loginUser", user.home());
    }

    @Test
    void testDefaultConstructor() {
        User emptyUser = new User();
        assertNull(emptyUser.getUserName());
        assertNull(emptyUser.getPassword());
        assertNull(emptyUser.getId());
    }

    @Test
    void testSetId() {
        user.setId(10L);
        assertEquals(10L, user.getId());
    }
}
