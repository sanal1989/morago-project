package com.habsida.moragoproject.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Withdrawal extends AbstractAuditable{

    @Enumerated(EnumType.ORDINAL)
    private EStatus status;

    private String accountHolder;
    private String accountNumber;
    private String nameOfBank;
    private Double sum;

    @ManyToOne
    private User user;

    public Withdrawal() {
    }

    public Withdrawal(String accountHolder, String accountNumber, String nameOfBank, EStatus status, Double sum, User user) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.nameOfBank = nameOfBank;
        this.status = status;
        this.sum = sum;
        this.user = user;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
