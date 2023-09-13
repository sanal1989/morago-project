package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.ThemeRepository;
import com.habsida.moragoproject.entity.Rating;
import com.habsida.moragoproject.entity.Theme;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThemeDao {

    ThemeRepository themeRepository;

    public ThemeDao(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> findAll(){
        return themeRepository.findAll();
    }

    public Theme findById(Long id){
        return themeRepository.findById(id).get();
    }

    public Theme addTheme(Theme theme){
        return themeRepository.save(theme);
    }

    public void deleteTheme(Long id){
        themeRepository.deleteById(id);
    }

    public Theme editTheme(Theme theme){
        Theme themeFromDB = themeRepository.findById(theme.getId()).get();
        if(!theme.getDescription().isEmpty()){
            themeFromDB.setDescription(theme.getDescription());
        }
        if(theme.getPopular() != themeFromDB.getPopular()){
            themeFromDB.setPopular(theme.getPopular());
        }
        if(theme.getActive() != themeFromDB.getActive()){
            themeFromDB.setActive(theme.getActive());
        }
        if(!theme.getKoreanTitle().isEmpty()){
            themeFromDB.setKoreanTitle(theme.getKoreanTitle());
        }
        if(!theme.getName().isEmpty()){
            themeFromDB.setName(theme.getName());
        }
        if(theme.getNightPrice() != themeFromDB.getNightPrice()){
            themeFromDB.setNightPrice(theme.getNightPrice());
        }
        if(theme.getPrice() != themeFromDB.getPrice()){
            themeFromDB.setPrice(theme.getPrice());
        }
        return themeRepository.save(themeFromDB);
    }
}