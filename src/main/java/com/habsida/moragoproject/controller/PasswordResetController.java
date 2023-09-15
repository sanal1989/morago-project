package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordInput;
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
    public PasswordReset addPasswordReset(@Argument PasswordInput passwordInput){
        return passwordResetService.addPasswordReset(passwordInput);
    }

    @MutationMapping
    public void deletePasswordReset(@Argument Long id){
        passwordResetService.deletePasswordReset(id);
    }

    @MutationMapping
    public PasswordReset editPasswordReset(@Argument Long id, @Argument PasswordInput passwordInput){
        return passwordResetService.editPasswordReset(id, passwordInput);
    }
}
