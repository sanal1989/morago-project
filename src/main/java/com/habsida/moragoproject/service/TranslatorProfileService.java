package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.LanguageInput;
import com.habsida.moragoproject.model.input.ThemeInput;
import com.habsida.moragoproject.model.input.TranslatorProfileInput;
import com.habsida.moragoproject.repository.LanguagesRepository;
import com.habsida.moragoproject.repository.ThemeRepository;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class TranslatorProfileService {

    TranslatorProfileRepository translatorProfileRepository;
    LanguagesRepository languagesRepository;
    ThemeRepository themeRepository;

    public TranslatorProfileService(TranslatorProfileRepository translatorProfileRepository, LanguagesRepository languagesRepository, ThemeRepository themeRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
        this.languagesRepository = languagesRepository;
        this.themeRepository = themeRepository;
    }

    public List<TranslatorProfile> findAll(){
        return translatorProfileRepository.findAll();
    }

    public TranslatorProfile findById(Long id){
        return translatorProfileRepository.findById(id)
                .orElseThrow(()->new RuntimeException("TranslatorProfile->TranslatorProfile doesn't find by Id"));
    }

    public TranslatorProfile createTranslatorProfile(TranslatorProfileInput translatorProfileInput){
        TranslatorProfile translatorProfile = new TranslatorProfile();
        if(!isNull(translatorProfileInput.getDateOfBirth()) && !translatorProfileInput.getDateOfBirth().isEmpty()){
            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        }else {
            translatorProfile.setDateOfBirth("EMPTY");
        }
        if(!isNull(translatorProfileInput.getEmail()) && !translatorProfileInput.getEmail().isEmpty()){
            translatorProfile.setEmail(translatorProfileInput.getEmail());
        }else {
            translatorProfile.setEmail("EMPTY");
        }
        if(!isNull(translatorProfileInput.getLevelOfKorean()) && !translatorProfileInput.getLevelOfKorean().isEmpty()){
            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        }else {
            translatorProfile.setLevelOfKorean("EMPTY");
        }
        if(!isNull(translatorProfileInput.getIsActive())){
            translatorProfile.setIsActive(translatorProfileInput.getIsActive());
        }else {
            translatorProfile.setIsActive(false);
        }
        if(!isNull(translatorProfileInput.getIsAvailable())){
            translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
        }else {
            translatorProfile.setIsAvailable(false);
        }
        if(!isNull(translatorProfileInput.getIsOnline())){
            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        }else {
            translatorProfile.setIsOnline(false);
        }
        if(!isNull(translatorProfileInput.getLanguageList())){
            List<LanguageInput> languageList = translatorProfileInput.getLanguageList();
            for (int i = 0; i < languageList.size(); i++) {
                Optional<Language> language = languagesRepository.findByName(languageList.get(i).getName());
                if(language.isPresent()) translatorProfile.getLanguageList().add(language.get());
            }
        }
        if(!isNull(translatorProfileInput.getThemeList())){
            List<ThemeInput> themeList = translatorProfileInput.getThemeList();
            for (int i = 0; i < themeList.size(); i++) {
                Optional<Theme> theme = themeRepository.findByName(themeList.get(i).getName());
                if(theme.isPresent()) translatorProfile.getThemeList().add(theme.get());
            }
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    public void deleteTranslatorProfileById(Long id){
        translatorProfileRepository.deleteById(id);
    }

    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput){
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id).get();
        if(!isNull(translatorProfileInput.getDateOfBirth()) && !translatorProfileInput.getDateOfBirth().isEmpty()){
            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        }else {
            translatorProfile.setDateOfBirth("EMPTY");
        }
        if(!isNull(translatorProfileInput.getEmail()) && !translatorProfileInput.getEmail().isEmpty()){
            translatorProfile.setEmail(translatorProfileInput.getEmail());
        }else {
            translatorProfile.setEmail("EMPTY");
        }
        if(!isNull(translatorProfileInput.getLevelOfKorean()) && !translatorProfileInput.getLevelOfKorean().isEmpty()){
            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        }else {
            translatorProfile.setLevelOfKorean("EMPTY");
        }
        if(!isNull(translatorProfileInput.getIsActive())){
            translatorProfile.setIsActive(translatorProfileInput.getIsActive());
        }else {
            translatorProfile.setIsActive(false);
        }
        if(!isNull(translatorProfileInput.getIsAvailable())){
            translatorProfile.setIsAvailable(translatorProfileInput.getIsAvailable());
        }else {
            translatorProfile.setIsAvailable(false);
        }
        if(!isNull(translatorProfileInput.getIsOnline())){
            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        }else {
            translatorProfile.setIsOnline(false);
        }
        return translatorProfileRepository.save(translatorProfile);
    }
}
