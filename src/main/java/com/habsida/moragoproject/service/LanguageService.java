package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.LanguageInput;
import com.habsida.moragoproject.repository.LanguagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class LanguageService {

    LanguagesRepository languagesRepository;

    public LanguageService(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    public List<Language> findAll(){
        return languagesRepository.findAll();
    }

    public Language findById(Long id){
        return languagesRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Language -> Language doesn't find by Id"));
    }

    public Language createLanguage(LanguageInput languageInput){
        Language language = new Language();
        if(!isNull(languageInput.getName()) && !languageInput.getName().isEmpty()){
            language.setName(languageInput.getName());
        }else {
            language.setName("EMPTY");
        }
        return languagesRepository.save(language);
    }

    public void deleteLanguageById(Long id){
        languagesRepository.deleteById(id);
    }

    public Language updateLanguage(Long id, LanguageInput languageInput){
        Language language = languagesRepository.findById(id).get();
        if(!isNull(languageInput.getName()) && !languageInput.getName().isEmpty()){
            language.setName(languageInput.getName());
        }else {
            language.setName("EMPTY");
        }
        return languagesRepository.save(language);
    }
}
