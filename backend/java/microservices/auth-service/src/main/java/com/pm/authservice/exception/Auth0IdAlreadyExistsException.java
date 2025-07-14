package com.pm.authservice.exception;

public class Auth0IdAlreadyExistsException extends RuntimeException{
    public Auth0IdAlreadyExistsException(String message) {
        super(message);
    }
}
