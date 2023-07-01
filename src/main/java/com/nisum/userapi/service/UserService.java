package com.nisum.userapi.service;

import com.nisum.userapi.domain.request.UserRequest;
import com.nisum.userapi.domain.entity.UserEntity;
import com.nisum.userapi.exception.InvalidPasswordException;
import com.nisum.userapi.exception.UserException;

public interface UserService {
    UserEntity save(UserRequest userRequest) throws UserException, InvalidPasswordException;
}
