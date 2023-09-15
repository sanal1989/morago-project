package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.service.TranslatorProfileService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
    public TranslatorProfile addTranslatorProfile(@Argument String dateOfBirth, @Argument String email, @Argument Boolean isAvailable,
                                                    @Argument Boolean isOnline, @Argument String levelOfKorean, @Argument Boolean isActive){
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setDateOfBirth(dateOfBirth);
        translatorProfile.setEmail(email);
        translatorProfile.setIsAvailable(isAvailable);
        translatorProfile.setIsOnline(isOnline);
        translatorProfile.setLevelOfKorean(levelOfKorean);
        translatorProfile.setIsActive(isActive);
        return translatorProfileService.addTranslatorProfile(translatorProfile);
    }

    @MutationMapping
    public void deleteTranslatorProfile(@Argument Long id){
        translatorProfileService.deleteTranslatorProfile(id);
    }

    @MutationMapping
    public TranslatorProfile editTranslatorProfile(@Argument Long id, @Argument String dateOfBirth, @Argument String email, @Argument Boolean isAvailable,
                                                   @Argument Boolean isOnline, @Argument String levelOfKorean, @Argument Boolean isActive){
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setId(id);
        translatorProfile.setDateOfBirth(dateOfBirth);
        translatorProfile.setEmail(email);
        translatorProfile.setIsAvailable(isAvailable);
        translatorProfile.setIsOnline(isOnline);
        translatorProfile.setLevelOfKorean(levelOfKorean);
        translatorProfile.setIsActive(isActive);
        return translatorProfileService.editTranslatorProfile(translatorProfile);
    }
}