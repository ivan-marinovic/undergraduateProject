package com.undergraduate.userManagementSystem.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotEmpty
    private String fullName;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;
    private boolean enable;
    private Set<Integer> roleId;
}
