package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.UserDao;
import com.habsida.moragoproject.dao.WithdrawalDao;
import com.habsida.moragoproject.model.entity.Withdrawal;
import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.WithdrawalInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class WithdrawalService {

    WithdrawalDao withdrawalDao;
    UserDao userDao;

    public WithdrawalService(WithdrawalDao withdrawalDao, UserDao userDao) {
        this.withdrawalDao = withdrawalDao;
        this.userDao = userDao;
    }

    public List<Withdrawal> findAll(){
        return withdrawalDao.findAll();
    }

    public Withdrawal findById(Long id){
        return withdrawalDao.findById(id);
    }

    public Withdrawal addWithdrawal(WithdrawalInput withdrawalInput){
        Withdrawal withdrawal = new Withdrawal();
        if(isNull(withdrawalInput.getStatus()) || withdrawalInput.getStatus().isEmpty()){
            withdrawal.setStatus(EStatus.E100);
        }else {
            withdrawal.setStatus(EStatus.valueOf(withdrawalInput.getStatus()));
        }
        if( isNull(withdrawalInput.getAccountNumber()) || withdrawalInput.getAccountNumber().isEmpty()){
            withdrawal.setAccountNumber("EMPTY");
        }else {
            withdrawal.setAccountNumber(withdrawalInput.getAccountNumber());
        }
        if( isNull(withdrawalInput.getAccountHolder()) || withdrawalInput.getAccountHolder().isEmpty()){
            withdrawal.setAccountHolder("EMPTY");
        }else {
            withdrawal.setAccountHolder(withdrawalInput.getAccountHolder());
        }
        if(isNull(withdrawalInput.getNameOfBank())){
            if(withdrawalInput.getNameOfBank().isEmpty())
            withdrawal.setNameOfBank("EMPTY");
        }else {
            withdrawal.setNameOfBank(withdrawalInput.getNameOfBank());
        }
        if(isNull(withdrawalInput.getSum())){
            withdrawal.setSum(0d);
        }else {
            withdrawal.setSum(withdrawalInput.getSum());
        }
        if(!isNull(withdrawalInput.getUser())){
            withdrawal.setUser(userDao.findById(withdrawalInput.getUser()));
        }
        return withdrawalDao.addWithdrawal(withdrawal);
    }

    public void deleteWithdrawal(Long id){
        withdrawalDao.deleteWithdrawal(id);
    }

    public Withdrawal editWithdrawal(Long id, WithdrawalInput withdrawalInput){
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(id);
        if(isNull(withdrawalInput.getStatus()) || withdrawalInput.getStatus().isEmpty()){
            withdrawal.setStatus(EStatus.E100);
        }else {
            withdrawal.setStatus(EStatus.valueOf(withdrawalInput.getStatus()));
        }
        if(isNull(withdrawalInput.getAccountNumber()) || withdrawalInput.getAccountNumber().isEmpty()){
            withdrawal.setAccountNumber("EMPTY");
        }else {
            withdrawal.setAccountNumber(withdrawalInput.getAccountNumber());
        }
        if(isNull(withdrawalInput.getAccountHolder()) || withdrawalInput.getAccountHolder().isEmpty()){
            withdrawal.setAccountHolder("EMPTY");
        }else {
            withdrawal.setAccountHolder(withdrawalInput.getAccountHolder());
        }
        if(isNull(withdrawalInput.getNameOfBank()) || withdrawalInput.getNameOfBank().isEmpty()){
            withdrawal.setNameOfBank("EMPTY");
        }else {
            withdrawal.setNameOfBank(withdrawalInput.getNameOfBank());
        }
        if(isNull(withdrawalInput.getSum())){
            withdrawal.setSum(0d);
        }else {
            withdrawal.setSum(withdrawalInput.getSum());
        }
        return withdrawalDao.editWithdrawal(withdrawal);
    }
}
