package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Debtor extends AbstractAuditable{

    private String accountHolder;
    private Boolean isPaid;
    private String nameOfBank;

    @ManyToOne
    private User user;

    public Debtor() {
    }

    public Debtor(String accountHolder, Boolean isPaid, String nameOfBank, User user) {
        this.accountHolder = accountHolder;
        this.isPaid = isPaid;
        this.nameOfBank = nameOfBank;
        this.user = user;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
