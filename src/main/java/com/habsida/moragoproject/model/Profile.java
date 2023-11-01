package com.habsida.moragoproject.model;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private Boolean isFreeCallMade;
    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;
    private Boolean isActive;
    private Set<Language> languageList = new HashSet<>();
    private Set<Theme> themeList = new HashSet<>();
    private String selfDescription;
}
