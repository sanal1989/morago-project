package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.ThemeDao;
import com.habsida.moragoproject.entity.Theme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    ThemeDao themeDao;

    public ThemeService(ThemeDao themeDao) {
        this.themeDao = themeDao;
    }

    public List<Theme> findAll(){
        return themeDao.findAll();
    }

    public Theme findById(Long id){
        return themeDao.findById(id);
    }

    public Theme addTheme(Theme theme){
        return themeDao.addTheme(theme);
    }

    public void deleteTheme(Long id){
        themeDao.deleteTheme(id);
    }

    public Theme editTheme(Theme theme){
        return themeDao.editTheme(theme);
    }
}
