package com.nisum.userapi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String token;
}
