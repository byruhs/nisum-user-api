package com.nisum.userapi.service;

import com.nisum.userapi.serviceImpl.JwtUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class JwtUserDetailsServiceTest {

    private JwtUserDetailsService jwtUserDetailsService;

    @BeforeEach
    void setup() {
        String defaultUsername = "testUser";
        String defaultPassword = "testPassword";
        jwtUserDetailsService = new JwtUserDetailsService(defaultUsername, defaultPassword);
    }

    @Test
    void loadUserByUsername_ValidUsername_ReturnsUserDetails() {
        // Arrange
        String username = "testUser";

        // Act
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
    }

    @Test
    void loadUserByUsername_InvalidUsername_ThrowsException() {
        // Arrange
        String username = "nonExistentUser";

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(username));
    }
}
