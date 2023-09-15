package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.CoinDao;
import com.habsida.moragoproject.model.entity.Coin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {

    CoinDao coinDao;

    public CoinService(CoinDao coinDao) {
        this.coinDao = coinDao;
    }

    public List<Coin> findAll(){
        return coinDao.findAll();
    }

    public Coin findById(Long id){
        return coinDao.findById(id);
    }

    public Coin addCoin(Coin coin){
        return coinDao.addCoin(coin);
    }

    public void deleteCoin(Long id){
        coinDao.deleteCoin(id);
    }

    public Coin editCoin(Coin coin){
        return coinDao.editCoin(coin);
    }
}
