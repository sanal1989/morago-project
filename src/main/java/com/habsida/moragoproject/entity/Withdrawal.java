package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Withdrawal extends AbstractAuditable{

    private String accountHolder;
    private String accountNumber;
    private String nameOfBank;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    private Double sum;

    public Withdrawal() {
    }

    public Withdrawal(String accountHolder, String accountNumber, String nameOfBank, EStatus status, Double sum) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.nameOfBank = nameOfBank;
        this.status = status;
        this.sum = sum;
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
