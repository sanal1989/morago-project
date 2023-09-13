package com.habsida.moragoproject.service;


import com.habsida.moragoproject.dao.FrequentlyAskedQuestionDao;
import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrequentlyAskedQuestionService {

    FrequentlyAskedQuestionDao frequentlyAskedQuestionDao;

    public FrequentlyAskedQuestionService(FrequentlyAskedQuestionDao frequentlyAskedQuestionDao) {
        this.frequentlyAskedQuestionDao = frequentlyAskedQuestionDao;
    }

    public List<FrequentlyAskedQuestion> findAll(){
        return frequentlyAskedQuestionDao.findAll();
    }

    public FrequentlyAskedQuestion findById(Long id){
        return frequentlyAskedQuestionDao.findById(id);
    }

    public FrequentlyAskedQuestion addFrequentlyAskedQuestion(FrequentlyAskedQuestion frequentlyAskedQuestion){
        return frequentlyAskedQuestionDao.addFrequentlyAskedQuestion(frequentlyAskedQuestion);
    }

    public void deleteFrequentlyAskedQuestion(Long id){
        frequentlyAskedQuestionDao.deleteFrequentlyAskedQuestion(id);
    }

    public FrequentlyAskedQuestion editFrequentlyAskedQuestion(FrequentlyAskedQuestion frequentlyAskedQuestion){
        return frequentlyAskedQuestionDao.editFrequentlyAskedQuestion(frequentlyAskedQuestion);
    }
}
