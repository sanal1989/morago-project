package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dao.UserProfileDao;
import com.habsida.moragoproject.entity.UserProfile;
import com.habsida.moragoproject.service.UserProfileService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserProfileController {

    UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @QueryMapping
    public List<UserProfile> findAllUserProfile(){
        return userProfileService.findAll();
    }

    @QueryMapping
    public UserProfile findUserProfileById(@Argument Long id){
        return userProfileService.findById(id);
    }

    @MutationMapping
    public UserProfile addUserProfile(@Argument Boolean isFreeCallMade){
        return userProfileService.addUserProfile(new UserProfile(isFreeCallMade));
    }

    @MutationMapping
    public void deleteUserProfile(@Argument Long id){
        userProfileService.deleteUserProfile(id);
    }

    @MutationMapping
    public UserProfile editUserProfile(@Argument Long id, @Argument Boolean isFreeCallMade){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        userProfile.setFreeCallMade(isFreeCallMade);
        return userProfileService.editUserProfile(userProfile);
    }
}
