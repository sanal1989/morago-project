package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Integer on_boarding_status;
    private String password;
    private String phone;
    private Double ratings;
    private Integer totalRatings;

    @OneToMany
    private List<Deposit> depositList = new ArrayList<>();

    @OneToMany
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany
    private List<Call> callList = new ArrayList<>();

    @OneToMany
    private List<Debtor> debtorList = new ArrayList<>();

    @OneToMany
    private List<Withdrawal> withdrawalList = new ArrayList<>();

    @OneToOne
    private Rating rating = new Rating();

    @OneToMany
    private List<File> fileList = new ArrayList<>();

    @ManyToOne
    private Role role = new Role();

    @OneToOne
    private UserProfile userProfile = new UserProfile();

    @OneToOne
    private TranslatorProfile translatorProfile = new TranslatorProfile();

    public User() {
    }

    public User(String apnToken, Double balance, String fcmToken, String firstName, Boolean isActive, Boolean isDebtor, String lastName, Integer on_boarding_status, String password, String phone, Double ratings, Integer totalRatings, List<Deposit> depositList, List<Notification> notificationList, List<Call> callList, List<Debtor> debtorList, List<Withdrawal> withdrawalList, Rating rating, List<File> fileList, Role role, UserProfile userProfile, TranslatorProfile translatorProfile) {
        this.apnToken = apnToken;
        this.balance = balance;
        this.fcmToken = fcmToken;
        this.firstName = firstName;
        this.isActive = isActive;
        this.isDebtor = isDebtor;
        this.lastName = lastName;
        this.on_boarding_status = on_boarding_status;
        this.password = password;
        this.phone = phone;
        this.ratings = ratings;
        this.totalRatings = totalRatings;
        this.depositList = depositList;
        this.notificationList = notificationList;
        this.callList = callList;
        this.debtorList = debtorList;
        this.withdrawalList = withdrawalList;
        this.rating = rating;
        this.fileList = fileList;
        this.role = role;
        this.userProfile = userProfile;
        this.translatorProfile = translatorProfile;
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

    public Integer getOn_boarding_status() {
        return on_boarding_status;
    }

    public void setOn_boarding_status(Integer on_boarding_status) {
        this.on_boarding_status = on_boarding_status;
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

    public List<Deposit> getDepositList() {
        return depositList;
    }

    public void setDepositList(List<Deposit> depositList) {
        this.depositList = depositList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<Call> getCallList() {
        return callList;
    }

    public void setCallList(List<Call> callList) {
        this.callList = callList;
    }

    public List<Debtor> getDebtorList() {
        return debtorList;
    }

    public void setDebtorList(List<Debtor> debtorList) {
        this.debtorList = debtorList;
    }

    public List<Withdrawal> getWithdrawalList() {
        return withdrawalList;
    }

    public void setWithdrawalList(List<Withdrawal> withdrawalList) {
        this.withdrawalList = withdrawalList;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
