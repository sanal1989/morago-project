package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.FrequentlyAskedQuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class FrequentlyAskedQuestionDao {

    FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public FrequentlyAskedQuestionDao(FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository) {
        this.frequentlyAskedQuestionRepository = frequentlyAskedQuestionRepository;
    }
}
