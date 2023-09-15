package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.ThemeRepository;
import com.habsida.moragoproject.model.entity.Theme;
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
        if(!theme.getDescription().equals("EMPTY")){
            themeFromDB.setDescription(theme.getDescription());
        }
        if(!theme.getKoreanTitle().equals("EMPTY")){
            themeFromDB.setKoreanTitle(theme.getKoreanTitle());
        }
        if(!theme.getName().equals("EMPTY")){
            themeFromDB.setName(theme.getName());
        }
        if(theme.getIsPopular() != themeFromDB.getIsPopular()){
            themeFromDB.setIsPopular(theme.getIsPopular());
        }
        if(theme.getIsActive() != themeFromDB.getIsActive()){
            themeFromDB.setIsActive(theme.getIsActive());
        }

        if(theme.getNightPrice() !=0 && theme.getNightPrice() != themeFromDB.getNightPrice()){
            themeFromDB.setNightPrice(theme.getNightPrice());
        }
        if(theme.getPrice()!=0 && theme.getPrice() != themeFromDB.getPrice()){
            themeFromDB.setPrice(theme.getPrice());
        }
        return themeRepository.save(themeFromDB);
    }
}