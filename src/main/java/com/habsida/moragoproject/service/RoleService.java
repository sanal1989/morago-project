package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.RoleDao;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.RoleInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RoleService {
    RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> findAll(){
        return roleDao.findAll();
    }

    public Role findById(Long id){
        return roleDao.findById(id);
    }

    public Role addRole(RoleInput roleInput){
        Role role = new Role();
        if(isNull(roleInput.getName())){
            role.setName(ERole.USER);
        }else{
            role.setName(ERole.valueOf(roleInput.getName()));
        }
        return roleDao.addRole(role);
    }

    public void deleteRole(Long id){
        roleDao.deleteRole(id);
    }

    public Role editRole(Long id, RoleInput roleInput){
        Role role = new Role();
        role.setId(id);
        if(isNull(roleInput.getName())){
            role.setName(ERole.USER);
        }else{
            role.setName(ERole.valueOf(roleInput.getName()));
        }
        return roleDao.editRole(role);
    }

}
