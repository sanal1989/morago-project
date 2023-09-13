package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.TranslatorProfileDao;
import com.habsida.moragoproject.entity.TranslatorProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorProfileService {

    TranslatorProfileDao translatorProfileDao;

    public TranslatorProfileService(TranslatorProfileDao translatorProfileDao) {
        this.translatorProfileDao = translatorProfileDao;
    }

    public List<TranslatorProfile> findAll(){
        return translatorProfileDao.findAll();
    }

    public TranslatorProfile findById(Long id){
        return translatorProfileDao.findById(id);
    }

    public TranslatorProfile addTranslatorProfile(TranslatorProfile translatorProfile){
        return translatorProfileDao.addTranslatorProfile(translatorProfile);
    }

    public void deleteTranslatorProfile(Long id){
        translatorProfileDao.deleteTranslatorProfile(id);
    }

    public TranslatorProfile editTranslatorProfile(TranslatorProfile translatorProfile){
        return translatorProfileDao.editTranslatorProfile(translatorProfile);
    }
}
