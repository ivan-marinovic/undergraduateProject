package com.undergraduate.userManagementSystem.service;

import com.undergraduate.userManagementSystem.exception.RoleAlreadyExistsException;
import com.undergraduate.userManagementSystem.exception.RoleDoesNotExistsException;
import com.undergraduate.userManagementSystem.model.Role;
import com.undergraduate.userManagementSystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void create(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        if (optionalRole.isPresent()) {
            throw new RoleAlreadyExistsException("Role already exists!");
        } else {
            roleRepository.save(role);
        }
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Integer roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if(optionalRole.isEmpty()) {
            throw new RoleDoesNotExistsException("Role does not exists!");
        } else {
            return optionalRole.get();
        }
    }

    public void deleteById(Integer roleId) {
        roleRepository.delete(findById(roleId));
    }

    public void update(Integer roleId, Role role) {
        Role updatedRole = findById(roleId);
        updatedRole.setName(role.getName());
        roleRepository.save(updatedRole);
    }
}
