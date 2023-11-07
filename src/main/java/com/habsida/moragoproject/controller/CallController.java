package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CallInput;
import com.habsida.moragoproject.service.CallService;
import com.habsida.moragoproject.service.ThemeService;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class CallController {

    CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @QueryMapping
    public List<Call> findAllCall(){
        return callService.findAll();
    }

    @QueryMapping
    public List<Call> findAllCallPagination(@Argument int offset, @Argument int limit){
        return callService.findAll(offset, limit);
    }

    @QueryMapping
    public Call findCallById(@Argument Long id){
        return callService.findById(id);
    }

    @MutationMapping
    public Call createCall(@Argument CallInput callInput){
        return callService.createCall(callInput);
    }

    @MutationMapping
    public String deleteCallById(@Argument Long id){
        return callService.deleteCallById(id);
    }

    @MutationMapping
    public Call updateCall(@Argument Long id, @Argument CallInput callInput){
        return callService.updateCall(id, callInput);
    }
}
