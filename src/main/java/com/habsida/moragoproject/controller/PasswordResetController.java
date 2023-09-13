package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.PasswordReset;
import com.habsida.moragoproject.service.PasswordResetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PasswordResetController {

    PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @QueryMapping
    public List<PasswordReset> findAllPasswordReset(){
        return passwordResetService.findAll();
    }

    @QueryMapping
    public PasswordReset findPasswordResetById(@Argument Long id){
        return passwordResetService.findById(id);
    }

    @MutationMapping
    public PasswordReset addPasswordReset(@Argument String phone, @Argument Integer resetCode, @Argument String token){
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setPhone(phone);
        passwordReset.setResetCode(resetCode);
        passwordReset.setToken(token);
        return passwordResetService.addPasswordReset(passwordReset);
    }

    @MutationMapping
    public void deletePasswordReset(@Argument Long id){
        passwordResetService.deletePasswordReset(id);
    }

    @MutationMapping
    public PasswordReset editPasswordReset(@Argument Long id, @Argument String phone, @Argument Integer resetCode, @Argument String token){
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setId(id);
        passwordReset.setPhone(phone);
        passwordReset.setResetCode(resetCode);
        passwordReset.setToken(token);
        return passwordResetService.editPasswordReset(passwordReset);
    }
}
