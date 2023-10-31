package com.habsida.moragoproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.habsida.moragoproject.model.CurrentUser;
import com.habsida.moragoproject.service.CurrentUserService;
import com.habsida.moragoproject.service.UserService;
import graphql.execution.DataFetcherResult;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

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
