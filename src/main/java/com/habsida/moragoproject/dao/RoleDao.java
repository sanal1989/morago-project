package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleDao {

    RoleRepository roleRepository;

    public RoleDao(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
