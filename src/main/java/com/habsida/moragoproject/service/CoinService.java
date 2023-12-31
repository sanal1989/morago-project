package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.input.CoinInput;
import com.habsida.moragoproject.repository.CoinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Coin> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<Coin> pages = coinRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public Coin findById(Long id){
        return coinRepository.findById(id)
                .orElseThrow(()-> new NotFoundByIdException("Coin -> Coin doesn't find by Id " + id));
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

    public String deleteCoinById(Long id){
        try{
            coinRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Coin with Id "+id+" deleted";
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
