package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.LanguagesRepository;
import org.springframework.stereotype.Component;

@Component
public class LanguagesDao {
    LanguagesRepository languagesRepository;

    public LanguagesDao(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }
}
