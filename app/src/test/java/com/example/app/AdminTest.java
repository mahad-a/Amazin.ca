package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin();
    }

    @Test
    void testSetAndGetUsername() {
        admin.setUsername("FirasArunZarifIlyaasMahad");
        assertEquals("FirasArunZarifIlyaasMahad", admin.getUsername());
    }

    // @Test
    // void testSetAndGetPassword() {
    //     admin.setPassword("password123");
    //     assertEquals("password123", admin.getPassword());
    // }

    @Test
    void testSetAndGetAdminId() {
        admin.setAdminId(1L);
        assertEquals(1L, admin.getAdminId());
    }

    @Test
    void testDefaultAdminIdIsNull() {
        assertNull(admin.getAdminId());
    }

    @Test
    void testDefaultUsernameIsNull() {
        assertNull(admin.getUsername());
    }

    @Test
    void testDefaultPasswordIsNull() {
        assertNull(admin.getPassword());
    }
}
