package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.UserProfileInput;
import com.habsida.moragoproject.repository.UserProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class UserProfileService {

    UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> findAll(){
        return userProfileRepository.findAll();
    }

    public List<UserProfile> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<UserProfile> pages =userProfileRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public UserProfile findById(Long id){
        return userProfileRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("UserProfile -> UserProfile doesn't find by Id " + id));
    }

    public UserProfile createUserProfile(UserProfileInput userProfileInput){
        UserProfile userProfile = new UserProfile();
        if(!isNull(userProfileInput.getIsFreeCallMade())){
            userProfile.setIsFreeCallMade(userProfileInput.getIsFreeCallMade());
        }else{
            userProfile.setIsFreeCallMade(false);
        }
        return userProfileRepository.save(userProfile);
    }

    public String deleteUserProfileById(Long id){
        try{
            userProfileRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "UserProfile with Id "+id+" deleted";
    }

    public UserProfile updateUserProfile(Long id, UserProfileInput userProfileInput){
        UserProfile userProfile = userProfileRepository.findById(id).get();
        if(isNull(userProfileInput.getIsFreeCallMade())){
            userProfile.setIsFreeCallMade(userProfileInput.getIsFreeCallMade());
        }else{
            userProfile.setIsFreeCallMade(false);
        }
        return userProfileRepository.save(userProfile);
    }

    public void save(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }
}
