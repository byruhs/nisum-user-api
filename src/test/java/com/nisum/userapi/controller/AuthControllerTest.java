package com.nisum.userapi.controller;

import com.nisum.userapi.domain.request.AuthRequest;
import com.nisum.userapi.domain.response.AuthResponse;
import com.nisum.userapi.serviceImpl.JwtUserDetailsService;
import com.nisum.userapi.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService userDetailsService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAuthenticationToken_ValidCredentials_ReturnsToken() throws Exception {
        // Arrange
        AuthRequest authRequest = new AuthRequest("testUser", "testPassword");
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(authRequest.getUsername())).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(userDetails.getUsername())).thenReturn("testToken");

        // Act
        ResponseEntity<?> response = authController.createAuthenticationToken(authRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof AuthResponse);
        AuthResponse authResponse = (AuthResponse) response.getBody();
        assertEquals("testToken", authResponse.getToken());
    }

    @Test
    void createAuthenticationToken_InvalidCredentials_ThrowsException() throws Exception {
        // Arrange
        AuthRequest authRequest = new AuthRequest("testUser", "testPassword");
        doThrow(new BadCredentialsException("INVALID_CREDENTIALS")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Act & Assert
        assertThrows(Exception.class, () -> authController.createAuthenticationToken(authRequest));
    }

    @Test
    void createAuthenticationToken_DisabledUser_ThrowsException() throws Exception {
        // Arrange
        AuthRequest authRequest = new AuthRequest("testUser", "testPassword");
        doThrow(new DisabledException("USER_DISABLED")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Act & Assert
        assertThrows(Exception.class, () -> authController.createAuthenticationToken(authRequest));
    }
}