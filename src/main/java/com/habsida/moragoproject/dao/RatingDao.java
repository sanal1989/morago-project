package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.RatingRepository;
import org.springframework.stereotype.Component;

@Component
public class RatingDao {

    RatingRepository repository;

    public RatingDao(RatingRepository repository) {
        this.repository = repository;
    }
}
