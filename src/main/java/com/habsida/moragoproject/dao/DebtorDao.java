package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.DebtorRepository;
import com.habsida.moragoproject.model.entity.Debtor;
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
        if(debtor.getIsPaid() != debtorFromDB.getIsPaid()){
            debtorFromDB.setIsPaid(debtor.getIsPaid());
        }

        return debtorRepository.save(debtorFromDB);
    }
}