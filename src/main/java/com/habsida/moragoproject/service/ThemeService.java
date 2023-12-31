package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.ThemeInput;
import com.habsida.moragoproject.repository.CategoryRepository;
import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.repository.ThemeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ThemeService {

    ThemeRepository themeRepository;
    CategoryService categoryService;
    FileService fileService;

    public ThemeService(ThemeRepository themeRepository,
                        CategoryService categoryService,
                        FileService fileService) {
        this.themeRepository = themeRepository;
        this.categoryService = categoryService;
        this.fileService = fileService;
    }

    public List<Theme> findAll(){
        return themeRepository.findAll();
    }

    public List<Theme> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<Theme> pages =themeRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public Theme findById(Long id){
        return themeRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("Theme-> Theme doesn't find by Id " + id));
    }

    public Theme createTheme(ThemeInput themeInput){
        Theme theme = new Theme();
        if(!isNull(themeInput.getDescription()) && !themeInput.getDescription().isEmpty()){
            theme.setDescription(themeInput.getDescription());
        }
        if(!isNull(themeInput.getKoreanTitle()) && !themeInput.getKoreanTitle().isEmpty()){
            theme.setKoreanTitle(themeInput.getKoreanTitle());
        }
        if(!isNull(themeInput.getName()) && !themeInput.getName().isEmpty()){
            theme.setName(themeInput.getName());
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
            theme.setCategory(categoryService.findById(themeInput.getCategory()));
        }
        if(!isNull(themeInput.getFile())){
            theme.setFile(fileService.findById(themeInput.getFile()));
        }
        return themeRepository.save(theme);
    }

    public String deleteThemeById(Long id){
        try{
            themeRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Theme with Id "+id+" deleted";
    }

    public Theme updateTheme(Long id, ThemeInput themeInput){
        Theme theme = themeRepository.findById(id).get();
        if(!isNull(themeInput.getDescription()) && !themeInput.getDescription().isEmpty()){
            theme.setDescription(themeInput.getDescription());
        }
        if(!isNull(themeInput.getKoreanTitle()) && !themeInput.getKoreanTitle().isEmpty()){
            theme.setKoreanTitle(themeInput.getKoreanTitle());
        }
        if(!isNull(themeInput.getName()) && !themeInput.getName().isEmpty()){
            theme.setName(themeInput.getName());
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
            theme.setCategory(categoryService.findById(themeInput.getCategory()));
        }
        if(!isNull(themeInput.getFile())){
            theme.setFile(fileService.findById(themeInput.getFile()));
        }
        return themeRepository.save(theme);
    }

    public Optional<Theme> findByName(String name) {
        return themeRepository.findByName(name);
    }
}
