package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundById;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.UserInput;
import com.habsida.moragoproject.repository.RoleRepository;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import com.habsida.moragoproject.repository.UserProfileRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private TranslatorProfileRepository translatorProfileRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenService refreshTokenService;

    public UserService(UserRepository userRepository,
                       UserProfileRepository userProfileRepository,
                       TranslatorProfileRepository translatorProfileRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.translatorProfileRepository = translatorProfileRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new NotFoundById("User -> User doesn't find by Id " + id));
    }

    @Transactional
    public User createUser(UserInput userInput){
        User user = new User();
        if(!isNull(userInput.getFirstName()) && !userInput.getFirstName().isEmpty()){
            user.setFirstName(userInput.getFirstName());
        }else {
            user.setFirstName("EMPTY");
        }
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }else {
            user.setLastName("EMPTY");
        }
        if(!isNull(userInput.getApnToken()) && !userInput.getApnToken().isEmpty()){
            user.setApnToken(userInput.getApnToken());
        }else {
            user.setApnToken("EMPTY");
        }
        if(!isNull(userInput.getFcmToken()) && !userInput.getFcmToken().isEmpty()){
            user.setFcmToken(userInput.getFcmToken());
        }else {
            user.setFcmToken("EMPTY");
        }
        if(!isNull(userInput.getPassword()) && !userInput.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        }else {
            user.setPassword("EMPTY");
        }
        if(!isNull(userInput.getPhone()) && !userInput.getPhone().isEmpty()){
            user.setPhone(userInput.getPhone());
        }else {
            user.setPhone("EMPTY");
        }
        if(!isNull(userInput.getBalance())){
            user.setBalance(userInput.getBalance());
        }else {
            user.setBalance(0d);
        }
        if(!isNull(userInput.getRatings())){
            user.setRatings(userInput.getRatings());
        }else {
            user.setRatings(0d);
        }
        if(!isNull(userInput.getIsActive())){
            user.setIsActive(userInput.getIsActive());
        }else {
            user.setIsActive(false);
        }
        if(!isNull(userInput.getIsDebtor())){
            user.setIsDebtor(userInput.getIsDebtor());
        }else {
            user.setIsDebtor(false);
        }
        if(!isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }else {
            user.setOnBoardingStatus(0);
        }
        if(!isNull(userInput.getTotalRatings())){
            user.setTotalRatings(userInput.getTotalRatings());
        }else {
            user.setTotalRatings(0);
        }
        if(!isNull(userInput.getUserProfile())){
            user.setUserProfile(userProfileRepository.findById(userInput.getUserProfile())
                    .orElseThrow(()->new NotFoundById("User- > UserProfile doesn't find by Id " + userInput.getUserProfile())));
        }else{
            user.setUserProfile(null);
        }
        if(!isNull(userInput.getTranslatorProfile())){
            user.setTranslatorProfile(translatorProfileRepository.findById(userInput.getTranslatorProfile())
                    .orElseThrow(()->new NotFoundById("User- > TranslatorProfile doesn't find by Id " + userInput.getTranslatorProfile())));
        }else{
            user.setTranslatorProfile(null);
        }
        if(!isNull(userInput.getRoles())){
            List<String> roles = userInput.getRoles();
            Set<Role> rolestoBD = user.getRoles();
            if(roles.contains("ADMIN")) {
                rolestoBD.add(roleRepository.findByName(ERole.ADMIN)
                        .orElseThrow(()-> new NotFoundById("User -> Role.ADMIN doesn't exist")));
            }
            if(roles.contains("USER")){
                rolestoBD.add(roleRepository.findByName(ERole.USER)
                        .orElseThrow(()-> new NotFoundById("User -> Role.USER doesn't exist")));
            }
            if(roles.contains("TRANSLATOR")){
                rolestoBD.add(roleRepository.findByName(ERole.TRANSLATOR)
                        .orElseThrow(()-> new NotFoundById("User -> Role.TRANSLATOR doesn't exist")));
            }
            user.setRoles(rolestoBD);
        }
        User userDB = userRepository.save(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInput.getFirstName());
        userDB.setRefreshToken(refreshToken);
        userRepository.save(userDB);
        return userDB;
    }

    public String deleteUserById(Long id){
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            throw  new NotFoundById(e.getMessage());
        }
        return "User with Id "+id+" deleted";
    }

    public User updateUser(Long id, UserInput userInput){
        User user = userRepository.findById(id).get();
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }else {
            user.setLastName("EMPTY");
        }
        if(!isNull(userInput.getApnToken()) && !userInput.getApnToken().isEmpty()){
            user.setApnToken(userInput.getApnToken());
        }else {
            user.setApnToken("EMPTY");
        }
        if(!isNull(userInput.getFcmToken()) && !userInput.getFcmToken().isEmpty()){
            user.setFcmToken(userInput.getFcmToken());
        }else {
            user.setFcmToken("EMPTY");
        }
        if(!isNull(userInput.getPhone()) && !userInput.getPhone().isEmpty()){
            user.setPhone(userInput.getPhone());
        }else {
            user.setPhone("EMPTY");
        }
        if(!isNull(userInput.getBalance())){
            user.setBalance(userInput.getBalance());
        }else {
            user.setBalance(0d);
        }
        if(!isNull(userInput.getRatings())){
            user.setRatings(userInput.getRatings());
        }else {
            user.setRatings(0d);
        }
        if(!isNull(userInput.getIsActive())){
            user.setIsActive(userInput.getIsActive());
        }else {
            user.setIsActive(false);
        }
        if(!isNull(userInput.getIsDebtor())){
            user.setIsDebtor(userInput.getIsDebtor());
        }else {
            user.setIsDebtor(false);
        }
        if(!isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }else {
            user.setOnBoardingStatus(0);
        }
        if(!isNull(userInput.getTotalRatings())){
            user.setTotalRatings(userInput.getTotalRatings());
        }else {
            user.setTotalRatings(0);
        }
        return userRepository.save(user);
    }
}
