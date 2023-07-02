package com.nisum.userapi.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Nombre de usuario.",
            name = "username",
            dataType = "String",
            required = true,
            position = 1
    )
    @NotNull(message = "Username es Requerido.")
    private String username;

    @ApiModelProperty(value = "Password.",
            name = "password",
            dataType = "String",
            required = true,
            position = 2
    )
    @NotNull(message = "Password es Requerido.")
    private String password;
}
