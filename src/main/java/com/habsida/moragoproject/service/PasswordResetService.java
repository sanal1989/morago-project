package com.habsida.moragoproject.service;

import com.habsida.moragoproject.configuration.utils.JwtUtil;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.PasswordResetInput;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
public class PasswordResetService {

    PasswordResetRepository passwordResetRepository;
    PasswordEncoder passwordEncoder;
    UserService userService;
    JwtUtil jwtUtil;

    private Long expirationTime = 5l;

    public PasswordResetService(PasswordResetRepository passwordResetRepository, PasswordEncoder passwordEncoder, UserService userService, JwtUtil jwtUtil) {
        this.passwordResetRepository = passwordResetRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public List<PasswordReset> findAll(){
        return passwordResetRepository.findAll();
    }

    public PasswordReset findById(Long id){
        return passwordResetRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("PasswordReset -> PasswordReset doesn't find by Id " + id));
    }

    public PasswordReset createPasswordReset(PasswordResetInput passwordResetInput){
        PasswordReset passwordReset = new PasswordReset();
        if(!isNull(passwordResetInput.getPhone()) && !passwordResetInput.getPhone().isEmpty()){
            passwordReset.setPhone(passwordResetInput.getPhone());
        }
//        if(!isNull(passwordResetInput.getToken()) && !passwordResetInput.getToken().isEmpty()){
//            passwordReset.setToken(passwordResetInput.getToken());
//        }
        if(!isNull(passwordResetInput.getResetCode())){
            passwordReset.setResetCode(passwordResetInput.getResetCode());
        }
        return passwordResetRepository.save(passwordReset);
    }

    public String deletePasswordResetById(Long id){
        try{
            passwordResetRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "PasswordReset with Id "+id+" deleted";
    }

    public PasswordReset updatePasswordReset(Long id, PasswordResetInput passwordResetInput){
        PasswordReset passwordReset =passwordResetRepository.findById(id).get();
        if(!isNull(passwordResetInput.getPhone()) && !passwordResetInput.getPhone().isEmpty()){
            passwordReset.setPhone(passwordResetInput.getPhone());
        }
//        if(!isNull(passwordResetInput.getToken()) && !passwordResetInput.getToken().isEmpty()){
//            passwordReset.setToken(passwordResetInput.getToken());
//        }
        if(!isNull(passwordResetInput.getResetCode())){
            passwordReset.setResetCode(passwordResetInput.getResetCode());
        }else {
            passwordReset.setResetCode(0);
        }
        return passwordResetRepository.save(passwordReset);
    }

    public PasswordReset createCode(String phone){
        if(!userService.existsByPhone(phone)){
            throw new NotFoundByIdException("not fount by Phone");
        }
        Random random = new Random();
        Integer resetCode = random.nextInt(10000);

        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setResetCode(resetCode);
        passwordReset.setPhone(phone);

        User user = userService.findByPhone(phone);
        passwordReset.setUser(user);

        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(expirationTime);
        passwordReset.setTime(localDateTime);
        passwordReset.setToken(Integer.toString((phone + resetCode).hashCode()));
        passwordResetRepository.save(passwordReset);
        return passwordReset;
    }


    public String createToken(String hashCode,Integer resetCode, Long id) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        LocalDateTime localDateTime = LocalDateTime.parse(time.substring(0,23).replace("T", " "),formatter);
        PasswordReset passwordReset = passwordResetRepository.findById(id).get();
        if(!LocalDateTime.now().isBefore(passwordReset.getTime())){
            throw new RuntimeException("time is expired");
        }
        String newHashCode =  Integer.toString((passwordReset.getPhone() + resetCode).hashCode());
        System.out.println(newHashCode);
        System.out.println(hashCode);
        if(!newHashCode.equals(hashCode)){
            throw new RuntimeException("hash code doesn't equals");
        }
        return jwtUtil.generateTokenResetPassword(passwordReset.getPhone());
    }

    public void resetNewPassword(String token, String password) {
        jwtUtil.validateRefreshPasswordToken(token);
        String phone = jwtUtil.getPhoneFromRefreshPasswordToken(token);
        User user = userService.findByPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
    }
}
