package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
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
                .orElseThrow(()->new NotFoundByIdException("Language -> Language doesn't find by Id " + id));
    }

    public Language createLanguage(LanguageInput languageInput){
        Language language = new Language();
        if(!isNull(languageInput.getName()) && !languageInput.getName().isEmpty()){
            language.setName(languageInput.getName());
        }
        return languagesRepository.save(language);
    }

    public String deleteLanguageById(Long id){
        try{
            languagesRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Language with Id "+id+" deleted";
    }

    public Language updateLanguage(Long id, LanguageInput languageInput){
        Language language = languagesRepository.findById(id).get();
        if(!isNull(languageInput.getName()) && !languageInput.getName().isEmpty()){
            language.setName(languageInput.getName());
        }
        return languagesRepository.save(language);
    }
}
