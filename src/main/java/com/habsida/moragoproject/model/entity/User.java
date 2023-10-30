package com.habsida.moragoproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends AbstractAuditable{

    private String apnToken;
    private Double balance;
    private String fcmToken;
    private String firstName;
    private Boolean isActive;
    private Boolean isDebtor;
    private String lastName;
    private Integer onBoardingStatus;
    private String password;
    private String phone;
    private Double ratings;
    private Integer totalRatings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile userProfile = new UserProfile();

    @OneToOne(cascade = CascadeType.ALL)
    private TranslatorProfile translatorProfile = new TranslatorProfile();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RefreshToken refreshToken;

    @Override
    public String toString() {
        return "User{" +
                "apnToken='" + apnToken + '\'' +
                ", balance=" + balance +
                ", fcmToken='" + fcmToken + '\'' +
                ", firstName='" + firstName + '\'' +
                ", isActive=" + isActive +
                ", isDebtor=" + isDebtor +
                ", lastName='" + lastName + '\'' +
                ", onBoardingStatus=" + onBoardingStatus +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", ratings=" + ratings +
                ", totalRatings=" + totalRatings +
                ", roles=" + roles +
                ", userProfile=" + userProfile +
                ", translatorProfile=" + translatorProfile +
                '}';
    }
}
