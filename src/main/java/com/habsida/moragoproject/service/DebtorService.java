package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.input.DebtorInput;
import com.habsida.moragoproject.repository.DebtorRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DebtorService {

    DebtorRepository debtorRepository;
    UserService userService;

    public DebtorService(DebtorRepository debtorRepository, @Lazy UserService userService) {
        this.debtorRepository = debtorRepository;
        this.userService = userService;
    }

    public List<Debtor> findAll(){
        return debtorRepository.findAll();
    }

    public List<Debtor> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<Debtor> pages = debtorRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public Debtor findById(Long id){
        return debtorRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException("Debtor -> Debtor doesn't find by Id " +id));
    }

    public Debtor createDebtor(DebtorInput debtorInput){

        Debtor debtor = new Debtor();

        if(!isNull(debtorInput.getAccountHolder()) && !debtorInput.getAccountHolder().isEmpty()){
            debtor.setAccountHolder(debtorInput.getAccountHolder());
        }
        if(!isNull(debtorInput.getNameOfBank()) && !debtorInput.getNameOfBank().isEmpty()){
            debtor.setNameOfBank(debtorInput.getNameOfBank());
        }
        if(!isNull(debtorInput.getIsPaid())){
            debtor.setIsPaid(debtorInput.getIsPaid());
        }else{
            debtor.setIsPaid(false);
        }
        if(!isNull(debtorInput.getUser())){
            debtor.setUser(userService.findById(debtorInput.getUser()));
        }
        return debtorRepository.save(debtor);
    }

    public String deleteDebtorById(Long id){
        try{
            debtorRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Debtor with Id "+id+" deleted";
    }

    public Debtor updateDebtor(Long id, DebtorInput debtorInput){

        Debtor debtor = debtorRepository.findById(id).get();

        if(!isNull(debtorInput.getAccountHolder()) && !debtorInput.getAccountHolder().isEmpty()){
            debtor.setAccountHolder(debtorInput.getAccountHolder());
        }
        if(!isNull(debtorInput.getNameOfBank()) && !debtorInput.getNameOfBank().isEmpty()){
            debtor.setNameOfBank(debtorInput.getNameOfBank());
        }
        if(!isNull(debtorInput.getIsPaid())){
            debtor.setIsPaid(debtorInput.getIsPaid());
        }else{
            debtor.setIsPaid(false);
        }
        return debtorRepository.save(debtor);
    }
}
