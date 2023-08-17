package com.undergraduate.userManagementSystem.exception;

import com.undergraduate.userManagementSystem.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public final ResponseEntity<ApiResponse> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ResponseEntity<>(new ApiResponse(0, userAlreadyExistsException.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserDoesNotExistsException.class)
    public final ResponseEntity<ApiResponse> userDoesNotExistsException(UserDoesNotExistsException userDoesNotExistsException) {
        return new ResponseEntity<>(new ApiResponse(0, userDoesNotExistsException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RoleAlreadyExistsException.class)
    public final ResponseEntity<ApiResponse> roleAlreadyExistsException(RoleAlreadyExistsException roleAlreadyExistsException) {
        return new ResponseEntity<>(new ApiResponse(0, roleAlreadyExistsException.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RoleDoesNotExistsException.class)
    public final ResponseEntity<ApiResponse> roleDoesNotExistsException(RoleDoesNotExistsException roleDoesNotExistsException) {
        return new ResponseEntity<>(new ApiResponse(0, roleDoesNotExistsException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
