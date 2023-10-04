package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.ThemeInput;
import com.habsida.moragoproject.service.ThemeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public Theme createTheme(@Argument ThemeInput themeInput){
        return themeService.createTheme(themeInput);
    }

    @MutationMapping
    public String deleteThemeById(@Argument Long id){
        return themeService.deleteThemeById(id);
    }

    @MutationMapping
    public Theme updateTheme(@Argument Long id, @Argument ThemeInput themeInput){
        return themeService.updateTheme(id, themeInput);
    }
}
