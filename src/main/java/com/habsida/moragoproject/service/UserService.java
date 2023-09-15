package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.TranslatorProfileDao;
import com.habsida.moragoproject.dao.UserDao;
import com.habsida.moragoproject.dao.UserProfileDao;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.UserInput;
import org.springframework.stereotype.Service;

import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class UserService {

    UserDao userDao;
    UserProfileDao userProfileDao;
    TranslatorProfileDao translatorProfileDao;

    public UserService(UserDao userDao, UserProfileDao userProfileDao, TranslatorProfileDao translatorProfileDao) {
        this.userDao = userDao;
        this.userProfileDao = userProfileDao;
        this.translatorProfileDao = translatorProfileDao;
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(Long id){
        return userDao.findById(id);
    }

    public User addUser(UserInput userInput){
        User user = new User();
        if(isNull(userInput.getFirstName()) || userInput.getFirstName().isEmpty()){
            user.setFirstName("EMPTY");
        }else {
            user.setFirstName(userInput.getFirstName());
        }
        if(isNull(userInput.getLastName()) || userInput.getLastName().isEmpty()){
            user.setLastName("EMPTY");
        }else {
            user.setLastName(userInput.getLastName());
        }
        if(isNull(userInput.getApnToken()) || userInput.getApnToken().isEmpty()){
            user.setApnToken("EMPTY");
        }else {
            user.setApnToken(userInput.getApnToken());
        }
        if(isNull(userInput.getFcmToken()) || userInput.getFcmToken().isEmpty()){
            user.setFcmToken("EMPTY");
        }else {
            user.setFcmToken(userInput.getFcmToken());
        }
        if(isNull(userInput.getPassword()) || userInput.getPassword().isEmpty()){
            user.setPassword("EMPTY");
        }else {
            user.setPassword(userInput.getPassword());
        }
        if(isNull(userInput.getPhone()) || userInput.getPhone().isEmpty()){
            user.setPhone("EMPTY");
        }else {
            user.setPhone(userInput.getPhone());
        }
        if(isNull(userInput.getBalance())){
            user.setBalance(0d);
        }else {
            user.setBalance(userInput.getBalance());
        }
        if(isNull(userInput.getRatings())){
            user.setRatings(0d);
        }else {
            user.setRatings(userInput.getRatings());
        }
        if(isNull(userInput.getIsActive())){
            user.setIsActive(false);
        }else {
            user.setIsActive(userInput.getIsActive());
        }
        if(isNull(userInput.getIsDebtor())){
            user.setIsDebtor(false);
        }else {
            user.setIsDebtor(userInput.getIsDebtor());
        }
        if(isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(0);
        }else {
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }
        if(isNull(userInput.getTotalRatings())){
            user.setTotalRatings(0);
        }else {
            user.setTotalRatings(userInput.getTotalRatings());
        }
        if(!isNull(userInput.getUserProfile())){
            user.setUserProfile(userProfileDao.findById(userInput.getUserProfile()));
        }
        if(!isNull(userInput.getTranslatorProfile())){
            user.setTranslatorProfile(translatorProfileDao.findById(userInput.getTranslatorProfile()));
        }
        if(!isNull(userInput.getRoles())){
            List<String> roles = userInput.getRoles();
            List<Role> rolestoBD = new ArrayList<>();
            if(roles.contains("ADMIN"))rolestoBD.add(new Role(ERole.ADMIN));
            if(roles.contains("USER"))rolestoBD.add(new Role(ERole.USER));
            if(roles.contains("TRANSLATOR"))rolestoBD.add(new Role(ERole.TRANSLATOR));
            user.setRoles(rolestoBD);
        }
        return userDao.addUser(user);
    }

    public void deleteUser(Long id){
        userDao.deleteUser(id);
    }

    public User editUser(Long id, UserInput userInput){
        User user = new User();
        user.setId(id);
        if(isNull(userInput.getFirstName()) || userInput.getFirstName().isEmpty()){
            user.setFirstName("EMPTY");
        }else {
            user.setFirstName(userInput.getFirstName());
        }
        if(isNull(userInput.getLastName()) || userInput.getLastName().isEmpty()){
            user.setLastName("EMPTY");
        }else {
            user.setLastName(userInput.getLastName());
        }
        if(isNull(userInput.getApnToken()) || userInput.getApnToken().isEmpty()){
            user.setApnToken("EMPTY");
        }else {
            user.setApnToken(userInput.getApnToken());
        }
        if(isNull(userInput.getFcmToken()) || userInput.getFcmToken().isEmpty()){
            user.setFcmToken("EMPTY");
        }else {
            user.setFcmToken(userInput.getFcmToken());
        }
        if(isNull(userInput.getPassword()) || userInput.getPassword().isEmpty()){
            user.setPassword("EMPTY");
        }else {
            user.setPassword(userInput.getPassword());
        }
        if(isNull(userInput.getPhone()) || userInput.getPhone().isEmpty()){
            user.setPhone("EMPTY");
        }else {
            user.setPhone(userInput.getPhone());
        }
        if(isNull(userInput.getBalance())){
            user.setBalance(0d);
        }else {
            user.setBalance(userInput.getBalance());
        }
        if(isNull(userInput.getRatings())){
            user.setRatings(0d);
        }else {
            user.setRatings(userInput.getRatings());
        }
        if(isNull(userInput.getIsActive())){
            user.setIsActive(false);
        }else {
            user.setIsActive(userInput.getIsActive());
        }
        if(isNull(userInput.getIsDebtor())){
            user.setIsDebtor(false);
        }else {
            user.setIsDebtor(userInput.getIsDebtor());
        }
        if(isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(0);
        }else {
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }
        if(isNull(userInput.getTotalRatings())){
            user.setTotalRatings(0);
        }else {
            user.setTotalRatings(userInput.getTotalRatings());
        }
        return userDao.editUser(user);
    }
}
