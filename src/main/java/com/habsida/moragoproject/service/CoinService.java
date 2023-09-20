package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.model.input.CoinInput;
import com.habsida.moragoproject.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CoinService {

    CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<Coin> findAll(){
        return coinRepository.findAll();
    }

    public Coin findById(Long id){
        return coinRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Coin -> Coin doesn't find by Id"));
    }

    public Coin createCoin(CoinInput coinInput){
        Coin coin = new Coin();
        if(!isNull(coinInput.getCoin())){
            coin.setCoin(coinInput.getCoin());
        }else {
            coin.setCoin(0d);
        }
        if(!isNull(coinInput.getWon())){
            coin.setWon(coinInput.getWon());
        }else {
            coin.setWon(0d);
        }
        return coinRepository.save(coin);
    }

    public void deleteCoinById(Long id){
        coinRepository.deleteById(id);
    }

    public Coin updateCoin(Long id, CoinInput coinInput){
        Coin coin = coinRepository.findById(id).get();
        if(!isNull(coinInput.getCoin())){
            coin.setCoin(coinInput.getCoin());
        }else {
            coin.setCoin(0d);
        }
        if(!isNull(coinInput.getWon())){
            coin.setWon(coinInput.getWon());
        }else {
            coin.setWon(0d);
        }
        return coinRepository.save(coin);
    }
}
