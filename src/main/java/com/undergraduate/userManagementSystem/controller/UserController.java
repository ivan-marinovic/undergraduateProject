package com.undergraduate.userManagementSystem.controller;

import com.undergraduate.userManagementSystem.dto.ApiResponse;
import com.undergraduate.userManagementSystem.dto.user.UserRequest;
import com.undergraduate.userManagementSystem.dto.user.UserResponse;
import com.undergraduate.userManagementSystem.model.User;
import com.undergraduate.userManagementSystem.service.UserService;
import com.undergraduate.userManagementSystem.service.conversion.UserConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserConversionService userConversionService;

    public UserController(UserService userService, UserConversionService userConversionService) {
        this.userService = userService;
        this.userConversionService = userConversionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody UserRequest userRequest) {
        userService.create(userConversionService.convertToModel(userRequest), userRequest.getRoleId());
        return new ResponseEntity<>(new ApiResponse(1, "User successfully created!"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.findAll();
        List<UserResponse> userResponseList = userConversionService.convertToDtoList(users);
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "userId") Long userId) {
        User user = userService.findById(userId);
        UserResponse userResponse = userConversionService.convertToDto(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable(name = "userId") Long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(new ApiResponse(1, "User successfully deleted"), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable(name = "userId") Long userId,
            @RequestBody UserRequest userRequest) {
        userService.update(userId, userConversionService.convertToModel(userRequest), userRequest.getRoleId());
        return new ResponseEntity<>(new ApiResponse(1, "User successfully updated"), HttpStatus.OK);
    }

}
