package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.PasswordResetDao;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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

    public PasswordReset addPasswordReset(PasswordInput passwordInput){
        PasswordReset passwordReset = new PasswordReset();
        if(isNull(passwordInput.getPhone()) || passwordInput.getPhone().isEmpty()){
            passwordReset.setPhone("EMPTY");
        }else {
            passwordReset.setPhone(passwordInput.getPhone());
        }
        if(isNull(passwordInput.getResetCode())){
            passwordReset.setResetCode(0);
        }else {
            passwordReset.setResetCode(passwordInput.getResetCode());
        }
        if(isNull(passwordInput.getToken()) || passwordInput.getToken().isEmpty()){
            passwordReset.setToken("EMPTY");
        }else {
            passwordReset.setToken(passwordInput.getToken());
        }
        return passwordResetDao.addPasswordReset(passwordReset);
    }

    public void deletePasswordReset(Long id){
        passwordResetDao.deletePasswordReset(id);
    }

    public PasswordReset editPasswordReset(Long id, PasswordInput passwordInput){
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setId(id);
        if(isNull(passwordInput.getPhone()) || passwordInput.getPhone().isEmpty()){
            passwordReset.setPhone("EMPTY");
        }else {
            passwordReset.setPhone(passwordInput.getPhone());
        }
        if(isNull(passwordInput.getResetCode())){
            passwordReset.setResetCode(0);
        }else {
            passwordReset.setResetCode(passwordInput.getResetCode());
        }
        if(isNull(passwordInput.getToken()) || passwordInput.getToken().isEmpty()){
            passwordReset.setToken("EMPTY");
        }else {
            passwordReset.setToken(passwordInput.getToken());
        }
        return passwordResetDao.editPasswordReset(passwordReset);
    }
}
