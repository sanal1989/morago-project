package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.AppVersionRepository;
import org.springframework.stereotype.Component;

@Component
public class AppVersionDao {

    AppVersionRepository appVersionRepository;

    public AppVersionDao(AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }
}
