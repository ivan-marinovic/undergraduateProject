package com.undergraduate.userManagementSystem.dto.user;

import com.undergraduate.userManagementSystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long userId;
    private String fullName;
    private String email;
    private boolean enable;
    private List<Role> roles;
}
