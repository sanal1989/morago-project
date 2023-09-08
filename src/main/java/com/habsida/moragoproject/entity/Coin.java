package com.habsida.moragoproject.entity;

import javax.persistence.Entity;


@Entity
public class Coin extends AbstractAuditable{

    private Double coin;
    private Double won;

    public Coin() {
    }

    public Coin(Double coin, Double won) {
        this.coin = coin;
        this.won = won;
    }

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public Double getWon() {
        return won;
    }

    public void setWon(Double won) {
        this.won = won;
    }
}
