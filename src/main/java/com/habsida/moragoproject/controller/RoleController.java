package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.ERole;
import com.habsida.moragoproject.entity.Role;
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
    public Role addRole(@Argument String name){
        return roleService.addRole(new Role(ERole.valueOf(name)));
    }

    @MutationMapping
    public void deleteRole(@Argument Long id){
        roleService.deleteRole(id);
    }

    @MutationMapping
    public Role editRole(@Argument Long id, @Argument String name){
        Role role = new Role();
        role.setId(id);
        role.setName(ERole.valueOf(name));
        return roleService.editRole(role);
    }
}
