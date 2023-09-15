package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingInput {

    private Double grade;

    private Long userGivesRating;
    private Long userTakesRating;
    private Long user;
}
