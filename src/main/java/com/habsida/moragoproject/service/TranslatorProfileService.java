package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.TranslatorProfileInput;
import com.habsida.moragoproject.repository.FileRepository;
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
    LanguageService languageService;
    ThemeService themeService;
    FileService fileService;

    public TranslatorProfileService(TranslatorProfileRepository translatorProfileRepository,
                                    LanguageService languageService,
                                    ThemeService themeService,
                                    FileService fileService) {
        this.translatorProfileRepository = translatorProfileRepository;
        this.languageService = languageService;
        this.themeService = themeService;
        this.fileService = fileService;
    }

    public List<TranslatorProfile> findAll(){
        return translatorProfileRepository.findAll();
    }

    public TranslatorProfile findById(Long id){
        return translatorProfileRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("TranslatorProfile->TranslatorProfile doesn't find by Id " + id));
    }

    public TranslatorProfile createTranslatorProfile(TranslatorProfileInput translatorProfileInput){
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setIsAvailable(false);
        if(!isNull(translatorProfileInput.getDateOfBirth()) && !translatorProfileInput.getDateOfBirth().isEmpty()){
            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        }
        if(!isNull(translatorProfileInput.getEmail()) && !translatorProfileInput.getEmail().isEmpty()){
            translatorProfile.setEmail(translatorProfileInput.getEmail());
        }
        if(!isNull(translatorProfileInput.getLevelOfKorean()) && !translatorProfileInput.getLevelOfKorean().isEmpty()){
            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        }
        if(!isNull(translatorProfileInput.getIsActive())){
            translatorProfile.setIsActive(translatorProfileInput.getIsActive());
        }else {
            translatorProfile.setIsActive(false);
        }


        if(!isNull(translatorProfileInput.getIsOnline())){
            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        }else {
            translatorProfile.setIsOnline(false);
        }
        if(!isNull(translatorProfileInput.getLanguageList())){
            List<String> languageList = translatorProfileInput.getLanguageList();
            for (int i = 0; i < languageList.size(); i++) {
                Optional<Language> language = languageService.findByName(languageList.get(i));
                if(language.isPresent()) translatorProfile.getLanguageList().add(language.get());
            }
        }
        if(!isNull(translatorProfileInput.getFile())){
            translatorProfile.setFile(fileService.findById(translatorProfileInput.getFile()));
        }
        if(!isNull(translatorProfileInput.getThemeList())){
            List<String> themeList = translatorProfileInput.getThemeList();
            for (int i = 0; i < themeList.size(); i++) {
                Optional<Theme> theme = themeService.findByName(themeList.get(i));
                if(theme.isPresent()) translatorProfile.getThemeList().add(theme.get());
            }
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    public String deleteTranslatorProfileById(Long id){
        try{
            translatorProfileRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "TranslatorProfile with Id "+id+" deleted";

    }

    public TranslatorProfile updateTranslatorProfile(Long id, TranslatorProfileInput translatorProfileInput){
        TranslatorProfile translatorProfile = translatorProfileRepository.findById(id).get();
        if(!isNull(translatorProfileInput.getDateOfBirth()) && !translatorProfileInput.getDateOfBirth().isEmpty()){
            translatorProfile.setDateOfBirth(translatorProfileInput.getDateOfBirth());
        }
        if(!isNull(translatorProfileInput.getEmail()) && !translatorProfileInput.getEmail().isEmpty()){
            translatorProfile.setEmail(translatorProfileInput.getEmail());
        }
        if(!isNull(translatorProfileInput.getLevelOfKorean()) && !translatorProfileInput.getLevelOfKorean().isEmpty()){
            translatorProfile.setLevelOfKorean(translatorProfileInput.getLevelOfKorean());
        }
        if(!isNull(translatorProfileInput.getIsActive())){
            translatorProfile.setIsActive(translatorProfileInput.getIsActive());
        }else {
            translatorProfile.setIsActive(false);
        }
        if(!isNull(translatorProfileInput.getIsOnline())){
            translatorProfile.setIsOnline(translatorProfileInput.getIsOnline());
        }else {
            translatorProfile.setIsOnline(false);
        }
        return translatorProfileRepository.save(translatorProfile);
    }
}
