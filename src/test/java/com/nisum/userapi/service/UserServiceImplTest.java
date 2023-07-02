package com.nisum.userapi.service;

import com.nisum.userapi.domain.dto.PhoneDto;
import com.nisum.userapi.domain.entity.PhoneEntity;
import com.nisum.userapi.domain.entity.UserEntity;
import com.nisum.userapi.domain.request.UserRequest;
import com.nisum.userapi.exception.InvalidPasswordException;
import com.nisum.userapi.exception.UserException;
import com.nisum.userapi.repository.UserRepository;
import com.nisum.userapi.serviceImpl.UserServiceImpl;
import com.nisum.userapi.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        String passwordRegexp = "^[a-zA-Z0-9]{8,}$";
        userService = new UserServiceImpl(userRepository, jwtTokenUtil, passwordRegexp);
    }

    @Test
    void save_ValidUserRequest_ReturnsUserEntity() throws UserException, InvalidPasswordException {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("johndoe@example.com");
        userRequest.setPassword("aA123456");
        userRequest.setPhones(new HashSet<>(Arrays.asList(new PhoneDto("12345678", "123", "1"))));

        when(userRepository.findUserEntityByEmail(userRequest.getEmail())).thenReturn(Optional.empty());
        when(jwtTokenUtil.generateToken(userRequest.getEmail())).thenReturn("testToken");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserEntity savedUser = userService.save(userRequest);

        // Assert
        assertNotNull(savedUser);
        assertEquals(userRequest.getName(), savedUser.getName());
        assertEquals(userRequest.getEmail(), savedUser.getEmail());
        assertTrue(new BCryptPasswordEncoder().matches(userRequest.getPassword(), savedUser.getPassword()));
        assertNotNull(savedUser.getCreated());
        assertEquals("testToken", savedUser.getToken());
        assertEquals(savedUser.getCreated(), savedUser.getLastLogin());
        assertTrue(savedUser.getActive());
        assertNotNull(savedUser.getPhones());
        assertEquals(1, savedUser.getPhones().size());
        PhoneEntity phoneEntity = savedUser.getPhones().iterator().next();
        assertEquals("12345678", phoneEntity.getNumber());
        assertEquals("123", phoneEntity.getCityCode());
        assertEquals("1", phoneEntity.getCountryCode());
        assertNotNull(phoneEntity.getCreated());
    }

    @Test
    void save_DuplicateEmail_ThrowsUserException() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("johndoe@example.com");

        when(userRepository.findUserEntityByEmail(userRequest.getEmail())).thenReturn(Optional.of(new UserEntity()));

        // Act & Assert
        assertThrows(UserException.class, () -> userService.save(userRequest));
    }

    @Test
    void save_InvalidPassword_ThrowsInvalidPasswordException() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("johndoe@example.com");
        userRequest.setPassword("123");
        userRequest.setPhones(new HashSet<>(Arrays.asList(new PhoneDto("12345678", "123", "1"))));

        // Act & Assert
        assertThrows(InvalidPasswordException.class, () -> userService.save(userRequest));
    }
}
