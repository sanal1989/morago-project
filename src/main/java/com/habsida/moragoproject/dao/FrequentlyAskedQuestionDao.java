package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.FrequentlyAskedQuestionRepository;
import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FrequentlyAskedQuestionDao {

    FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public FrequentlyAskedQuestionDao(FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository) {
        this.frequentlyAskedQuestionRepository = frequentlyAskedQuestionRepository;
    }

    public List<FrequentlyAskedQuestion> findAll(){
        return frequentlyAskedQuestionRepository.findAll();
    }

    public FrequentlyAskedQuestion findById(Long id){
        return frequentlyAskedQuestionRepository.findById(id).get();
    }

    public FrequentlyAskedQuestion addFrequentlyAskedQuestion(FrequentlyAskedQuestion frequentlyAskedQuestion){
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    public void deleteFrequentlyAskedQuestion(Long id){
        frequentlyAskedQuestionRepository.deleteById(id);
    }

    public FrequentlyAskedQuestion editFrequentlyAskedQuestion(FrequentlyAskedQuestion frequentlyAskedQuestion){
        FrequentlyAskedQuestion frequentlyAskedQuestionFromDB = frequentlyAskedQuestionRepository.findById(frequentlyAskedQuestion.getId()).get();
        if(frequentlyAskedQuestionFromDB.getCategory() != frequentlyAskedQuestionFromDB.getCategory()){
            frequentlyAskedQuestionFromDB.setCategory(frequentlyAskedQuestionFromDB.getCategory());
        }
        if(!frequentlyAskedQuestionFromDB.getAnswer().equals(frequentlyAskedQuestionFromDB.getAnswer())){
            frequentlyAskedQuestionFromDB.setAnswer(frequentlyAskedQuestionFromDB.getAnswer());
        }
        if(!frequentlyAskedQuestionFromDB.getQuestion().equals(frequentlyAskedQuestionFromDB.getQuestion())){
            frequentlyAskedQuestionFromDB.setQuestion(frequentlyAskedQuestionFromDB.getQuestion());
        }
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestionFromDB);
    }
}
