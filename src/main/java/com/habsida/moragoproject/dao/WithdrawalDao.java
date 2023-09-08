package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.WithdrawalRepository;
import org.springframework.stereotype.Component;

@Component
public class WithdrawalDao {

    WithdrawalRepository withdrawalRepository;

    public WithdrawalDao(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }
}
