package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TranslatorProfile extends AbstractAuditable{

    // why String
    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;
    private Boolean isActive;

    @OneToMany
    private List<Language> languageList = new ArrayList<>();

    @OneToMany
    private List<Theme> themeList = new ArrayList<>();

    public TranslatorProfile() {
    }

    public TranslatorProfile(String dateOfBirth, String email, Boolean isAvailable, Boolean isOnline, String levelOfKorean, Boolean isActive, List<Language> languageList, List<Theme> themeList) {
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isAvailable = isAvailable;
        this.isOnline = isOnline;
        this.levelOfKorean = levelOfKorean;
        this.isActive = isActive;
        this.languageList = languageList;
        this.themeList = themeList;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getLevelOfKorean() {
        return levelOfKorean;
    }

    public void setLevelOfKorean(String levelOfKoran) {
        this.levelOfKorean = levelOfKoran;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public List<Theme> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<Theme> themeList) {
        this.themeList = themeList;
    }
}
