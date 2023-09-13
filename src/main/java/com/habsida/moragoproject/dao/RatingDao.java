package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.RatingRepository;
import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.entity.Rating;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingDao {

    RatingRepository ratingRepository;

    public RatingDao(RatingRepository repository) {
        this.ratingRepository = repository;
    }

    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }

    public Rating findById(Long id){
        return ratingRepository.findById(id).get();
    }

    public Rating addRating(Rating rating){
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long id){
        ratingRepository.deleteById(id);
    }

    public Rating editRating(Rating rating){
        Rating ratingFromDB = ratingRepository.findById(rating.getId()).get();
        if(rating.getGrade() != ratingFromDB.getGrade()){
            ratingFromDB.setGrade(rating.getGrade());
        }
        return ratingRepository.save(ratingFromDB);
    }
}
