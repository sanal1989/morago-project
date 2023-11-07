package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.input.RatingInput;
import com.habsida.moragoproject.service.RatingService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public List<Rating> findAllRatingPagination(@Argument int offset, @Argument int limit){
        return ratingService.findAll(offset, limit);
    }

    @QueryMapping
    public Rating findRatingById(@Argument Long id){
        return ratingService.findById(id);
    }

    @MutationMapping
    public Rating createRating(@Argument RatingInput ratingInput){
        return ratingService.createRating(ratingInput);
    }

    @MutationMapping
    public String deleteRatingById(@Argument Long id){
        return ratingService.deleteRatingById(id);
    }

    @MutationMapping
    public Rating updateRating(@Argument Long id, @Argument RatingInput ratingInput){
        return ratingService.updateRating(id, ratingInput);
    }
}
