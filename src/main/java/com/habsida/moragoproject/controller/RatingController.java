package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.RatingInput;
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
    public Rating findRatingById(@Argument Long id){
        return ratingService.findById(id);
    }

    @MutationMapping
    public Rating addRating(@Argument RatingInput ratingInput){
        return ratingService.addRating(ratingInput);
    }

    @MutationMapping
    public void deleteRating(@Argument Long id){
        ratingService.deleteRating(id);
    }

    @MutationMapping
    public Rating editRating(@Argument Long id, @Argument RatingInput ratingInput){
        return ratingService.editRating(id, ratingInput);
    }
}
