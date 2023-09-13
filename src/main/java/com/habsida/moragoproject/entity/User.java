package com.habsida.moragoproject.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile userProfile = new UserProfile();

    @OneToOne(cascade = CascadeType.ALL)
    private TranslatorProfile translatorProfile = new TranslatorProfile();

    public User() {
    }

    public User(String apnToken, Double balance, String fcmToken,
                String firstName, Boolean isActive, Boolean isDebtor,
                String lastName, Integer onBoardingStatus, String password,
                String phone, Double ratings, Integer totalRatings,
                Set<Role> roles, UserProfile userProfile, TranslatorProfile translatorProfile) {
        this.apnToken = apnToken;
        this.balance = balance;
        this.fcmToken = fcmToken;
        this.firstName = firstName;
        this.isActive = isActive;
        this.isDebtor = isDebtor;
        this.lastName = lastName;
        this.onBoardingStatus = onBoardingStatus;
        this.password = password;
        this.phone = phone;
        this.ratings = ratings;
        this.totalRatings = totalRatings;
        this.userProfile = userProfile;
        this.translatorProfile = translatorProfile;
        this.roles = roles;
    }

    public String getApnToken() {
        return apnToken;
    }

    public void setApnToken(String apnToken) {
        this.apnToken = apnToken;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDebtor() {
        return isDebtor;
    }

    public void setDebtor(Boolean debtor) {
        isDebtor = debtor;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getOnBoardingStatus() {
        return onBoardingStatus;
    }

    public void setOnBoardingStatus(Integer onBoardingStatus) {
        this.onBoardingStatus = onBoardingStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Integer totalRatings) {
        this.totalRatings = totalRatings;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public TranslatorProfile getTranslatorProfile() {
        return translatorProfile;
    }

    public void setTranslatorProfile(TranslatorProfile translatorProfile) {
        this.translatorProfile = translatorProfile;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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
