package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.Rating;
import com.habsida.moragoproject.service.RatingService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RatingController {

    RatingService ratingService;


    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @QueryMapping
    public List<Rating> findAllRating(){
        return ratingService.findAll();
    }

    @QueryMapping
    public Rating findRatingById(Long id){
        return ratingService.findById(id);
    }

    @MutationMapping
    public Rating addRating(@Argument Double grade){
        Rating rating = new Rating();
        rating.setGrade(grade);
        return ratingService.addRating(rating);
    }

    @MutationMapping
    public void deleteRating(@Argument Long id){
        ratingService.deleteRating(id);
    }

    @MutationMapping
    public Rating editRating(@Argument Long id, @Argument Double grade){
        Rating rating = new Rating();
        rating.setId(id);
        rating.setGrade(grade);
        return ratingService.editRating(rating);
    }
}
