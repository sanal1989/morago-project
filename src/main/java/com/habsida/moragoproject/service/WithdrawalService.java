package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.WithdrawalDao;
import com.habsida.moragoproject.entity.Withdrawal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalService {

    WithdrawalDao withdrawalDao;

    public WithdrawalService(WithdrawalDao withdrawalDao) {
        this.withdrawalDao = withdrawalDao;
    }

    public List<Withdrawal> findAll(){
        return withdrawalDao.findAll();
    }

    public Withdrawal findById(Long id){
        return withdrawalDao.findById(id);
    }

    public Withdrawal addWithdrawal(Withdrawal withdrawal){
        return withdrawalDao.addWithdrawal(withdrawal);
    }

    public void deleteWithdrawal(Long id){
        withdrawalDao.deleteWithdrawal(id);
    }

    public Withdrawal editWithdrawal(Withdrawal withdrawal){
        return withdrawalDao.editWithdrawal(withdrawal);
    }
}
