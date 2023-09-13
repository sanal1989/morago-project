package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguagesRepository extends JpaRepository<Language, Long> {
}
