package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Coin;
import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.input.CoinInput;
import com.habsida.moragoproject.service.CoinService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public List<Coin> findAllCoinPagination(@Argument int offset, @Argument int limit){
        return coinService.findAll(offset, limit);
    }

    @QueryMapping
    public Coin findCoinById(Long id){
        return coinService.findById(id);
    }

    @MutationMapping
    public Coin createCoin(@Argument CoinInput coinInput){
        return coinService.createCoin(coinInput);
    }

    @MutationMapping
    public String deleteCoinById(@Argument Long id){
        return coinService.deleteCoinById(id);
    }

    @MutationMapping
    public Coin updateCoin(@Argument Long id, @Argument CoinInput coinInput){
        return coinService.updateCoin(id, coinInput);
    }
}
