package com.undergraduate.userManagementSystem.dto.user;

import com.undergraduate.userManagementSystem.dto.role.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long userId;
    private String fullName;
    private String email;
    private boolean enable;
    private Set<RoleResponse> roles;
}
