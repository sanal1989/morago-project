package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.UserProfileRepository;
import com.habsida.moragoproject.model.entity.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileDao {

    UserProfileRepository userProfileRepository;

    public UserProfileDao(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> findAll(){
        return userProfileRepository.findAll();
    }

    public UserProfile findById(Long id){
        return userProfileRepository.findById(id).get();
    }

    public UserProfile addUserProfile(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Long id){
        userProfileRepository.deleteById(id);
    }

    public UserProfile editUserProfile(UserProfile userProfile){
        UserProfile userProfileFromDB = userProfileRepository.findById(userProfile.getId()).get();
        if(userProfile.getIsFreeCallMade() != userProfileFromDB.getIsFreeCallMade()){
            userProfileFromDB.setIsFreeCallMade(userProfile.getIsFreeCallMade());
        }
        return userProfileRepository.save(userProfileFromDB);
    }
}
