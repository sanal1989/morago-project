package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.LanguagesRepository;
import com.habsida.moragoproject.model.entity.Language;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageDao {

    LanguagesRepository languagesRepository;

    public LanguageDao(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    public List<Language> findAll(){
        return languagesRepository.findAll();
    }

    public Language findById(Long id){
        return languagesRepository.findById(id).get();
    }

    public Language addLanguage(Language language){
        return languagesRepository.save(language);
    }

    public void deleteLanguage(Long id){
        languagesRepository.deleteById(id);
    }

    public Language editLanguage(Language language){
        Language languageFromDB = languagesRepository.findById(language.getId()).get();
        if(!language.getName().equals("EMPTY")){
            languageFromDB.setName(language.getName());
        }
        return languagesRepository.save(languageFromDB);
    }
}
