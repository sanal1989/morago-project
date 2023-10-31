package com.habsida.moragoproject.service;

import com.habsida.moragoproject.configuration.security.JwtUtil;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.ResetPasswordDTO;
import com.habsida.moragoproject.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Service
public class ResetPasswordService {
    private Long expirationTime = 5l;
    private static Map<String, String> resetPasswordMapCode = new HashMap<>();
    PasswordEncoder passwordEncoder;
    UserService userService;
    JwtUtil jwtUtil;
    public ResetPasswordService(PasswordEncoder passwordEncoder,
                                UserService userService,
                                JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public String createCode(String phone){
        if(!userService.existsByPhone(phone)){
            throw new NotFoundByIdException("not fount by Phone");
        }
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(256));
        }
        resetPasswordMapCode.put(phone, stringBuilder.toString());
        return stringBuilder.toString();
    }

    public ResetPasswordDTO createHashCode(String phone){
        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setTime(LocalDateTime.now().plusMinutes(expirationTime));
        resetPasswordDTO.setHashCode(passwordEncoder.encode(phone + resetPasswordMapCode.get(phone) + resetPasswordDTO.getTime()));
        return resetPasswordDTO;
    }

    public String createToken(String hashCode, String time, String phone, String code) {
        String newHashCode =  passwordEncoder.encode(phone + code + time);
        System.out.println(phone+" " + code +" " + time);
        System.out.println(passwordEncoder.matches(phone + code + time,newHashCode));
        System.out.println(passwordEncoder.matches(phone + code + time,hashCode));
        if(!(passwordEncoder.matches(phone + code + time,newHashCode)
            && passwordEncoder.matches(phone + code + time, hashCode))){
            throw new RuntimeException("hash code doesn't equals");
        }
        return jwtUtil.generateTokenResetPassword(phone, time);
    }

    public void resetNewPassword(String token, String password) {
        jwtUtil.validateRefreshPasswordToken(token);
        String phone = jwtUtil.getPhoneFromRefreshPasswordToken(token);
        User user = userService.findByPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
    }
}
