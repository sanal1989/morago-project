package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.DebtorRepository;
import com.habsida.moragoproject.entity.Coin;
import com.habsida.moragoproject.entity.Debtor;
import com.habsida.moragoproject.entity.EStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebtorDao {

    DebtorRepository debtorRepository;

    public DebtorDao(DebtorRepository debtorRepository) {
        this.debtorRepository = debtorRepository;
    }

    public List<Debtor> findAll(){
        return debtorRepository.findAll();
    }

    public Debtor findById(Long id){
        return debtorRepository.findById(id).get();
    }

    public Debtor addDebtor(Debtor debtor){
        return debtorRepository.save(debtor);
    }

    public void deleteDebtor(Long id){
        debtorRepository.deleteById(id);
    }

    public Debtor editDebtor(Debtor debtor){
        Debtor debtorFromDB = debtorRepository.findById(debtor.getId()).get();
        if(!debtor.getAccountHolder().equals(debtorFromDB.getAccountHolder())){
            debtorFromDB.setAccountHolder(debtor.getAccountHolder());
        }
        if(!debtor.getNameOfBank().equals(debtorFromDB.getNameOfBank())){
            debtorFromDB.setNameOfBank(debtor.getNameOfBank());
        }
        if(debtor.getPaid() != debtorFromDB.getPaid()){
            debtorFromDB.setPaid(debtor.getPaid());
        }

        return debtorRepository.save(debtorFromDB);
    }
}