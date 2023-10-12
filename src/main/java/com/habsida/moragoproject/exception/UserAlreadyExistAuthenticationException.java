package com.habsida.moragoproject.exception;

public class UserAlreadyExistAuthenticationException extends RuntimeException{
    public UserAlreadyExistAuthenticationException(String message) {
        super(message);
    }
}
