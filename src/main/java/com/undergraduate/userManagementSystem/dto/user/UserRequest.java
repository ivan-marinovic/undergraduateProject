package com.undergraduate.userManagementSystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String fullName;
    private String email;
    private String password;
    private boolean enable;
    private List<Integer> roleId;
}
