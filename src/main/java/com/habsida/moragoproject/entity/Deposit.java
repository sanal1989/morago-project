package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Deposit extends AbstractAuditable{

    private String accountHolder;
    private Double coin;
    private String nameOfBank;
    private EStatus status;
    private Double won;

    @ManyToOne
    private User user;

    public Deposit() {
    }

    public Deposit(String accountHolder, Double coin, String nameOfBank, EStatus status, Double won,User user) {
        this.accountHolder = accountHolder;
        this.coin = coin;
        this.nameOfBank = nameOfBank;
        this.status = status;
        this.won = won;
        this.user = user;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public Double getWon() {
        return won;
    }

    public void setWon(Double won) {
        this.won = won;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
