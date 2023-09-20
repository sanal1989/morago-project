package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Debtor;
import com.habsida.moragoproject.model.input.DebtorInput;
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
    public Debtor createDebtor(@Argument DebtorInput debtorInput){
        return debtorService.createDebtor(debtorInput);
    }

    @MutationMapping
    public void deleteDebtorById(@Argument Long id){
        debtorService.deleteDebtorById(id);
    }

    @MutationMapping
    public Debtor updateDebtor(@Argument Long id, @Argument DebtorInput debtorInput){
        return debtorService.updateDebtor(id, debtorInput);
    }
}

