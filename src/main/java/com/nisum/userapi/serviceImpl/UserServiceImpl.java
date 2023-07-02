package com.nisum.userapi.serviceImpl;

import com.nisum.userapi.domain.entity.PhoneEntity;
import com.nisum.userapi.domain.request.UserRequest;
import com.nisum.userapi.domain.entity.UserEntity;
import com.nisum.userapi.exception.InvalidPasswordException;
import com.nisum.userapi.exception.UserException;
import com.nisum.userapi.repository.UserRepository;
import com.nisum.userapi.service.UserService;
import com.nisum.userapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String paswordRegexp;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, JwtTokenUtil jwtTokenUtil, @Value("${pasword.regexp}") String paswordRegexp) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.paswordRegexp = paswordRegexp;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserEntity save(final UserRequest userRequest) throws UserException, InvalidPasswordException {
        validEmail(userRequest.getEmail());
        validPassword(userRequest.getPassword());

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setToken(jwtTokenUtil.generateToken(userRequest.getEmail()));
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

    private void validPassword(final String password) throws InvalidPasswordException {
        Pattern pattern = Pattern.compile(paswordRegexp);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new InvalidPasswordException("La contraseña debe cumplir los siguientes criterios: " +
                    "Contener al menos un número (0-9). " +
                    "Contener al menos una letra minúscula. " +
                    "Contener al menos una letra mayúscula. " +
                    "No contener espacios en blanco. " +
                    "Tener una longitud de entre 8 y 20 caracteres. ");
        }
    }

}
