package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.RoleInput;
import com.habsida.moragoproject.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RoleService {

    RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Role -> Role doesn't find by Id"));
    }

    public Role createRole(RoleInput roleInput){
        Role role = new Role();
        if(!isNull(roleInput.getName()) && !roleInput.getName().isEmpty()){
            role.setName(ERole.valueOf(roleInput.getName()));
        }else{
            role.setName(ERole.USER);
        }
        return roleRepository.save(role);
    }

    public void deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }

    public Role updateRole(Long id, RoleInput roleInput){
        Role role = roleRepository.findById(id).get();
        if(!isNull(roleInput.getName()) && !roleInput.getName().isEmpty()){
            role.setName(ERole.valueOf(roleInput.getName()));
        }else{
            role.setName(ERole.USER);
        }
        return roleRepository.save(role);
    }

}
