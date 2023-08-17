package com.undergraduate.userManagementSystem.controller;

import com.undergraduate.userManagementSystem.dto.auth.AuthenticationRequest;
import com.undergraduate.userManagementSystem.dto.auth.AuthenticationResponse;
import com.undergraduate.userManagementSystem.dto.auth.RegisterRequest;
import com.undergraduate.userManagementSystem.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
            ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
