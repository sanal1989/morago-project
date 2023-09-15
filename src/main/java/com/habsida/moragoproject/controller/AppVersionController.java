package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.enums.EPlatform;
import com.habsida.moragoproject.service.AppVersionService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AppVersionController {

    AppVersionService appVersionService;


    public AppVersionController(AppVersionService appVersionService) {
        this.appVersionService = appVersionService;
    }

    @QueryMapping
    public List<AppVersion> findAllAppVersion(){
        return appVersionService.findAll();
    }

    @QueryMapping
    public AppVersion findAppVersionByEPlatform(EPlatform ePlatform){
        return appVersionService.findByEPlatform(ePlatform);
    }

    @MutationMapping
    public AppVersion addAppVersion(AppVersion appVersion){
        return appVersionService.addAppVersion(appVersion);
    }

    @MutationMapping
    public void deleteAppVersion(EPlatform ePlatform){
        appVersionService.deleteAppVersion(ePlatform);
    }

    @MutationMapping
    public AppVersion editAppVersion(AppVersion appVersion){
        return appVersionService.editAppVersion(appVersion);
    }
}
