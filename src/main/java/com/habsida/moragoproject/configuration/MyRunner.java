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
        if(!roleRepository.findByName(ERole.ADMIN).isPresent()){
            roleRepository.save(new Role(ERole.ADMIN));
        }
        if(!roleRepository.findByName(ERole.USER).isPresent()){
            roleRepository.save(new Role(ERole.USER));
        }
        if(!roleRepository.findByName(ERole.TRANSLATOR).isPresent()){
            roleRepository.save(new Role(ERole.TRANSLATOR));
        }
    }
}
