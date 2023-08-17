package com.undergraduate.userManagementSystem.service.conversion;

import com.undergraduate.userManagementSystem.dto.user.UserRequest;
import com.undergraduate.userManagementSystem.dto.user.UserResponse;
import com.undergraduate.userManagementSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConversionService {

    public User convertToModel(UserRequest userRequest) {
        return User.builder()
                .fullName(userRequest.getFullName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .enable(userRequest.isEnable())
                .build();
    }

    public UserResponse convertToDto(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .enable(user.isEnable())
                .roles(user.getRoles())
                .build();
    }

    public List<UserResponse> convertToDtoList(List<User> users) {
        List<UserResponse> usersDto = new ArrayList<>();
        users.forEach((user) -> usersDto.add(convertToDto(user)));
        return usersDto;
    }
}
