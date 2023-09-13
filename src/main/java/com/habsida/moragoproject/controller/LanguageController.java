package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.service.LanguageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LanguageController {

    LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @QueryMapping
    public List<Language> findAllLanguage(){
        return languageService.findAll();
    }

    @QueryMapping
    public Language findLanguageById(@Argument Long id){
        return languageService.findById(id);
    }

    @MutationMapping
    public Language addLanguage(@Argument String name){
        Language language = new Language();
        language.setName(name);
        return languageService.addLanguage(language);
    }

    @MutationMapping
    public void deleteLanguage(@Argument Long id){
        languageService.deleteLanguage(id);
    }

    @MutationMapping
    public Language editLanguage(@Argument Long id, @Argument String name){
        Language language = new Language();
        language.setId(id);
        language.setName(name);
        return languageService.editLanguage(language);
    }
}
