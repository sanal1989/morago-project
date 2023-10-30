package com.habsida.moragoproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.habsida.moragoproject.model.CurrentUser;
import com.habsida.moragoproject.model.Profile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.service.CurrentUserService;
import com.habsida.moragoproject.service.UserService;
import graphql.execution.DataFetcherResult;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

import static graphql.execution.DataFetcherResult.newResult;

@Controller
public class CurrentUserController {
    UserService userService;
    private CurrentUserService currentUserService;

    public CurrentUserController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @QueryMapping
    public DataFetcherResult<CurrentUser> currentUser() throws JsonProcessingException {
        return currentUserService.getCurrentUser();
    }


}
