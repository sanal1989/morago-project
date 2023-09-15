package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.service.CoinService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CoinController {

    CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @QueryMapping
    public List<Coin> findAllCoin(){
        return coinService.findAll();
    }

    @QueryMapping
    public Coin findCoinById(Long id){
        return coinService.findById(id);
    }

    @MutationMapping
    public Coin addCoin(@Argument Double coins, @Argument Double won){
        Coin coin = new Coin();
        coin.setCoin(coins);
        coin.setWon(won);
        return coinService.addCoin(coin);
    }

    @MutationMapping
    public void deleteCoin(@Argument Long id){
        coinService.deleteCoin(id);
    }

    @MutationMapping
    public Coin editCoin(@Argument Long id, @Argument Double coins, @Argument Double won){
        Coin coin = new Coin();
        coin.setId(id);
        coin.setCoin(coins);
        coin.setWon(won);
        return coinService.editCoin(coin);
    }
}
