package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.DepositRepository;
import org.springframework.stereotype.Component;

@Component
public class DepositDao {

    DepositRepository depositRepository;

    public DepositDao(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }
}
