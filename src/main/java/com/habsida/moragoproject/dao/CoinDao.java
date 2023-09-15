package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.CoinRepository;
import com.habsida.moragoproject.model.entity.Coin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinDao {

    CoinRepository coinRepository;

    public CoinDao(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<Coin> findAll(){
        return coinRepository.findAll();
    }

    public Coin findById(Long id){
        return coinRepository.findById(id).get();
    }

    public Coin addCoin(Coin coin){
        return coinRepository.save(coin);
    }

    public void deleteCoin(Long id){
        coinRepository.deleteById(id);
    }

    public Coin editCoin(Coin coin){
        Coin coinFromDB = coinRepository.findById(coin.getId()).get();
        if(coin.getCoin() != coinFromDB.getCoin()){
            coinFromDB.setCoin(coin.getCoin());
        }
        if(coin.getWon() != coinFromDB.getWon()){
            coinFromDB.setWon(coin.getWon());
        }

        return coinRepository.save(coinFromDB);
    }
}
