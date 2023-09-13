package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.Deposit;
import com.habsida.moragoproject.entity.EStatus;
import com.habsida.moragoproject.service.DepositService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DepositController {

    DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @QueryMapping
    public List<Deposit> findAllDeposit(){
        return depositService.findAll();
    }

    @QueryMapping
    public Deposit findDepositById(@Argument Long id){
        return depositService.findById(id);
    }

    @MutationMapping
    public Deposit addDeposit(@Argument String accountHolder, @Argument Double coin, @Argument String nameOfBank,
                              @Argument String status, @Argument Double won){
        Deposit deposit = new Deposit();
        deposit.setAccountHolder(accountHolder);
        deposit.setCoin(coin);
        deposit.setNameOfBank(nameOfBank);
        deposit.setStatus(EStatus.valueOf(status));
        deposit.setWon(won);
        return depositService.addDeposit(deposit);
    }

    @MutationMapping
    public void deleteDeposit(@Argument Long id){
        depositService.deleteDeposit(id);
    }

    @MutationMapping
    public Deposit editDeposit(@Argument Long id, @Argument String accountHolder, @Argument Double coin, @Argument String nameOfBank,
                               @Argument String status, @Argument Double won){
        Deposit deposit = new Deposit();
        deposit.setId(id);
        deposit.setAccountHolder(accountHolder);
        deposit.setCoin(coin);
        deposit.setNameOfBank(nameOfBank);
        deposit.setStatus(EStatus.valueOf(status));
        deposit.setWon(won);
        return depositService.editDeposit(deposit);
    }
}
