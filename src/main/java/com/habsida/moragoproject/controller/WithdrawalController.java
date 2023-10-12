package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.input.WithdrawalInput;
import com.habsida.moragoproject.service.WithdrawalService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public Withdrawal createWithdrawal(@Argument WithdrawalInput withdrawalInput){
        return withdrawalService.createWithdrawal(withdrawalInput);
    }

    @MutationMapping
    public String deleteWithdrawalById(@Argument Long id){
        return withdrawalService.deleteWithdrawalById(id);
    }

    @MutationMapping
    public Withdrawal updateWithdrawal(@Argument Long id, @Argument WithdrawalInput withdrawalInput){
        return withdrawalService.updateWithdrawal(id, withdrawalInput);
    }
}
