package com.nisum.userapi.serviceImpl;

import com.nisum.userapi.domain.entity.PhoneEntity;
import com.nisum.userapi.domain.request.UserRequest;
import com.nisum.userapi.domain.entity.UserEntity;
import com.nisum.userapi.exception.UserException;
import com.nisum.userapi.repository.UserRepository;
import com.nisum.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(final UserRequest userRequest) throws UserException {
        validEmail(userRequest.getEmail());

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setToken("token");
        userEntity.setLastLogin(userEntity.getCreated());
        userEntity.setActive(true);
        userEntity.setPhones(userRequest.getPhones().stream().map(p -> new PhoneEntity(userEntity, p.getNumber(), p.getCityCode(), p.getCountryCode(), LocalDateTime.now())).collect(Collectors.toSet()));
        return userRepository.save(userEntity);
    }

    /* Metodos Privados */
    private void validEmail(final String email) throws UserException {
        Optional<UserEntity> user = userRepository.findUserEntityByEmail(email);
        if (user.isPresent()) {
            throw new UserException("El correo ya registrado");
        }
    }

}
