package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.ThemeInput;
import com.habsida.moragoproject.repository.CategoryRepository;
import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ThemeService {

    ThemeRepository themeRepository;
    CategoryRepository categoryRepository;
    FileRepository fileRepository;

    public ThemeService(ThemeRepository themeRepository, CategoryRepository categoryRepository, FileRepository fileRepository) {
        this.themeRepository = themeRepository;
        this.categoryRepository = categoryRepository;
        this.fileRepository = fileRepository;
    }

    public List<Theme> findAll(){
        return themeRepository.findAll();
    }

    public Theme findById(Long id){
        return themeRepository.findById(id).orElseThrow(()->new RuntimeException("Theme-> Theme doesn't find by Id"));
    }

    public Theme createTheme(ThemeInput themeInput){
        Theme theme = new Theme();
        if(!isNull(themeInput.getDescription()) && !themeInput.getDescription().isEmpty()){
            theme.setDescription(themeInput.getDescription());
        }else {
            theme.setDescription("EMPTY");
        }
        if(!isNull(themeInput.getKoreanTitle()) && !themeInput.getKoreanTitle().isEmpty()){
            theme.setKoreanTitle(themeInput.getKoreanTitle());
        }else {
            theme.setKoreanTitle("EMPTY");
        }
        if(!isNull(themeInput.getName()) && !themeInput.getName().isEmpty()){
            theme.setName(themeInput.getName());
        }else {
            theme.setName("EMPTY");
        }
        if(!isNull(themeInput.getIsActive())){
            theme.setIsActive(themeInput.getIsActive());
        }else {
            theme.setIsActive(false);
        }
        if(!isNull(themeInput.getIsPopular())){
            theme.setIsPopular(themeInput.getIsPopular());
        }else {
            theme.setIsPopular(false);
        }
        if(!isNull(themeInput.getNightPrice())){
            theme.setNightPrice(themeInput.getNightPrice());
        }else {
            theme.setNightPrice(0d);
        }
        if(!isNull(themeInput.getPrice())){
            theme.setPrice(themeInput.getPrice());
        }else {
            theme.setPrice(0d);
        }
        if(!isNull(themeInput.getCategory())){
            theme.setCategory(categoryRepository.findById(themeInput.getCategory())
                    .orElseThrow(()->new RuntimeException("Theme-> Category doesn't find By Id")));
        }
        if(!isNull(themeInput.getFile())){
            theme.setFile(fileRepository.findById(themeInput.getFile())
                    .orElseThrow(()->new RuntimeException("Theme-> File doesn't find By Id")));
        }
        return themeRepository.save(theme);
    }

    public void deleteThemeById(Long id){
        themeRepository.deleteById(id);
    }

    public Theme updateTheme(Long id, ThemeInput themeInput){
        Theme theme = themeRepository.findById(id).get();
        if(!isNull(themeInput.getDescription()) && !themeInput.getDescription().isEmpty()){
            theme.setDescription(themeInput.getDescription());
        }else {
            theme.setDescription("EMPTY");
        }
        if(!isNull(themeInput.getKoreanTitle()) && !themeInput.getKoreanTitle().isEmpty()){
            theme.setKoreanTitle(themeInput.getKoreanTitle());
        }else {
            theme.setKoreanTitle("EMPTY");
        }
        if(!isNull(themeInput.getName()) && !themeInput.getName().isEmpty()){
            theme.setName(themeInput.getName());
        }else {
            theme.setName("EMPTY");
        }
        if(!isNull(themeInput.getIsActive())){
            theme.setIsActive(themeInput.getIsActive());
        }else {
            theme.setIsActive(false);
        }
        if(!isNull(themeInput.getIsPopular())){
            theme.setIsPopular(themeInput.getIsPopular());
        }else {
            theme.setIsPopular(false);
        }
        if(!isNull(themeInput.getNightPrice())){
            theme.setNightPrice(themeInput.getNightPrice());
        }else {
            theme.setNightPrice(0d);
        }
        if(!isNull(themeInput.getPrice())){
            theme.setPrice(themeInput.getPrice());
        }else {
            theme.setPrice(0d);
        }
        if(!isNull(themeInput.getCategory())){
            theme.setCategory(categoryRepository.findById(themeInput.getCategory())
                    .orElseThrow(()->new RuntimeException("Theme-> Category doesn't find By Id")));
        }
        if(!isNull(themeInput.getFile())){
            theme.setFile(fileRepository.findById(themeInput.getFile())
                    .orElseThrow(()->new RuntimeException("Theme-> File doesn't find By Id")));
        }
        return themeRepository.save(theme);
    }
}
