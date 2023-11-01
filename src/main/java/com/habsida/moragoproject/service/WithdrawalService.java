package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.WithdrawalInput;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class WithdrawalService {

    WithdrawalRepository withdrawalRepository;
    UserService userService;

    public WithdrawalService(WithdrawalRepository withdrawalRepository, UserService userService) {
        this.withdrawalRepository = withdrawalRepository;
        this.userService = userService;
    }

    public List<Withdrawal> findAll(){
        return withdrawalRepository.findAll();
    }

    public Withdrawal findById(Long id){
        return withdrawalRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("Withdrawal doesn't find by Id " + id));
    }

    public Withdrawal createWithdrawal(WithdrawalInput withdrawalInput){
        Withdrawal withdrawal = new Withdrawal();
        if(!isNull(withdrawalInput.getStatus()) && !withdrawalInput.getStatus().isEmpty()){
            withdrawal.setStatus(EStatus.valueOf(withdrawalInput.getStatus()));
        }else{
            withdrawal.setStatus(EStatus.E100);
        }
        if(!isNull(withdrawalInput.getAccountNumber()) && !withdrawalInput.getAccountNumber().isEmpty()){
            withdrawal.setAccountNumber(withdrawalInput.getAccountNumber());
        }
        if(!isNull(withdrawalInput.getAccountHolder()) && !withdrawalInput.getAccountHolder().isEmpty()){
            withdrawal.setAccountHolder(withdrawalInput.getAccountHolder());
        }
        if(!isNull(withdrawalInput.getNameOfBank()) && !withdrawalInput.getNameOfBank().isEmpty()){
            withdrawal.setNameOfBank(withdrawalInput.getNameOfBank());
        }
        if(!isNull(withdrawalInput.getSum())){
            withdrawal.setSum(withdrawalInput.getSum());
        }else {
            withdrawal.setSum(0d);
        }
        if(!isNull(withdrawalInput.getUser())){
            withdrawal.setUser(userService.findById(withdrawalInput.getUser()));
        }
        return withdrawalRepository.save(withdrawal);
    }

    public String deleteWithdrawalById(Long id){
        try{
            withdrawalRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Withdrawal with Id "+id+" deleted";
    }

    public Withdrawal updateWithdrawal(Long id, WithdrawalInput withdrawalInput){
        Withdrawal withdrawal = withdrawalRepository.findById(id).get();
        if(!isNull(withdrawalInput.getStatus()) && !withdrawalInput.getStatus().isEmpty()){
            withdrawal.setStatus(EStatus.valueOf(withdrawalInput.getStatus()));
        }else{
            withdrawal.setStatus(EStatus.E100);
        }
        if(!isNull(withdrawalInput.getAccountNumber()) && !withdrawalInput.getAccountNumber().isEmpty()){
            withdrawal.setAccountNumber(withdrawalInput.getAccountNumber());
        }
        if(!isNull(withdrawalInput.getAccountHolder()) && !withdrawalInput.getAccountHolder().isEmpty()){
            withdrawal.setAccountHolder(withdrawalInput.getAccountHolder());
        }
        if(!isNull(withdrawalInput.getNameOfBank()) && !withdrawalInput.getNameOfBank().isEmpty()){
            withdrawal.setNameOfBank(withdrawalInput.getNameOfBank());
        }
        if(!isNull(withdrawalInput.getSum())){
            withdrawal.setSum(withdrawalInput.getSum());
        }else {
            withdrawal.setSum(0d);
        }
        return withdrawalRepository.save(withdrawal);
    }
}
