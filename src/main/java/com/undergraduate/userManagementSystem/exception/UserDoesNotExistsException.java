package com.undergraduate.userManagementSystem.exception;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String msg) {
        super(msg);
    }
}
