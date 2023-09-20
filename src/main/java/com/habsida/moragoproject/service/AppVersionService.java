package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.enums.EPlatform;
import com.habsida.moragoproject.model.input.AppVersionInput;
import com.habsida.moragoproject.repository.AppVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AppVersionService {

    AppVersionRepository appVersionRepository;

    public AppVersionService(AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }

    public List<AppVersion> findAll(){
        return appVersionRepository.findAll();
    }

    public AppVersion findByEPlatform(EPlatform ePlatform){
        return appVersionRepository.findByPlatform(ePlatform)
                .orElseThrow(()->new RuntimeException("AppVersion -> AppVersion doesn't find by Id"));
    }

    public AppVersion createAppVersion(AppVersionInput appVersionInput){
        AppVersion appVersion = new AppVersion();
        if(!isNull(appVersionInput.getPlatform()) && !appVersionInput.getPlatform().isEmpty()){
            appVersion.setPlatform(EPlatform.valueOf(appVersionInput.getPlatform()));
        }
        if(!isNull(appVersionInput.getPlatform()) && !appVersionInput.getPlatform().isEmpty()){
            appVersion.setPlatform(EPlatform.valueOf(appVersionInput.getPlatform()));
        }
        if(!isNull(appVersionInput.getLatest()) && !appVersionInput.getLatest().isEmpty()){
            appVersion.setLatest(appVersionInput.getLatest());
        }else {
            appVersion.setLatest("EMPTY");
        }
        if(!isNull(appVersionInput.getMin()) && !appVersionInput.getMin().isEmpty()){
            appVersion.setMin(appVersionInput.getMin());
        }else {
            appVersion.setMin("EMPTY");
        }
        return appVersionRepository.save(appVersion);
    }

    public void deleteAppVersionById(EPlatform ePlatform){
        appVersionRepository.deleteByPlatform(ePlatform);
    }

    public AppVersion updateAppVersion(AppVersionInput appVersionInput){
        AppVersion appVersion = appVersionRepository.findByPlatform(EPlatform.valueOf(appVersionInput.getPlatform())).get();
        if(!isNull(appVersionInput.getPlatform()) && !appVersionInput.getPlatform().isEmpty()){
            appVersion.setPlatform(EPlatform.valueOf(appVersionInput.getPlatform()));
        }
        if(!isNull(appVersionInput.getPlatform()) && !appVersionInput.getPlatform().isEmpty()){
            appVersion.setPlatform(EPlatform.valueOf(appVersionInput.getPlatform()));
        }
        if(!isNull(appVersionInput.getLatest()) && !appVersionInput.getLatest().isEmpty()){
            appVersion.setLatest(appVersionInput.getLatest());
        }else {
            appVersion.setLatest("EMPTY");
        }
        if(!isNull(appVersionInput.getMin()) && !appVersionInput.getMin().isEmpty()){
            appVersion.setMin(appVersionInput.getMin());
        }else {
            appVersion.setMin("EMPTY");
        }
        return appVersionRepository.save(appVersion);
    }
}
