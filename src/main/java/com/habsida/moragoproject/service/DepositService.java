package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Deposit;
import com.habsida.moragoproject.model.enums.EStatus;
import com.habsida.moragoproject.model.input.DepositInput;
import com.habsida.moragoproject.repository.DepositRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class DepositService {

    DepositRepository depositRepository;
    UserService userService;

    public DepositService(DepositRepository depositRepository, UserService userService) {
        this.depositRepository = depositRepository;
        this.userService = userService;
    }

    public List<Deposit> findAll(){
        return depositRepository.findAll();
    }

    public Deposit findById(Long id){
        return depositRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("Deposit -> Deposit doesn't find by Id " + id));
    }

    public Deposit createDeposit(DepositInput depositInput){

        Deposit deposit = new Deposit();

        if(!isNull(depositInput.getAccountHolder()) && !depositInput.getAccountHolder().isEmpty()){
            deposit.setAccountHolder(depositInput.getAccountHolder());
        }
        if(!isNull(depositInput.getNameOfBank()) && !depositInput.getNameOfBank().isEmpty()){
            deposit.setNameOfBank(depositInput.getNameOfBank());
        }
        if(!isNull(depositInput.getCoin())){
            deposit.setCoin(depositInput.getCoin());
        }else{
            deposit.setCoin(0d);
        }
        if(!isNull(depositInput.getWon())){
            deposit.setWon(depositInput.getWon());
        }else{
            deposit.setWon(0d);
        }
        if(!isNull(depositInput.getStatus()) && !depositInput.getStatus().isEmpty()){
            deposit.setStatus(EStatus.valueOf(depositInput.getStatus()));
        }else{
            deposit.setStatus(EStatus.E100);
        }
        if(!isNull(depositInput.getUser())){
            deposit.setUser(userService.findById(depositInput.getUser()));
        }
        return depositRepository.save(deposit);
    }

    public String deleteDepositById(Long id){
        try{
            depositRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Deposit with Id "+id+" deleted";
    }

    public Deposit updateDeposit(Long id, DepositInput depositInput){

        Deposit deposit = depositRepository.findById(id).get();

        if(!isNull(depositInput.getAccountHolder()) && !depositInput.getAccountHolder().isEmpty()){
            deposit.setAccountHolder(depositInput.getAccountHolder());
        }
        if(!isNull(depositInput.getNameOfBank()) && !depositInput.getNameOfBank().isEmpty()){
            deposit.setNameOfBank(depositInput.getNameOfBank());
        }
        if(!isNull(depositInput.getCoin())){
            deposit.setCoin(depositInput.getCoin());
        }else{
            deposit.setCoin(0d);
        }
        if(!isNull(depositInput.getWon())){
            deposit.setWon(depositInput.getWon());
        }else{
            deposit.setWon(0d);
        }
        if(!isNull(depositInput.getStatus()) && !depositInput.getStatus().isEmpty()){
            deposit.setStatus(EStatus.valueOf(depositInput.getStatus()));
        }else{
            deposit.setStatus(EStatus.E100);
        }
        return depositRepository.save(deposit);
    }
}
