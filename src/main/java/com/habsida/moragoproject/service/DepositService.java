package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.DepositDao;
import com.habsida.moragoproject.entity.Deposit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    DepositDao depositDao;

    public DepositService(DepositDao depositDao) {
        this.depositDao = depositDao;
    }

    public List<Deposit> findAll(){
        return depositDao.findAll();
    }

    public Deposit findById(Long id){
        return depositDao.findById(id);
    }

    public Deposit addDeposit(Deposit deposit){
        return depositDao.addDeposit(deposit);
    }

    public void deleteDeposit(Long id){
        depositDao.deleteDeposit(id);
    }

    public Deposit editDeposit(Deposit deposit){
        return depositDao.editDeposit(deposit);
    }
}
