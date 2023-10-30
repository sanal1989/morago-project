package com.habsida.moragoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.habsida.moragoproject.model.CurrentUser;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import graphql.execution.DataFetcherResult;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private UserService userService;

    public CurrentUserService(UserService userService) {
        this.userService = userService;
    }

    public DataFetcherResult<CurrentUser> getCurrentUser() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        User user = userService.currentUser();

        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(user.getId());
        currentUser.setCreatedAt(user.getCreatedAt());
        currentUser.setUpdatedAt(user.getUpdatedAt());
        currentUser.setApnToken(user.getApnToken());
        currentUser.setPhone(user.getPhone());
        currentUser.setPassword(user.getPassword());
        currentUser.setBalance(user.getBalance());
        currentUser.setFcmToken(user.getFcmToken());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setIsActive(user.getIsActive());
        currentUser.setIsDebtor(user.getIsDebtor());
        currentUser.setLastName(user.getLastName());
        currentUser.setOnBoardingStatus(user.getOnBoardingStatus());
        currentUser.setRatings(user.getRatings());
        currentUser.setRoles(user.getRoles());
        currentUser.setRefreshToken(user.getRefreshToken());
        currentUser.setTotalRatings(user.getTotalRatings());
        try{
            if(user.getUserProfile() instanceof UserProfile){
                currentUser.setProfile(mapper.writeValueAsString(user.getUserProfile()));
                System.out.println(mapper.writeValueAsString(user.getUserProfile()));
            } else currentUser.setProfile(mapper.writeValueAsString(user.getTranslatorProfile()));
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return DataFetcherResult.<CurrentUser>newResult()
                .data(currentUser)
                .build();
    }
}
