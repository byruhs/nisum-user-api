package com.nisum.userapi.config;

import com.nisum.userapi.domain.response.ErrorResponse;
import com.nisum.userapi.exception.InvalidPasswordException;
import com.nisum.userapi.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleBusinessException(final Exception ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), Objects.isNull(ex.getCause()) ? "" : ex.getCause().getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleBusinessException(final UserException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage(), Objects.isNull(ex.getCause()) ? "" : ex.getCause().getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(final InvalidPasswordException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), Objects.isNull(ex.getCause()) ? "" : ex.getCause().getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "El módelo no es válido.", ex.getBindingResult().getAllErrors().stream()
                .map(e -> e.getDefaultMessage()).collect(Collectors.joining(", ")));
    }

}
