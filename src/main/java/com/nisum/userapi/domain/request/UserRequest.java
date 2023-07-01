package com.nisum.userapi.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.userapi.domain.dto.PhoneDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Nombre de usuario.",
            name = "id",
            dataType = "name",
            required = true,
            position = 1
    )
    @NotEmpty(message = "Nombre es Requerido.")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(value = "Correo electronico.",
            name = "email",
            dataType = "String",
            required = true,
            position = 2
    )
    @NotNull(message = "Email es Requerido.")
    @Email(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Email no es valido.")
    @JsonProperty("email")
    private String email;

    @ApiModelProperty(value = "Contraseña.",
            name = "password",
            dataType = "String",
            required = true,
            position = 3
    )
    @NotEmpty(message = "Password es Requerido.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\S+$).{8,}$" ,message = "Password no cumple criterios minimos")
    @JsonProperty("password")
    private String password;

    @ApiModelProperty(value = "Telefonos",
            name = "phones",
            dataType = "List",
            required = true,
            position = 4
    )
    @NotNull(message = "Debe proporcionar almenos un numero de Telefono.")
    @Size(min = 1, message = "Debe proporcionar almenos un numero de Telefono.")
    @JsonProperty("phones")
    private @Valid Set<PhoneDto> phones;

}
