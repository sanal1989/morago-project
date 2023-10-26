package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.CurrentUser;
import com.habsida.moragoproject.model.Profile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class CurrentUserController {
    UserService userService;

    public CurrentUserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public Profile currentProfiles(){
        User user = userService.currentUser();
        return user.getUserProfile() != null ? user.getUserProfile(): user.getTranslatorProfile();
    }

    @QueryMapping
    public CurrentUser currentUser(){
        CurrentUser currentUser = new CurrentUser();
        User user = userService.currentUser();
        currentUser.setPhone(user.getPhone());
        currentUser.setPassword(user.getPassword());
        if(user.getUserProfile() instanceof UserProfile) currentUser.setProfile(user.getUserProfile());
        else currentUser.setProfile(user.getTranslatorProfile());
        return currentUser;
    }
}
