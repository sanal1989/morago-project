package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.PasswordResetRepository;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetDao {

    PasswordResetRepository passwordResetRepository;

    public PasswordResetDao(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }
}
