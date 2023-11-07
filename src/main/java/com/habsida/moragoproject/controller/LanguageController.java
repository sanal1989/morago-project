package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.LanguageInput;
import com.habsida.moragoproject.service.LanguageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public List<Language> findAllLanguagePagination(@Argument int offset, @Argument int limit){
        return languageService.findAll(offset, limit);
    }

    @QueryMapping
    public Language findLanguageById(@Argument Long id){
        return languageService.findById(id);
    }

    @MutationMapping
    public Language createLanguage(@Argument LanguageInput languageInput){
        return languageService.createLanguage(languageInput);
    }

    @MutationMapping
    public String deleteLanguageById(@Argument Long id){
        return languageService.deleteLanguageById(id);
    }

    @MutationMapping
    public Language updateLanguage(@Argument Long id, @Argument LanguageInput languageInput){
        return languageService.updateLanguage(id, languageInput);
    }
}
