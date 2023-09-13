package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.Theme;
import com.habsida.moragoproject.service.ThemeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ThemeController {

    ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @QueryMapping
    public List<Theme> findAllTheme(){
        return themeService.findAll();
    }

    @QueryMapping
    public Theme findThemeById(@Argument Long id){
        return themeService.findById(id);
    }

    @MutationMapping
    public Theme addTheme(@Argument String description, @Argument Boolean isActive, @Argument Boolean isPopular,
                          @Argument String koreanTitle, @Argument String name, @Argument Double nightPrice, @Argument Double price){
        Theme theme = new Theme();
        theme.setDescription(description);
        theme.setActive(isActive);
        theme.setPopular(isPopular);
        theme.setKoreanTitle(koreanTitle);
        theme.setName(name);
        theme.setNightPrice(nightPrice);
        theme.setPrice(price);
        return themeService.addTheme(theme);
    }

    @MutationMapping
    public void deleteTheme(@Argument Long id){
        themeService.deleteTheme(id);
    }

    @MutationMapping
    public Theme editTheme(@Argument Long id, @Argument String description, @Argument Boolean isActive, @Argument Boolean isPopular,
                           @Argument String koreanTitle, @Argument String name, @Argument Double nightPrice, @Argument Double price){
        Theme theme = new Theme();
        theme.setId(id);
        theme.setDescription(description);
        theme.setActive(isActive);
        theme.setPopular(isPopular);
        theme.setKoreanTitle(koreanTitle);
        theme.setName(name);
        theme.setNightPrice(nightPrice);
        theme.setPrice(price);
        return themeService.editTheme(theme);
    }
}
