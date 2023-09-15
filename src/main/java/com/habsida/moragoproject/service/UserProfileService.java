package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.UserProfileDao;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.UserProfileInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class UserProfileService {

    UserProfileDao userProfileDao;

    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    public List<UserProfile> findAll(){
        return userProfileDao.findAll();
    }

    public UserProfile findById(Long id){
        return userProfileDao.findById(id);
    }

    public UserProfile addUserProfile(UserProfileInput userProfileInput){
        UserProfile userProfile = new UserProfile();
        if(isNull(userProfileInput.getIsFreeCallMade())){
            userProfile.setIsFreeCallMade(false);
        }else{
            userProfile.setIsFreeCallMade(userProfileInput.getIsFreeCallMade());
        }
        return userProfileDao.addUserProfile(userProfile);
    }

    public void deleteUserProfile(Long id){
        userProfileDao.deleteUserProfile(id);
    }

    public UserProfile editUserProfile(Long id, UserProfileInput userProfileInput){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        if(isNull(userProfileInput.getIsFreeCallMade())){
            userProfile.setIsFreeCallMade(false);
        }else{
            userProfile.setIsFreeCallMade(userProfileInput.getIsFreeCallMade());
        }
        return userProfileDao.editUserProfile(userProfile);
    }
}
