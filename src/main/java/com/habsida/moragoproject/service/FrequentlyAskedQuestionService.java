package com.habsida.moragoproject.service;


import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.enums.FAQCategory;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionInput;
import com.habsida.moragoproject.repository.FrequentlyAskedQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class FrequentlyAskedQuestionService {

    FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public FrequentlyAskedQuestionService(FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository) {
        this.frequentlyAskedQuestionRepository = frequentlyAskedQuestionRepository;
    }

    public List<FrequentlyAskedQuestion> findAll(){
        return frequentlyAskedQuestionRepository.findAll();
    }

    public FrequentlyAskedQuestion findById(Long id){
        return frequentlyAskedQuestionRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("FrequentlyAskedQuestion -> FrequentlyAskedQuestion doesn't find by Id " + id));
    }

    public FrequentlyAskedQuestion createFrequentlyAskedQuestion(FrequentlyAskedQuestionInput frequentlyAskedQuestionInput){
        FrequentlyAskedQuestion frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        if(!isNull(frequentlyAskedQuestionInput.getQuestion()) && !frequentlyAskedQuestionInput.getQuestion().isEmpty()){
            frequentlyAskedQuestion.setQuestion(frequentlyAskedQuestionInput.getQuestion());
        }
        if(!isNull(frequentlyAskedQuestionInput.getAnswer()) && !frequentlyAskedQuestionInput.getAnswer().isEmpty()){
            frequentlyAskedQuestion.setAnswer(frequentlyAskedQuestionInput.getAnswer());
        }
        if(!isNull(frequentlyAskedQuestionInput.getCategory()) && !frequentlyAskedQuestionInput.getCategory().isEmpty()){
            frequentlyAskedQuestion.setCategory(FAQCategory.valueOf(frequentlyAskedQuestionInput.getCategory()));
        }else{
            frequentlyAskedQuestion.setCategory(FAQCategory.FAQ1);
        }
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    public String deleteFrequentlyAskedQuestionById(Long id){
        try {
            frequentlyAskedQuestionRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "FrequentlyAskedQuestion with Id "+id+" deleted";
    }

    public FrequentlyAskedQuestion updateFrequentlyAskedQuestion(Long id, FrequentlyAskedQuestionInput frequentlyAskedQuestionInput){
        FrequentlyAskedQuestion frequentlyAskedQuestion = frequentlyAskedQuestionRepository.findById(id).get();
        if(!isNull(frequentlyAskedQuestionInput.getQuestion()) && !frequentlyAskedQuestionInput.getQuestion().isEmpty()){
            frequentlyAskedQuestion.setQuestion(frequentlyAskedQuestionInput.getQuestion());
        }
        if(!isNull(frequentlyAskedQuestionInput.getAnswer()) && !frequentlyAskedQuestionInput.getAnswer().isEmpty()){
            frequentlyAskedQuestion.setAnswer(frequentlyAskedQuestionInput.getAnswer());
        }
        if(!isNull(frequentlyAskedQuestionInput.getCategory()) && !frequentlyAskedQuestionInput.getCategory().isEmpty()){
            frequentlyAskedQuestion.setCategory(FAQCategory.valueOf(frequentlyAskedQuestionInput.getCategory()));
        }else{
            frequentlyAskedQuestion.setCategory(FAQCategory.FAQ1);
        }
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }
}
