package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorProfileInput {

    private String dateOfBirth;
    private String email;
    private Boolean isOnline;
    private String levelOfKorean;
    private Boolean isActive;

    private List<String> languageList = new ArrayList<>();
    private List<String> themeList = new ArrayList<>();

    private Long file;
}
