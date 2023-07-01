package com.nisum.userapi.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nisum.userapi.domain.dto.PhoneDto;
import com.nisum.userapi.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String email;
    private String password;
    private String token;
    private Boolean active;
    private Set<PhoneDto> phones;
    private LocalDateTime lastLogin;
    private LocalDateTime created;
    private LocalDateTime modified;

    public UserResponse() {

    }

    public UserResponse(final UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.token = userEntity.getToken();
        this.active = userEntity.getActive();
        this.phones = userEntity.getPhones().stream().map(PhoneDto::new).collect(Collectors.toSet());
        this.created = userEntity.getCreated();
        this.modified = userEntity.getModified();
        this.lastLogin = userEntity.getLastLogin();
    }

}
