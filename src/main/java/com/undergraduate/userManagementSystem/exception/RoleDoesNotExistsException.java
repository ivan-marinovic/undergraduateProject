package com.undergraduate.userManagementSystem.exception;

public class RoleDoesNotExistsException extends RuntimeException {

    public RoleDoesNotExistsException(String msg) {
        super(msg);
    }
}
