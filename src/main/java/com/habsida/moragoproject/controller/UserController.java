package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.TranslatorProfile;
import com.habsida.moragoproject.entity.User;
import com.habsida.moragoproject.entity.UserProfile;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
    public User findUserById(@Argument Long id){
        return userService.findById(id);
    }

    @MutationMapping
    public void deleteUser(@Argument Long id){
        userService.deleteUser(id);
    }

    @MutationMapping
    public User addUser(@Argument String firstName, @Argument String lastName, @Argument String apnToken,
                        @Argument String fcmToken, @Argument String password, @Argument String phone,
                        @Argument Double balance, @Argument Double ratings, @Argument Boolean isActive,
                        @Argument Boolean isDebtor, @Argument Integer onBoardingStatus, @Argument Integer totalRatings){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setApnToken(apnToken);
        user.setFcmToken(fcmToken);
        user.setPassword(password);
        user.setPhone(phone);
        user.setBalance(balance);
        user.setRatings(ratings);
        user.setActive(isActive);
        user.setDebtor(isDebtor);
        user.setOnBoardingStatus(onBoardingStatus);
        user.setTotalRatings(totalRatings);
        return userService.addUser(user);
    }

    @MutationMapping
    public User editUser(@Argument Long id,@Argument String firstName, @Argument String lastName, @Argument String apnToken,
                        @Argument String fcmToken, @Argument String password, @Argument String phone,
                        @Argument Double balance, @Argument Double ratings, @Argument Boolean isActive,
                        @Argument Boolean isDebtor, @Argument Integer onBoardingStatus, @Argument Integer totalRatings){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setApnToken(apnToken);
        user.setFcmToken(fcmToken);
        user.setPassword(password);
        user.setPhone(phone);
        user.setBalance(balance);
        user.setRatings(ratings);
        user.setActive(isActive);
        user.setDebtor(isDebtor);
        user.setOnBoardingStatus(onBoardingStatus);
        user.setTotalRatings(totalRatings);
        return userService.editUser(user);
    }


}
