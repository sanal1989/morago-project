package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.UserProfileDao;
import com.habsida.moragoproject.entity.Role;
import com.habsida.moragoproject.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserProfile addUserProfile(UserProfile userProfile){
        return userProfileDao.addUserProfile(userProfile);
    }

    public void deleteUserProfile(Long id){
        userProfileDao.deleteUserProfile(id);
    }

    public UserProfile editUserProfile(UserProfile userProfile){
        return userProfileDao.editUserProfile(userProfile);
    }
}
