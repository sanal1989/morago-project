package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.enums.EPlatform;
import com.habsida.moragoproject.model.input.AppVersionInput;
import com.habsida.moragoproject.service.AppVersionService;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public List<AppVersion> findAllAppVersionPagination(@Argument int offset, @Argument int limit){
        return appVersionService.findAll(offset, limit);
    }
    @QueryMapping
    public AppVersion findAppVersionByEPlatform(EPlatform ePlatform){
        return appVersionService.findByEPlatform(ePlatform);
    }

    @MutationMapping
    public AppVersion createAppVersion(@Argument AppVersionInput appVersionInput){
        return appVersionService.createAppVersion(appVersionInput);
    }

    @MutationMapping
    public String deleteAppVersionById(EPlatform ePlatform){
        return appVersionService.deleteAppVersionById(ePlatform);
    }

    @MutationMapping
    public AppVersion updateAppVersion(AppVersionInput appVersionInput){
        return appVersionService.updateAppVersion(appVersionInput);
    }
}
