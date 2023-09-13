package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.RoleDao;
import com.habsida.moragoproject.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Role addRole(Role role){
        return roleDao.addRole(role);
    }

    public void deleteRole(Long id){
        roleDao.deleteRole(id);
    }

    public Role editRole(Role role){
        return roleDao.editRole(role);
    }

}
