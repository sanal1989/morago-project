package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.UserProfileInput;
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
    public UserProfile addUserProfile(@Argument UserProfileInput userProfileInput){
        return userProfileService.addUserProfile(userProfileInput);
    }

    @MutationMapping
    public void deleteUserProfile(@Argument Long id){
        userProfileService.deleteUserProfile(id);
    }

    @MutationMapping
    public UserProfile editUserProfile(@Argument Long id, @Argument UserProfileInput userProfileInput){
        return userProfileService.editUserProfile(id, userProfileInput);
    }
}
