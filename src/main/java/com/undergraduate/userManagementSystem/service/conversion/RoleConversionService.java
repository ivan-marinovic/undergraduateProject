package com.undergraduate.userManagementSystem.service.conversion;

import com.undergraduate.userManagementSystem.dto.role.RoleRequest;
import com.undergraduate.userManagementSystem.dto.role.RoleResponse;
import com.undergraduate.userManagementSystem.dto.user.UserRequest;
import com.undergraduate.userManagementSystem.dto.user.UserResponse;
import com.undergraduate.userManagementSystem.model.Role;
import com.undergraduate.userManagementSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleConversionService {


    public Role convertToModel(RoleRequest roleRequest) {
        return Role.builder()
                .name(roleRequest.getName())
                .build();
    }

    public RoleResponse convertToDto(Role role) {
        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .name(role.getName())
                .build();
    }

    public List<RoleResponse> convertToDtoList(List<Role> roles) {
        List<RoleResponse> rolesDto = new ArrayList<>();
        roles.forEach((role) -> rolesDto.add(convertToDto(role)));
        return rolesDto;
    }
}
