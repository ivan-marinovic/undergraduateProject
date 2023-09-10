package com.undergraduate.userManagementSystem.controller;

import com.undergraduate.userManagementSystem.dto.ApiResponse;
import com.undergraduate.userManagementSystem.dto.role.RoleRequest;
import com.undergraduate.userManagementSystem.dto.role.RoleResponse;
import com.undergraduate.userManagementSystem.service.RoleService;
import com.undergraduate.userManagementSystem.service.conversion.RoleConversionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleConversionService roleConversionService;

    public RoleController(RoleService roleService, RoleConversionService roleConversionService) {
        this.roleService = roleService;
        this.roleConversionService = roleConversionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody RoleRequest roleRequest) {
        roleService.create(roleConversionService.convertToModel(roleRequest));
        return new ResponseEntity<>(new ApiResponse(1, "Role successfully created!"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAll() {
        List<RoleResponse> roleResponseList = roleConversionService.convertToDtoList(roleService.findAll());
        return new ResponseEntity<>(roleResponseList, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> getById(@PathVariable(name = "roleId") Integer roleId) {
        RoleResponse roleResponse = roleConversionService.convertToDto(roleService.findById(roleId));
        return new ResponseEntity<>(roleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable(name = "roleId") Integer roleId) {
        roleService.deleteById(roleId);
        return new ResponseEntity<>(new ApiResponse(1, "Role successfully deleted"), HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponse> update(@PathVariable(name = "roleId") Integer roleId,@Valid @RequestBody RoleRequest roleRequest) {
        roleService.update(roleId, roleConversionService.convertToModel(roleRequest));
        return new ResponseEntity<>(new ApiResponse(1, "Role successfully updated"), HttpStatus.OK);
    }
}
