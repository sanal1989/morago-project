package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.input.RatingInput;
import com.habsida.moragoproject.repository.RatingRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class RatingService {

    RatingRepository ratingRepository;
    UserService userService;

    public RatingService(RatingRepository ratingRepository, UserService userService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }

    public List<Rating> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<Rating> pages = ratingRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public Rating findById(Long id){
        return ratingRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("Rating -> Rating doesn't find By Id " +id));
    }

    public Rating createRating(RatingInput ratingInput){

        Rating rating = new Rating();

        if(!isNull(ratingInput.getGrade())){
            rating.setGrade(ratingInput.getGrade());
        }else{
            rating.setGrade(0d);
        }
        if(!isNull(ratingInput.getUserGivesRating())){
            rating.setUserGivesRating(userService.findById(ratingInput.getUserGivesRating()));
        }
        if(!isNull(ratingInput.getUserTakesRating())){
            rating.setUserTakesRating(userService.findById(ratingInput.getUserTakesRating()));
        }
        if(!isNull(ratingInput.getUser())){
            rating.setUser(userService.findById(ratingInput.getUser()));
        }
        return ratingRepository.save(rating);
    }

    public String deleteRatingById(Long id){
        try{
            ratingRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Rating with Id "+id+" deleted";
    }

    public Rating updateRating(Long id, RatingInput ratingInput){
        Rating rating = ratingRepository.findById(id).get();
        if(!isNull(ratingInput.getGrade())){
            rating.setGrade(ratingInput.getGrade());
        }else{
            rating.setGrade(0d);
        }
        if(!isNull(ratingInput.getUserGivesRating())){
            rating.setUserGivesRating(userService.findById(ratingInput.getUserGivesRating()));
        }
        if(!isNull(ratingInput.getUserTakesRating())){
            rating.setUserTakesRating(userService.findById(ratingInput.getUserTakesRating()));
        }
        return ratingRepository.save(rating);
    }
}
