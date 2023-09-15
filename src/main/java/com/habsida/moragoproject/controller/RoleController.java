package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.input.RoleInput;
import com.habsida.moragoproject.service.RoleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
    public Role findRoleById(@Argument Long id){
        return roleService.findById(id);
    }

    @MutationMapping
    public Role addRole(@Argument RoleInput roleInput){
        return roleService.addRole(roleInput);
    }

    @MutationMapping
    public void deleteRole(@Argument Long id){
        roleService.deleteRole(id);
    }

    @MutationMapping
    public Role editRole(@Argument Long id, @Argument RoleInput roleInput){
        return roleService.editRole(id, roleInput);
    }
}
