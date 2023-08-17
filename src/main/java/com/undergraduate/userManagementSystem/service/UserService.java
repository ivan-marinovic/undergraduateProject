package com.undergraduate.userManagementSystem.service;

import com.undergraduate.userManagementSystem.exception.UserAlreadyExistsException;
import com.undergraduate.userManagementSystem.exception.UserDoesNotExistsException;
import com.undergraduate.userManagementSystem.model.Role;
import com.undergraduate.userManagementSystem.model.User;
import com.undergraduate.userManagementSystem.repository.RoleRepository;
import com.undergraduate.userManagementSystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void create(User user, List<Integer> roleId) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email is already taken");
        } else {
            List<Role> roles = roleRepository.findAllById(roleId);
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

    public void update(Long userId, User user, List<Integer> roleIds) {
        User updatedUser = findById(userId);
        updatedUser.setFullName(user.getFullName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = roleRepository.findAllById(roleIds);
        updatedUser.setRoles(roles);
        updatedUser.setEnable(user.isEnable());
        userRepository.save(updatedUser);
    }
}
