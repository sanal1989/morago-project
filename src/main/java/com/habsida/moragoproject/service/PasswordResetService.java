package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.PasswordResetDao;
import com.habsida.moragoproject.entity.PasswordReset;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordResetService {

    PasswordResetDao passwordResetDao;

    public PasswordResetService(PasswordResetDao passwordResetDao) {
        this.passwordResetDao = passwordResetDao;
    }

    public List<PasswordReset> findAll(){
        return passwordResetDao.findAll();
    }

    public PasswordReset findById(Long id){
        return passwordResetDao.findById(id);
    }

    public PasswordReset addPasswordReset(PasswordReset passwordReset){
        return passwordResetDao.addPasswordReset(passwordReset);
    }

    public void deletePasswordReset(Long id){
        passwordResetDao.deletePasswordReset(id);
    }

    public PasswordReset editPasswordReset(PasswordReset passwordReset){
        return passwordResetDao.editPasswordReset(passwordReset);
    }
}
