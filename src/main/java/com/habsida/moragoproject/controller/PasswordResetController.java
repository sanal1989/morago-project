package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.ResponsePasswordReset;
import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.input.PasswordResetInput;
import com.habsida.moragoproject.service.PasswordResetService;
import com.habsida.moragoproject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<PasswordReset> findAllPasswordResetPagination(@Argument int offset, @Argument int limit){
        return passwordResetService.findAll(offset, limit);
    }
    @QueryMapping
    public PasswordReset findPasswordResetById(@Argument Long id){
        return passwordResetService.findById(id);
    }


    @MutationMapping
    public String deletePasswordResetById(@Argument Long id){
        return passwordResetService.deletePasswordResetById(id);
    }

    @MutationMapping
    public ResponsePasswordReset resetPasswordGetHashCode(@Argument String phone){
        return passwordResetService.createPasswordReset(UserService.checkPhone(phone));
    }


    @MutationMapping
    public String generateTokenResetPassword(@Argument String hashCode,
                                             @Argument Long id,
                                             @Argument String time){
        return passwordResetService.createPasswordResetToken(hashCode, time, id);
    }


}
