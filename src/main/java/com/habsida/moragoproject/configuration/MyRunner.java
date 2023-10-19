package com.habsida.moragoproject.configuration;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public MyRunner(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!roleRepository.existsByName(ERole.ADMIN)){
            roleRepository.save(new Role(ERole.ADMIN));
        }
        if(!roleRepository.existsByName(ERole.USER)){
            roleRepository.save(new Role(ERole.USER));
        }
        if(!roleRepository.existsByName(ERole.TRANSLATOR)){
            roleRepository.save(new Role(ERole.TRANSLATOR));
        }
    }
}
