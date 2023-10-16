package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.TranslatorProfileInput;
import com.habsida.moragoproject.service.TranslatorProfileService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class TranslatorProfileController {

    TranslatorProfileService translatorProfileService;

    public TranslatorProfileController(TranslatorProfileService translatorProfileService) {
        this.translatorProfileService = translatorProfileService;
    }

    @QueryMapping
    public List<TranslatorProfile> findAllTranslatorProfile(){
        return translatorProfileService.findAll();
    }

    @QueryMapping
    public TranslatorProfile findTranslatorProfileById(@Argument Long id){
        return translatorProfileService.findById(id);
    }

    @MutationMapping
    public TranslatorProfile createTranslatorProfile(@Argument TranslatorProfileInput translatorProfileInput){
        return translatorProfileService.createTranslatorProfile(translatorProfileInput);
    }

    @MutationMapping
    public String deleteTranslatorProfileById(@Argument Long id){
        return translatorProfileService.deleteTranslatorProfileById(id);
    }

    @MutationMapping
    public TranslatorProfile updateTranslatorProfile(@Argument Long id, @Argument TranslatorProfileInput translatorProfileInput){
        return translatorProfileService.updateTranslatorProfile(id, translatorProfileInput);
    }
}