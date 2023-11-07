package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.enums.EPlatform;
import com.habsida.moragoproject.model.input.AppVersionInput;
import com.habsida.moragoproject.repository.AppVersionRepository;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<AppVersion> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<AppVersion> pages = appVersionRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public AppVersion findByEPlatform(EPlatform ePlatform){
        return appVersionRepository.findByPlatform(ePlatform)
                .orElseThrow(()->new NotFoundByIdException("AppVersion -> AppVersion doesn't find by Id " +ePlatform.toString()));
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
        }
        if(!isNull(appVersionInput.getMin()) && !appVersionInput.getMin().isEmpty()){
            appVersion.setMin(appVersionInput.getMin());
        }
        return appVersionRepository.save(appVersion);
    }

    public String deleteAppVersionById(EPlatform ePlatform){
        try{
            appVersionRepository.deleteByPlatform(ePlatform);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "AppVersion with Id "+ePlatform.toString()+" deleted";
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
        }
        if(!isNull(appVersionInput.getMin()) && !appVersionInput.getMin().isEmpty()){
            appVersion.setMin(appVersionInput.getMin());
        }
        return appVersionRepository.save(appVersion);
    }
}
