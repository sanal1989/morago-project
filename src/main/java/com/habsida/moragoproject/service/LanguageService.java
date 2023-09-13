package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.LanguageDao;
import com.habsida.moragoproject.entity.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    LanguageDao languageDao;

    public LanguageService(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    public List<Language> findAll(){
        return languageDao.findAll();
    }

    public Language findById(Long id){
        return languageDao.findById(id);
    }

    public Language addLanguage(Language language){
        return languageDao.addLanguage(language);
    }

    public void deleteLanguage(Long id){
        languageDao.deleteLanguage(id);
    }

    public Language editLanguage(Language language){
        return languageDao.editLanguage(language);
    }
}
