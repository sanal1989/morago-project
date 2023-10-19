package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
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
                .orElseThrow(()->new NotFoundByIdException("Role -> Role doesn't find by Id " + id));
    }

    public Role createRole(RoleInput roleInput){
        Role role = new Role();
        if(!isNull(roleInput.getName())){
            role.setName(roleInput.getName());
        }else{
            role.setName(ERole.USER);
        }
        return roleRepository.save(role);
    }

    public String deleteRoleById(Long id){
        try {
            roleRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Role with Id "+id+" deleted";
    }

    public Role updateRole(Long id, RoleInput roleInput){
        Role role = roleRepository.findById(id).get();
        if(!isNull(roleInput.getName())){
            role.setName(roleInput.getName());
        }else{
            role.setName(ERole.USER);
        }
        return roleRepository.save(role);
    }

}
