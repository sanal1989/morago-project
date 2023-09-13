package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.TranslatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorProfileRepository extends JpaRepository<TranslatorProfile, Long> {
}
