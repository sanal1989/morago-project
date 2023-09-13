package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.RoleRepository;
import com.habsida.moragoproject.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDao {

    RoleRepository roleRepository;

    public RoleDao(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(Long id){
        return roleRepository.findById(id).get();
    }

    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }

    public Role editRole(Role role){
        Role roleFromDB = roleRepository.findById(role.getId()).get();
        if(role.getName() != roleFromDB.getName()){
            roleFromDB.setName(role.getName());
        }
        return roleRepository.save(roleFromDB);
    }
}
