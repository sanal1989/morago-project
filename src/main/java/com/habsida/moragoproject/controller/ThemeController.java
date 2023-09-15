package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.ThemeInput;
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
    public Theme addTheme(@Argument ThemeInput themeInput){
        return themeService.addTheme(themeInput);
    }

    @MutationMapping
    public void deleteTheme(@Argument Long id){
        themeService.deleteTheme(id);
    }

    @MutationMapping
    public Theme editTheme(@Argument Long id, @Argument ThemeInput themeInput){
        return themeService.editTheme(id, themeInput);
    }
}
