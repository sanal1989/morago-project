package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.AppVersionDao;
import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.enums.EPlatform;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AppVersionService {

    AppVersionDao appVersionDao;

    public AppVersionService(AppVersionDao appVersionDao) {
        this.appVersionDao = appVersionDao;
    }

    public List<AppVersion> findAll(){
        return appVersionDao.findAll();
    }

    public AppVersion findByEPlatform(EPlatform ePlatform){
        return appVersionDao.findByEPlatform(ePlatform);
    }

    public AppVersion addAppVersion(AppVersion appVersion){
        return appVersionDao.addAppVersion(appVersion);
    }

    public void deleteAppVersion(EPlatform ePlatform){
        appVersionDao.deleteAppVersion(ePlatform);
    }

    public AppVersion editAppVersion(AppVersion appVersion){
        return appVersionDao.editAppVersion(appVersion);
    }
}
