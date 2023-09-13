package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.RatingDao;
import com.habsida.moragoproject.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    RatingDao ratingDao;

    public RatingService(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    public List<Rating> findAll(){
        return ratingDao.findAll();
    }

    public Rating findById(Long id){
        return ratingDao.findById(id);
    }

    public Rating addRating(Rating rating){
        return ratingDao.addRating(rating);
    }

    public void deleteRating(Long id){
        ratingDao.deleteRating(id);
    }

    public Rating editRating(Rating rating){
        return ratingDao.editRating(rating);
    }
}
