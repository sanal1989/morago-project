package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.Profile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.input.RefreshTokenResponse;
import com.habsida.moragoproject.model.input.UserInput;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> findAllUser(){
        return userService.findAll();
    }

    @QueryMapping
    public List<User> findAllUserPagination(@Argument int offset, @Argument int limit){
        return userService.findAll(offset, limit);
    }

    @QueryMapping
    public User findUserById(@Argument Long id){
        return userService.findById(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@Argument Long id){
        return userService.deleteUserById(id);
    }

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public RefreshTokenResponse registrationUser(@Argument UserInput userInput){
        return userService.createUser(userInput);
    }

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public RefreshTokenResponse registrationAdmin(@Argument UserInput userInput){
        return userService.createAdmin(userInput);
    }

    @MutationMapping
    public User updateUserName(@Argument String firstName, @Argument String lastName ){
        return userService.updateUserName(firstName, lastName);
    }

    @MutationMapping
    public boolean deleteCurrentUser() {
        userService.deleteCurrentUser();
        return true;
    }

    @QueryMapping
    public User currentUser() {
        return userService.currentUser();
    }

    @SchemaMapping(typeName = "User", field = "profile")
    public Profile getProfile(User user) {
        return userService.getProfile(user);
    }

    @MutationMapping
    public void resetNewPassword(@Argument String token,
                                 @Argument String password){
        userService.resetNewPassword(token, password);
    }

    @MutationMapping
    public Boolean updatePhone(@Argument String phone,
                                 @Argument String password){
        return userService.updatePhone(phone, password);
    }

    @MutationMapping
    public Boolean addApnToken(@Argument String apnToken){
        return userService.addApnToken(apnToken);
    }

    @MutationMapping
    public Boolean deleteApnToken(){
        return userService.deleteApnToken();
    }

    @MutationMapping
    public Boolean addFcmToken(@Argument String fcmToken){
        return userService.addFcmToken(fcmToken);
    }

    @MutationMapping
    public Boolean deleteFcmToken(){
        return userService.deleteFcmToken();
    }
}

