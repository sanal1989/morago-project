package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.ThemeRepository;
import org.springframework.stereotype.Component;

@Component
public class ThemeDao {

    ThemeRepository themeRepository;

    public ThemeDao(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }
}
