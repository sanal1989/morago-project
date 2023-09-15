package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.enums.EPlatform;
import com.habsida.moragoproject.repository.AppVersionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class AppVersionDao {

    AppVersionRepository appVersionRepository;

    public AppVersionDao(AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }

    public List<AppVersion> findAll(){
        return appVersionRepository.findAll();
    }

    public AppVersion findByEPlatform(EPlatform ePlatform){
        return appVersionRepository.findByPlatform(ePlatform);
    }

    public AppVersion addAppVersion(AppVersion appVersion){
        return appVersionRepository.save(appVersion);
    }

    public void deleteAppVersion(EPlatform ePlatform){
        appVersionRepository.deleteByPlatform(ePlatform);
    }

    public AppVersion editAppVersion(AppVersion appVersion){
        AppVersion AppVersionFromDB = appVersionRepository.findByPlatform(appVersion.getPlatform());
        if(!appVersion.getLatest().isEmpty() && !isNull(appVersion.getLatest())){
            AppVersionFromDB.setLatest(appVersion.getLatest());
        }
        if(!appVersion.getMin().isEmpty() || !isNull(appVersion.getMin())){
            AppVersionFromDB.setMin(appVersion.getMin());
        }
        return appVersionRepository.save(AppVersionFromDB);
    }
}