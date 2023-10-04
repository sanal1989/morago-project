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
//@PreAuthorize("isAuthenticated()")
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
    public UserProfile createUserProfile(@Argument UserProfileInput userProfileInput){
        return userProfileService.createUserProfile(userProfileInput);
    }

    @MutationMapping
    public String deleteUserProfileById(@Argument Long id){
        return userProfileService.deleteUserProfileById(id);
    }

    @MutationMapping
    public UserProfile updateUserProfile(@Argument Long id, @Argument UserProfileInput userProfileInput){
        return userProfileService.updateUserProfile(id, userProfileInput);
    }
}
