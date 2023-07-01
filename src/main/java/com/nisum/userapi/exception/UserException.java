package com.nisum.userapi.exception;

public class UserException extends Exception {

    public UserException() {
    }

    public UserException(final String message) {
        super(message);
    }

    public UserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserException(final Throwable cause) {
        super(cause);
    }

    public UserException(final String message, final Throwable cause, final boolean enableSuppression,
                          final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
