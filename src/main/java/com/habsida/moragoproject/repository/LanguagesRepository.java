package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguagesRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByName(String name);
}
