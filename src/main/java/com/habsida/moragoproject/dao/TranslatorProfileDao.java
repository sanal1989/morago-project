package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.TranslatorProfileRepository;
import com.habsida.moragoproject.entity.Role;
import com.habsida.moragoproject.entity.TranslatorProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TranslatorProfileDao {

    TranslatorProfileRepository translatorProfileRepository;

    public TranslatorProfileDao(TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }

    public List<TranslatorProfile> findAll(){
        return translatorProfileRepository.findAll();
    }

    public TranslatorProfile findById(Long id){
        return translatorProfileRepository.findById(id).get();
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfile translatorProfile){
        return translatorProfileRepository.save(translatorProfile);
    }

    public void deleteTranslatorProfile(Long id){
        translatorProfileRepository.deleteById(id);
    }

    public TranslatorProfile editTranslatorProfile(TranslatorProfile translatorProfile){
        TranslatorProfile translatorProfileFromDB = translatorProfileRepository.findById(translatorProfile.getId()).get();
        if(!translatorProfile.getDateOfBirth().equals(translatorProfileFromDB.getDateOfBirth())){
            translatorProfileFromDB.setDateOfBirth(translatorProfile.getDateOfBirth());
        }
        if(!translatorProfile.getEmail().equals(translatorProfileFromDB.getEmail())){
            translatorProfileFromDB.setEmail(translatorProfile.getEmail());
        }
        if(translatorProfile.getAvailable() != translatorProfileFromDB.getAvailable()){
            translatorProfileFromDB.setAvailable(translatorProfile.getAvailable());
        }
        if(translatorProfile.getOnline() != translatorProfileFromDB.getOnline()){
            translatorProfileFromDB.setOnline(translatorProfile.getOnline());
        }
        if(!translatorProfile.getLevelOfKorean().equals(translatorProfileFromDB.getLevelOfKorean())){
            translatorProfileFromDB.setLevelOfKorean(translatorProfile.getLevelOfKorean());
        }
        if(translatorProfile.getActive() != translatorProfileFromDB.getActive()){
            translatorProfileFromDB.setActive(translatorProfile.getActive());
        }
        return translatorProfileRepository.save(translatorProfileFromDB);
    }
}


