package com.undergraduate.userManagementSystem.controller;

import com.undergraduate.userManagementSystem.dto.ApiResponse;
import com.undergraduate.userManagementSystem.dto.user.PasswordRequest;
import com.undergraduate.userManagementSystem.dto.user.UserProfileRequest;
import com.undergraduate.userManagementSystem.dto.user.UserResponse;
import com.undergraduate.userManagementSystem.model.User;
import com.undergraduate.userManagementSystem.service.UserService;
import com.undergraduate.userManagementSystem.service.conversion.UserConversionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
public class UserProfileController {

    private final UserService userService;
    private final UserConversionService userConversionService;

    public UserProfileController(UserService userService, UserConversionService userConversionService) {
        this.userService = userService;
        this.userConversionService = userConversionService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        UserResponse userResponse = userConversionService.convertToDto(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody UserProfileRequest userProfileRequest
        ) {
        User user = userService.findByEmail(userDetails.getUsername());
        userService.updateProfile(user.getUserId(), userConversionService.convertProfileToModel(userProfileRequest));
        return new ResponseEntity<>(new ApiResponse(1, "User successfully updated"), HttpStatus.OK);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PasswordRequest passwordRequest) {
        userService.changePassword(userDetails.getUsername(), passwordRequest);
        return new ResponseEntity<>(new ApiResponse(1, "Password successfully changed"), HttpStatus.OK);
    }
}
