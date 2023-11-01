package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.service.PasswordResetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ResetPasswordController {

    PasswordResetService passwordResetService;

    public ResetPasswordController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @MutationMapping
    public PasswordReset resetPasswordGetCode(@Argument String phone){
        return passwordResetService.createCode(phone);
    }


    @MutationMapping
    public String generateTokenResetPassword(@Argument String hashCode,
                                     @Argument Long id,
                                     @Argument Integer code){
        return passwordResetService.createToken(hashCode, code, id);
    }

    @MutationMapping
    public void resetNewPassword(@Argument String token,
                                 @Argument String password){
        passwordResetService.resetNewPassword(token, password);
    }
}
