package com.habsida.moragoproject.service;

import com.habsida.moragoproject.configuration.utils.JwtUtil;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.PasswordResetInput;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.habsida.moragoproject.model.ResponsePasswordReset;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
@Service
public class PasswordResetService {

    PasswordResetRepository passwordResetRepository;
    PasswordEncoder passwordEncoder;
    UserService userService;
    JwtUtil jwtUtil;

    public PasswordResetService(PasswordResetRepository passwordResetRepository,
                                PasswordEncoder passwordEncoder,
                                UserService userService,
                                JwtUtil jwtUtil) {
        this.passwordResetRepository = passwordResetRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    private Long expirationTime = 5l;

    public List<PasswordReset> findAll(){
        return passwordResetRepository.findAll();
    }

    public List<PasswordReset> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<PasswordReset> pages =passwordResetRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public PasswordReset findById(Long id){
        return passwordResetRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("PasswordReset -> PasswordReset doesn't find by Id " + id));
    }

//    public PasswordReset createPasswordReset(PasswordResetInput passwordResetInput){
//        PasswordReset passwordReset = new PasswordReset();
//        if(!isNull(passwordResetInput.getPhone()) && !passwordResetInput.getPhone().isEmpty()){
//            passwordReset.setPhone(passwordResetInput.getPhone());
//        }
////        if(!isNull(passwordResetInput.getToken()) && !passwordResetInput.getToken().isEmpty()){
////            passwordReset.setToken(passwordResetInput.getToken());
////        }
//        if(!isNull(passwordResetInput.getResetCode())){
//            passwordReset.setResetCode(passwordResetInput.getResetCode());
//        }
//        return passwordResetRepository.save(passwordReset);
//    }

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

    public ResponsePasswordReset createPasswordReset(String phone){
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

        passwordResetRepository.save(passwordReset);

        ResponsePasswordReset responsePasswordReset = new ResponsePasswordReset();
        responsePasswordReset.setId(passwordReset.getId());
        responsePasswordReset.setLocalDateTime(localDateTime);
        responsePasswordReset.setHashCode(Integer.toString((phone + resetCode + localDateTime.toString().substring(0,23) ).hashCode()));
        return responsePasswordReset;
    }


    public String createPasswordResetToken(String hashCode, String time, Long id) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(time.substring(0,23).replace("T", " "),formatter);
        PasswordReset passwordReset = passwordResetRepository.findById(id).get();
        if(!LocalDateTime.now().isBefore(localDateTime)){
            throw new RuntimeException("time is expired");
        }
        String newHashCode =  Integer.toString((passwordReset.getPhone() + passwordReset.getResetCode() + localDateTime.toString()).hashCode());
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
