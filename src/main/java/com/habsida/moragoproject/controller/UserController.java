package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.UserInput;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
//    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<User> findAllUser(){
        return userService.findAll();
    }

    @QueryMapping
    public User findUserById(@Argument Long id){
        return userService.findById(id);
    }

    @MutationMapping
    public void deleteUserById(@Argument Long id){
        userService.deleteUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument UserInput userInput){
        return userService.createUser(userInput);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput userInput ){
        return userService.updateUser(id, userInput);
    }


}
