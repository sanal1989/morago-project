package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
