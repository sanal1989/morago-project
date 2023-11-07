package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.RoleInput;
import com.habsida.moragoproject.service.RoleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @QueryMapping
    public List<Role> findAllRole(){
        return roleService.findAll();
    }

    @QueryMapping
    public List<Role> findAllRolePagination(@Argument int offset, @Argument int limit){
        return roleService.findAll(offset, limit);
    }

    @QueryMapping
    public Role findRoleById(@Argument Long id){
        return roleService.findById(id);
    }

    @MutationMapping
    public Role createRole(@Argument RoleInput roleInput){
        return roleService.createRole(roleInput);
    }

    @MutationMapping
    public String deleteRoleById(@Argument Long id){
        return roleService.deleteRoleById(id);
    }

    @MutationMapping
    public Role updateRole(@Argument Long id, @Argument RoleInput roleInput){
        return roleService.updateRole(id, roleInput);
    }
}
