package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
}
