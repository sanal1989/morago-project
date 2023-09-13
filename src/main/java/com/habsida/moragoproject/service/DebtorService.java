package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.DebtorDao;
import com.habsida.moragoproject.entity.Debtor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtorService {

    DebtorDao debtorDao;

    public DebtorService(DebtorDao debtorDao) {
        this.debtorDao = debtorDao;
    }

    public List<Debtor> findAll(){
        return debtorDao.findAll();
    }

    public Debtor findById(Long id){
        return debtorDao.findById(id);
    }

    public Debtor addDebtor(Debtor debtor){
        return debtorDao.addDebtor(debtor);
    }

    public void deleteDebtor(Long id){
        debtorDao.deleteDebtor(id);
    }

    public Debtor editDebtor(Debtor debtor){
        return debtorDao.editDebtor(debtor);
    }
}
