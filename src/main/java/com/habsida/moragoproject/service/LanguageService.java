package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.LanguageDao;
import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.LanguageInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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

    public Language addLanguage(LanguageInput languageInput){
        Language language = new Language();
        if(isNull(languageInput.getName()) || languageInput.getName().isEmpty()){
            language.setName("EMPTY");
        }else {
            language.setName(languageInput.getName());
        }
        return languageDao.addLanguage(language);
    }

    public void deleteLanguage(Long id){
        languageDao.deleteLanguage(id);
    }

    public Language editLanguage(Long id, LanguageInput languageInput){
        Language language = new Language();
        language.setId(id);
        if(isNull(languageInput.getName()) || languageInput.getName().isEmpty()){
            language.setName("EMPTY");
        }else {
            language.setName(languageInput.getName());
        }
        return languageDao.editLanguage(language);
    }
}
