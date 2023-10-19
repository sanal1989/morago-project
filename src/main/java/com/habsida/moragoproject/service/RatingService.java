package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.RatingInput;
import com.habsida.moragoproject.repository.RatingRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RatingService {

    RatingRepository ratingRepository;
    UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public List<Rating> findAll(){
        return ratingRepository.findAll();
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
            rating.setUserGivesRating(userRepository.findById(ratingInput.getUserGivesRating())
                    .orElseThrow(()->new NotFoundByIdException("Rating->User dont find By Id " +ratingInput.getUserGivesRating())));
        }
        if(!isNull(ratingInput.getUserTakesRating())){
            rating.setUserTakesRating(userRepository.findById(ratingInput.getUserTakesRating())
                    .orElseThrow(()->new NotFoundByIdException("Rating->User dont find By Id " + ratingInput.getUserTakesRating())));
        }
        if(!isNull(ratingInput.getUser())){
            rating.setUser(userRepository.findById(ratingInput.getUser())
                    .orElseThrow(()->new NotFoundByIdException("Rating->User dont find By Id " + ratingInput.getUser())));
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
            rating.setUserGivesRating(userRepository.findById(ratingInput.getUserGivesRating())
                    .orElseThrow(()->new NotFoundByIdException("Rating->User dont find By Id " +ratingInput.getUserGivesRating())));
        }
        if(!isNull(ratingInput.getUserTakesRating())){
            rating.setUserTakesRating(userRepository.findById(ratingInput.getUserTakesRating())
                    .orElseThrow(()->new NotFoundByIdException("Rating->User dont find By Id " +ratingInput.getUserTakesRating())));
        }
        return ratingRepository.save(rating);
    }
}
