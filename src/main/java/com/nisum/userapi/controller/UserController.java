package com.nisum.userapi.controller;

import com.nisum.userapi.domain.response.UserResponse;
import com.nisum.userapi.domain.entity.UserEntity;
import com.nisum.userapi.domain.request.UserRequest;
import com.nisum.userapi.domain.response.ApiResponse;
import com.nisum.userapi.exception.UserException;
import com.nisum.userapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Registra un usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> save(@Valid @RequestBody final UserRequest userRequest) throws UserException{
        UserEntity userCreated = userService.save(userRequest);
        UserResponse userResponse = new UserResponse(userCreated);

        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setBody(userResponse);
        response.setMessage("Usuario registrado con éxito.");
        response.setStatusCode(HttpStatus.CREATED.value());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
