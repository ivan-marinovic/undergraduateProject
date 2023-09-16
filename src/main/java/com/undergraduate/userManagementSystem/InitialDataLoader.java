package com.undergraduate.userManagementSystem;

import com.undergraduate.userManagementSystem.model.Role;
import com.undergraduate.userManagementSystem.model.User;
import com.undergraduate.userManagementSystem.repository.RoleRepository;
import com.undergraduate.userManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialDataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${role.user}")
    private String ROLE_USER;
    @Value("${role.admin}")
    private String ROLE_ADMIN;
    @Value("${admin.fullName}")
    private String ADMIN_NAME;
    @Value("${admin.email}")
    private String ADMIN_EMAIL;
    @Value("${admin.password}")
    private String ADMIN_PASSWORD;


    @Override
    public void run(String... args){
        Optional<Role> optionalRoleUser = roleRepository.findByName(ROLE_USER);
        if (optionalRoleUser.isEmpty()) {
            Role roleUser = new Role(ROLE_USER);
            roleRepository.save(roleUser);
        }

        Optional<Role> optionalRoleAdmin = roleRepository.findByName(ROLE_ADMIN);
        if (optionalRoleAdmin.isEmpty()) {
            Role adminRole = new Role(ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        Optional<User> optionalUser = userRepository.findByEmail(ADMIN_EMAIL);
        if (optionalUser.isEmpty()) {
            User admin = new User();
            admin.setFullName(ADMIN_NAME);
            admin.setEmail(ADMIN_EMAIL);
            admin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(ROLE_ADMIN).get());
            admin.setRoles(roles);
            admin.setEnable(true);
            userRepository.save(admin);
        }
    }
}