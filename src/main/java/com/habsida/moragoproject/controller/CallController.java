package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.enums.CallStatus;
import com.habsida.moragoproject.service.CallService;
import com.habsida.moragoproject.service.ThemeService;
import com.habsida.moragoproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
public class CallController {

    CallService callService;
    UserService userService;
    ThemeService themeService;

    public CallController(CallService callService, UserService userService, ThemeService themeService) {
        this.callService = callService;
        this.userService = userService;
        this.themeService = themeService;
    }

    @QueryMapping
    public List<Call> findAllCall(){
        return callService.findAll();
    }

    @QueryMapping
    public Call findCallById(@Argument Long id){
        return callService.findById(id);
    }

    @MutationMapping
    public Call addCall(@Argument String callStatus, @Argument String channelName, @Argument Double commission,
                        @Argument Integer duration, @Argument Boolean isEndCall, @Argument Boolean status,
                        @Argument Double sum, @Argument Boolean translatorHasRated, @Argument Boolean userHasRated,
                        @Argument Long userCall, @Argument Long userAnswer, @Argument Long theme,
                        @Argument Long user){
        Call call = new Call();
        call.setCallStatus(CallStatus.valueOf(callStatus));
        call.setChannelName(channelName);
        call.setCommission(commission);
        call.setDuration(duration);
        call.setIsEndCall(isEndCall);
        call.setStatus(status);
        call.setSum(sum);
        call.setTranslatorHasRated(translatorHasRated);
        call.setUserHasRated(userHasRated);
        call.setCaller(userService.findById(userCall));
        call.setAnswerer(userService.findById(userAnswer));
        call.setTheme(themeService.findById(theme));
        return callService.addCall(call);
    }

    @MutationMapping
    public void deleteCall(@Argument Long id){
        callService.deleteCall(id);
    }

    @MutationMapping
    public Call editCall(@Argument Long id, @Argument String callStatus, @Argument String channelName, @Argument Double commission,
                         @Argument Integer duration, @Argument Boolean isEndCall, @Argument Boolean status,
                         @Argument Double sum, @Argument Boolean translatorHasRated, @Argument Boolean userHasRated,
                         @Argument Long userCall, @Argument Long userAnswer, @Argument Long theme,
                         @Argument Long user){
        Call call = new Call();
        call.setId(id);
        call.setCallStatus(CallStatus.valueOf(callStatus));
        call.setChannelName(channelName);
        call.setCommission(commission);
        call.setDuration(duration);
        call.setIsEndCall(isEndCall);
        call.setStatus(status);
        call.setSum(sum);
        call.setTranslatorHasRated(translatorHasRated);
        call.setUserHasRated(userHasRated);
        call.setCaller(userService.findById(userCall));
        call.setAnswerer(userService.findById(userAnswer));
        if(isNull(theme))call.setTheme(themeService.findById(theme));

        return callService.editCall(call);
    }
}
