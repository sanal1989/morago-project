package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.RatingDao;
import com.habsida.moragoproject.dao.UserDao;
import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.RatingInput;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RatingService {

    RatingDao ratingDao;
    UserDao userDao;

    public RatingService(RatingDao ratingDao, UserDao userDao) {
        this.ratingDao = ratingDao;
        this.userDao = userDao;
    }

    public List<Rating> findAll(){
        return ratingDao.findAll();
    }

    public Rating findById(Long id){
        return ratingDao.findById(id);
    }

    public Rating addRating(RatingInput ratingInput){
        Rating rating = new Rating();
        if(isNull(ratingInput.getGrade())){
            rating.setGrade(0d);
        }else{
            rating.setGrade(ratingInput.getGrade());
        }
        if(!isNull(ratingInput.getUserGivesRating())){
            rating.setUserGivesRating(userDao.findById(ratingInput.getUserGivesRating()));
        }
        if(!isNull(ratingInput.getUserTakesRating())){
            rating.setUserTakesRating(userDao.findById(ratingInput.getUserTakesRating()));
        }
        if(!isNull(ratingInput.getUser())){
            rating.setUser(userDao.findById(ratingInput.getUser()));
        }
        return ratingDao.addRating(rating);
    }

    public void deleteRating(Long id){
        ratingDao.deleteRating(id);
    }

    public Rating editRating(Long id, RatingInput ratingInput){
        Rating rating = new Rating();
        rating.setId(id);
        if(isNull(ratingInput.getGrade())){
            rating.setGrade(0d);
        }else{
            rating.setGrade(ratingInput.getGrade());
        }
        return ratingDao.editRating(rating);
    }
}
