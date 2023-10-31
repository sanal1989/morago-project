package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.ResetPasswordDTO;
import com.habsida.moragoproject.service.ResetPasswordService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ResetPasswordController {
    ResetPasswordService resetPasswordService;

    public ResetPasswordController(ResetPasswordService resetPasswordService) {
        this.resetPasswordService = resetPasswordService;
    }

    @QueryMapping
    public String resetPasswordGetCode(@Argument String phone){
        return resetPasswordService.createCode(phone);
    }

    @MutationMapping
    public ResetPasswordDTO resetPasswordHash(@Argument String phone){
        return resetPasswordService.createHashCode(phone);
    }

    @MutationMapping
    public String generateTokenResetPassword(@Argument String hashCode,
                                     @Argument String time,
                                     @Argument String phone,
                                     @Argument String code){
        return resetPasswordService.createToken(hashCode, time, phone, code);
    }

    @MutationMapping
    public void resetNewPassword(@Argument String token,
                                 @Argument String password){
        resetPasswordService.resetNewPassword(token, password);
    }
}
