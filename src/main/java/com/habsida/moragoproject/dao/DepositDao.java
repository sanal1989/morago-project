package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.DepositRepository;
import com.habsida.moragoproject.entity.Debtor;
import com.habsida.moragoproject.entity.Deposit;
import com.habsida.moragoproject.entity.EStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepositDao {

    DepositRepository depositRepository;

    public DepositDao(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<Deposit> findAll(){
        return depositRepository.findAll();
    }

    public Deposit findById(Long id){
        return depositRepository.findById(id).get();
    }

    public Deposit addDeposit(Deposit deposit){
        return depositRepository.save(deposit);
    }

    public void deleteDeposit(Long id){
        depositRepository.deleteById(id);
    }

    public Deposit editDeposit(Deposit deposit){
        Deposit depositFromDB = depositRepository.findById(deposit.getId()).get();
        if(!deposit.getAccountHolder().equals(depositFromDB.getAccountHolder())){
            depositFromDB.setAccountHolder(deposit.getAccountHolder());
        }
        if(!deposit.getNameOfBank().equals(depositFromDB.getNameOfBank())){
            depositFromDB.setNameOfBank(deposit.getNameOfBank());
        }
        if(deposit.getCoin() != depositFromDB.getCoin()){
            depositFromDB.setCoin(deposit.getCoin());
        }
        if(deposit.getWon() != depositFromDB.getWon()){
            depositFromDB.setWon(deposit.getWon());
        }
        if(deposit.getStatus() != depositFromDB.getStatus()){
            depositFromDB.setStatus(deposit.getStatus());
        }
        return depositRepository.save(depositFromDB);
    }
}
