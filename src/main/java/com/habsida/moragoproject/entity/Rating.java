package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rating extends AbstractAuditable{

    private Double grade;

    @OneToOne
    private User userGivesRating;

    @OneToOne
    private User userTakesRating;

    @ManyToOne
    private User user;

    public Rating() {
    }

    public Rating(Double grade, User userGivesRating, User userTakesRating, User user) {
        this.grade = grade;
        this.userGivesRating = userGivesRating;
        this.userTakesRating = userTakesRating;
        this.user = user;
    }

    public User getUserGivesRating() {
        return userGivesRating;
    }

    public void setUserGivesRating(User userGivesRating) {
        this.userGivesRating = userGivesRating;
    }

    public User getUserTakesRating() {
        return userTakesRating;
    }

    public void setUserTakesRating(User userTakesRating) {
        this.userTakesRating = userTakesRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
