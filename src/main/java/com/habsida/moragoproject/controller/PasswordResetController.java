package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetInput;
import com.habsida.moragoproject.service.PasswordResetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public PasswordReset createPasswordReset(@Argument PasswordResetInput passwordResetInput){
        return passwordResetService.createPasswordReset(passwordResetInput);
    }

    @MutationMapping
    public String deletePasswordResetById(@Argument Long id){
        return passwordResetService.deletePasswordResetById(id);
    }

    @MutationMapping
    public PasswordReset updatePasswordReset(@Argument Long id, @Argument PasswordResetInput passwordResetInput){
        return passwordResetService.updatePasswordReset(id, passwordResetInput);
    }
}
