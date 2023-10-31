package com.habsida.moragoproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TranslatorProfile extends AbstractAuditable  {

    private String dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;
    private Boolean isActive;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Set<Language> languageList = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private Set<Theme> themeList = new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private File file;

    @Override
    public String toString() {
        return "TranslatorProfile{" +
                "dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", isAvailable=" + isAvailable +
                ", isOnline=" + isOnline +
                ", levelOfKorean='" + levelOfKorean + '\'' +
                ", isActive=" + isActive +
                ", languageList=" + languageList +
                ", themeList=" + themeList +
                ", file=" + file +
                '}';
    }
}
