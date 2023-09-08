package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.CoinRepository;
import org.springframework.stereotype.Component;

@Component
public class CoinDao {

    CoinRepository coinRepository;

    public CoinDao(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }
}
