package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.WithdrawalInput;
import com.habsida.moragoproject.repository.UserRepository;
import com.habsida.moragoproject.repository.WithdrawalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class WithdrawalService {

    WithdrawalRepository withdrawalRepository;
    UserRepository userRepository;

    public WithdrawalService(WithdrawalRepository withdrawalRepository, UserRepository userRepository) {
        this.withdrawalRepository = withdrawalRepository;
        this.userRepository = userRepository;
    }

    public List<Withdrawal> findAll(){
        return withdrawalRepository.findAll();
    }

    public Withdrawal findById(Long id){
        return withdrawalRepository.findById(id).orElseThrow(()->new RuntimeException("Withdrawal doesn't find by Id"));
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
        }else {
            withdrawal.setAccountHolder("EMPTY");
        }
        if(!isNull(withdrawalInput.getAccountHolder()) && !withdrawalInput.getAccountHolder().isEmpty()){
            withdrawal.setAccountHolder(withdrawalInput.getAccountHolder());
        }else {
            withdrawal.setAccountHolder("EMPTY");
        }
        if(!isNull(withdrawalInput.getNameOfBank()) && !withdrawalInput.getNameOfBank().isEmpty()){
            withdrawal.setNameOfBank(withdrawalInput.getNameOfBank());
        }else {
            withdrawal.setNameOfBank("EMPTY");
        }
        if(!isNull(withdrawalInput.getSum())){
            withdrawal.setSum(withdrawalInput.getSum());
        }else {
            withdrawal.setSum(0d);
        }
        if(!isNull(withdrawalInput.getUser())){
            withdrawal.setUser(userRepository.findById(withdrawalInput.getUser())
                    .orElseThrow(()->new RuntimeException("Withdrawal -> User doesn't find by Id")));
        }
        return withdrawalRepository.save(withdrawal);
    }

    public String deleteWithdrawalById(Long id){
        withdrawalRepository.deleteById(id);
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
        }else {
            withdrawal.setAccountHolder("EMPTY");
        }
        if(!isNull(withdrawalInput.getAccountHolder()) && !withdrawalInput.getAccountHolder().isEmpty()){
            withdrawal.setAccountHolder(withdrawalInput.getAccountHolder());
        }else {
            withdrawal.setAccountHolder("EMPTY");
        }
        if(!isNull(withdrawalInput.getNameOfBank()) && !withdrawalInput.getNameOfBank().isEmpty()){
            withdrawal.setNameOfBank(withdrawalInput.getNameOfBank());
        }else {
            withdrawal.setNameOfBank("EMPTY");
        }
        if(!isNull(withdrawalInput.getSum())){
            withdrawal.setSum(withdrawalInput.getSum());
        }else {
            withdrawal.setSum(0d);
        }
        return withdrawalRepository.save(withdrawal);
    }
}
