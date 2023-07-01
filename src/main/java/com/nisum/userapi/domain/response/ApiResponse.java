package com.nisum.userapi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private T body;
    private Integer statusCode;
    private String message;

    public ApiResponse() {

    }
}
