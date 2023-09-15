package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.enums.EPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {

    AppVersion findByPlatform(EPlatform platform);

    void deleteByPlatform(EPlatform platform);
}
