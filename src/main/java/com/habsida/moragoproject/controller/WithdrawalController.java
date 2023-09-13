package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.EStatus;
import com.habsida.moragoproject.entity.Withdrawal;
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
    public Withdrawal addWithdrawal(@Argument String status, @Argument String accountHolder, @Argument String accountNumber,
                                    @Argument String nameOfBank, @Argument Double sum){
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setStatus(EStatus.valueOf(status));
        withdrawal.setAccountNumber(accountNumber);
        withdrawal.setAccountHolder(accountHolder);
        withdrawal.setNameOfBank(nameOfBank);
        withdrawal.setSum(sum);
        return withdrawalService.addWithdrawal(withdrawal);
    }

    @MutationMapping
    public void deleteWithdrawal(@Argument Long id){
        withdrawalService.deleteWithdrawal(id);
    }

    @MutationMapping
    public Withdrawal editWithdrawal(@Argument Long id, @Argument String status, @Argument String accountHolder, @Argument String accountNumber,
                                     @Argument String nameOfBank, @Argument Double sum){
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(id);
        withdrawal.setStatus(EStatus.valueOf(status));
        withdrawal.setAccountNumber(accountNumber);
        withdrawal.setAccountHolder(accountHolder);
        withdrawal.setNameOfBank(nameOfBank);
        withdrawal.setSum(sum);
        return withdrawalService.editWithdrawal(withdrawal);
    }
}
