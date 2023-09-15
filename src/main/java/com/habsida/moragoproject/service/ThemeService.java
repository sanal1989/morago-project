package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.CategoryDao;
import com.habsida.moragoproject.dao.FileDao;
import com.habsida.moragoproject.dao.ThemeDao;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.ThemeInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ThemeService {

    ThemeDao themeDao;
    CategoryDao categoryDao;
    FileDao fileDao;

    public ThemeService(ThemeDao themeDao, CategoryDao categoryDao, FileDao fileDao) {
        this.themeDao = themeDao;
        this.categoryDao = categoryDao;
        this.fileDao = fileDao;
    }

    public List<Theme> findAll(){
        return themeDao.findAll();
    }

    public Theme findById(Long id){
        return themeDao.findById(id);
    }

    public Theme addTheme(ThemeInput themeInput){
        Theme theme = new Theme();
        if(isNull(themeInput.getDescription()) || themeInput.getDescription().isEmpty()){
            theme.setDescription("EMPTY");
        }else {
            theme.setDescription(themeInput.getDescription());
        }
        if(isNull(themeInput.getKoreanTitle()) || themeInput.getKoreanTitle().isEmpty()){
            theme.setKoreanTitle("EMPTY");
        }else {
            theme.setKoreanTitle(themeInput.getKoreanTitle());
        }
        if(isNull(themeInput.getName()) || themeInput.getName().isEmpty()){
            theme.setName("EMPTY");
        }else {
            theme.setName(themeInput.getName());
        }
        if(isNull(themeInput.getIsActive())){
            theme.setIsActive(false);
        }else {
            theme.setIsActive(themeInput.getIsActive());
        }
        if(isNull(themeInput.getIsPopular())){
            theme.setIsPopular(false);
        }else {
            theme.setIsPopular(themeInput.getIsPopular());
        }
        if(isNull(themeInput.getNightPrice())){
            theme.setNightPrice(0d);
        }else {
            theme.setNightPrice(themeInput.getNightPrice());
        }
        if(isNull(themeInput.getPrice())){
            theme.setPrice(0d);
        }else {
            theme.setPrice(themeInput.getPrice());
        }
        if(!isNull(themeInput.getCategory())){
            theme.setCategory(categoryDao.findById(themeInput.getCategory()));
        }
        if(!isNull(themeInput.getFile())){
            theme.setFile(fileDao.findById(themeInput.getFile()));
        }
        return themeDao.addTheme(theme);
    }

    public void deleteTheme(Long id){
        themeDao.deleteTheme(id);
    }

    public Theme editTheme(Long id, ThemeInput themeInput){
        Theme theme = new Theme();
        theme.setId(id);
        if(isNull(themeInput.getDescription()) || themeInput.getDescription().isEmpty()){
            theme.setDescription("EMPTY");
        }else {
            theme.setDescription(themeInput.getDescription());
        }
        if(isNull(themeInput.getKoreanTitle()) || themeInput.getKoreanTitle().isEmpty()){
            theme.setKoreanTitle("EMPTY");
        }else {
            theme.setKoreanTitle(themeInput.getKoreanTitle());
        }
        if(isNull(themeInput.getName()) || themeInput.getName().isEmpty()){
            theme.setName("EMPTY");
        }else {
            theme.setName(themeInput.getName());
        }
        if(isNull(themeInput.getIsActive())){
            theme.setIsActive(false);
        }else {
            theme.setIsActive(themeInput.getIsActive());
        }
        if(isNull(themeInput.getIsPopular())){
            theme.setIsPopular(false);
        }else {
            theme.setIsPopular(themeInput.getIsPopular());
        }
        if(isNull(themeInput.getNightPrice())){
            theme.setNightPrice(0d);
        }else {
            theme.setNightPrice(themeInput.getNightPrice());
        }
        if(isNull(themeInput.getPrice())){
            theme.setPrice(0d);
        }else {
            theme.setPrice(themeInput.getPrice());
        }
        return themeDao.editTheme(theme);
    }
}
