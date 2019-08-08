package com.tambara.omoikane.gateway.exception;

import org.springframework.http.HttpStatus;

public class HttpErrorException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public HttpErrorException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
