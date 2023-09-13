package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.PasswordResetRepository;
import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.entity.PasswordReset;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordResetDao {

    PasswordResetRepository passwordResetRepository;

    public PasswordResetDao(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    public List<PasswordReset> findAll(){
        return passwordResetRepository.findAll();
    }

    public PasswordReset findById(Long id){
        return passwordResetRepository.findById(id).get();
    }

    public PasswordReset addPasswordReset(PasswordReset passwordReset){
        return passwordResetRepository.save(passwordReset);
    }

    public void deletePasswordReset(Long id){
        passwordResetRepository.deleteById(id);
    }

    public PasswordReset editPasswordReset(PasswordReset passwordReset){
        PasswordReset passwordResetFromDB = passwordResetRepository.findById(passwordReset.getId()).get();
        if(!passwordReset.getPhone().equals(passwordResetFromDB.getPhone())){
            passwordResetFromDB.setPhone(passwordReset.getPhone());
        }
        if(!passwordReset.getToken().equals(passwordResetFromDB.getToken())){
            passwordResetFromDB.setToken(passwordReset.getToken());
        }
        if(passwordReset.getResetCode() != passwordResetFromDB.getResetCode()){
            passwordResetFromDB.setResetCode(passwordReset.getResetCode());
        }
        return passwordResetRepository.save(passwordResetFromDB);
    }
}