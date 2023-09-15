package com.habsida.moragoproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating extends AbstractAuditable{

    private Double grade;

    @OneToOne
    private User userGivesRating;

    @OneToOne
    private User userTakesRating;

    @ManyToOne
    private User user;

}
