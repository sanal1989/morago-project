package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.input.WithdrawalInput;
import com.habsida.moragoproject.service.WithdrawalService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WithdrawalController {

    WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @QueryMapping
    public List<Withdrawal> findAllWithdrawalService(){
        return withdrawalService.findAll();
    }

    @QueryMapping
    public Withdrawal findWithdrawalById(@Argument Long id){
        return withdrawalService.findById(id);
    }

    @MutationMapping
    public Withdrawal addWithdrawal(@Argument WithdrawalInput withdrawalInput){
        return withdrawalService.addWithdrawal(withdrawalInput);
    }

    @MutationMapping
    public void deleteWithdrawal(@Argument Long id){
        withdrawalService.deleteWithdrawal(id);
    }

    @MutationMapping
    public Withdrawal editWithdrawal(@Argument Long id, @Argument WithdrawalInput withdrawalInput){
        return withdrawalService.editWithdrawal(id, withdrawalInput);
    }
}
