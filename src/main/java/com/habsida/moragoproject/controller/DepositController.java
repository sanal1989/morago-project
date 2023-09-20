package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.input.DepositInput;
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
    public Deposit createDeposit(@Argument DepositInput depositInput){
        return depositService.createDeposit(depositInput);
    }

    @MutationMapping
    public void deleteDepositById(@Argument Long id){
        depositService.deleteDepositById(id);
    }

    @MutationMapping
    public Deposit updateDeposit(@Argument Long id, @Argument DepositInput depositInput){
        return depositService.updateDeposit(id, depositInput);
    }
}
