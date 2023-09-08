package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.TranslatorProfileRepository;

public class TranslatorProfileDao {

    TranslatorProfileRepository translatorProfileRepository;

    public TranslatorProfileDao(TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }
}
