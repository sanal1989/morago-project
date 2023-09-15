package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.WithdrawalInput;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import com.habsida.moragoproject.model.entity.Withdrawal;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class WithdrawalDao {

    WithdrawalRepository withdrawalRepository;

    public WithdrawalDao(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    public List<Withdrawal> findAll(){
        return withdrawalRepository.findAll();
    }

    public Withdrawal findById(Long id){
        return withdrawalRepository.findById(id).get();
    }

    public Withdrawal addWithdrawal(Withdrawal withdrawal){
        return withdrawalRepository.save(withdrawal);
    }

    public void deleteWithdrawal(Long id){
        withdrawalRepository.deleteById(id);
    }

    public Withdrawal editWithdrawal(Withdrawal withdrawal){
        Withdrawal withdrawalFromDB = withdrawalRepository.findById(withdrawal.getId()).get();
        if(!withdrawal.getAccountHolder().equals("EMPTY")){
            withdrawalFromDB.setAccountHolder(withdrawal.getAccountHolder());
        }
        if(withdrawal.getStatus() != withdrawalFromDB.getStatus()){
            withdrawalFromDB.setStatus(withdrawal.getStatus());
        }
        if(!withdrawal.getAccountNumber().equals("EMPTY")){
            withdrawalFromDB.setAccountNumber(withdrawal.getAccountNumber());
        }
        if(!withdrawal.getNameOfBank().equals("EMPTY")){
            withdrawalFromDB.setNameOfBank(withdrawal.getNameOfBank());
        }
        if(withdrawal.getSum() != 0d && withdrawal.getSum() != withdrawalFromDB.getSum() ){
            withdrawalFromDB.setSum(withdrawal.getSum());
        }
        return withdrawalRepository.save(withdrawalFromDB);
    }
}
