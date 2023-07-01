package com.nisum.userapi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private Integer statusCode;
    private String message;
    private String detail;

    public ErrorResponse() {

    }
}
