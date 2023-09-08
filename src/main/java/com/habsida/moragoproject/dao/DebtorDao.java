package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.DebtorRepository;
import org.springframework.stereotype.Component;

@Component
public class DebtorDao {

    DebtorRepository debtorRepository;

    public DebtorDao(DebtorRepository debtorRepository) {
        this.debtorRepository = debtorRepository;
    }
}
