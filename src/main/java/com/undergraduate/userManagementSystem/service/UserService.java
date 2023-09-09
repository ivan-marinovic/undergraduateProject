package com.undergraduate.userManagementSystem.service;

import com.undergraduate.userManagementSystem.dto.user.PasswordRequest;
import com.undergraduate.userManagementSystem.exception.UserAlreadyExistsException;
import com.undergraduate.userManagementSystem.exception.UserDoesNotExistsException;
import com.undergraduate.userManagementSystem.model.Role;
import com.undergraduate.userManagementSystem.model.User;
import com.undergraduate.userManagementSystem.repository.RoleRepository;
import com.undergraduate.userManagementSystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(User user, Set<Integer> roleId) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email is already taken");
        } else {
            List<Role> roleList = roleRepository.findAllById(roleId);
            Set<Role> roles = new HashSet<>(roleList);
            User createdUser = new User();
            createdUser.setFullName(user.getFullName());
            createdUser.setEmail(user.getEmail());
            createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
            createdUser.setRoles(roles);
            createdUser.setEnable(true);
            userRepository.save(createdUser);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserDoesNotExistsException("User with id " + userId + " does not exists");
        }
    }

    public void deleteById(Long userId) {
        userRepository.delete(findById(userId));
    }

    public void update(Long userId, User user, Set<Integer> roleIds) {
        User updatedUser = findById(userId);
        updatedUser.setFullName(user.getFullName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roleList = roleRepository.findAllById(roleIds);
        Set<Role> roles = new HashSet<>(roleList);
        updatedUser.setRoles(roles);
        updatedUser.setEnable(user.isEnable());
        userRepository.save(updatedUser);
    }

    public User findByEmail(String username) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserDoesNotExistsException("User with email " + username + " does not exists");
        }
    }

    public void updateProfile(Long userId, User user) {
        User updatedUser = findById(userId);
        updatedUser.setFullName(user.getFullName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updatedUser.setEnable(user.isEnable());
        userRepository.save(updatedUser);
    }

    public void changePassword(String username, PasswordRequest newPassword) {
        User user = findByEmail(username);
        user.setPassword(passwordEncoder.encode(newPassword.getPassword()));
        userRepository.save(user);
    }
}
