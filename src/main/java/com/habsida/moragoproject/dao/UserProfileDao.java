package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.UserProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDao {

    UserProfileRepository userProfileRepository;

    public UserProfileDao(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }
}
