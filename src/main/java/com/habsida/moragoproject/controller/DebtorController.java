package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.service.DebtorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DebtorController {

    DebtorService debtorService;

    public DebtorController(DebtorService debtorService) {
        this.debtorService = debtorService;
    }

    @QueryMapping
    public List<Debtor> findAllDebtor(){
        return debtorService.findAll();
    }

    @QueryMapping
    public Debtor findDebtorById(@Argument Long id){
        return debtorService.findById(id);
    }

    @MutationMapping
    public Debtor addDebtor(@Argument String accountHolder, @Argument Boolean isPaid, @Argument String nameOfBank){
        Debtor debtor = new Debtor();
        debtor.setIsPaid(isPaid);
        debtor.setAccountHolder(accountHolder);
        debtor.setNameOfBank(nameOfBank);
        return debtorService.addDebtor(debtor);
    }

    @MutationMapping
    public void deleteDebtor(@Argument Long id){
        debtorService.deleteDebtor(id);
    }

    @MutationMapping
    public Debtor editDebtor(@Argument Long id, @Argument String accountHolder, @Argument Boolean isPaid, @Argument String nameOfBank){
        Debtor debtor = new Debtor();
        debtor.setId(id);
        debtor.setIsPaid(isPaid);
        debtor.setAccountHolder(accountHolder);
        debtor.setNameOfBank(nameOfBank);
        return debtorService.editDebtor(debtor);
    }
}

