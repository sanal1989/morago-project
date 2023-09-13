package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.FAQCategory;
import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.service.FrequentlyAskedQuestionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FrequentlyAskedQuestionController {
    FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    public FrequentlyAskedQuestionController(FrequentlyAskedQuestionService frequentlyAskedQuestionService) {
        this.frequentlyAskedQuestionService = frequentlyAskedQuestionService;
    }

    @QueryMapping
    public List<FrequentlyAskedQuestion> findAllFrequentlyAskedQuestion(){
        return frequentlyAskedQuestionService.findAll();
    }

    @QueryMapping
    public FrequentlyAskedQuestion findFrequentlyAskedQuestionById(@Argument Long id){
        return frequentlyAskedQuestionService.findById(id);
    }

    @MutationMapping
    public FrequentlyAskedQuestion addFrequentlyAskedQuestion(@Argument String category, @Argument String answer, @Argument String question){
        FrequentlyAskedQuestion frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setAnswer(answer);
        frequentlyAskedQuestion.setCategory(FAQCategory.valueOf(category));
        frequentlyAskedQuestion.setQuestion(question);
        return frequentlyAskedQuestionService.addFrequentlyAskedQuestion(frequentlyAskedQuestion);
    }

    @MutationMapping
    public void deleteFrequentlyAskedQuestion(@Argument Long id){
        frequentlyAskedQuestionService.deleteFrequentlyAskedQuestion(id);
    }

    @MutationMapping
    public FrequentlyAskedQuestion editFrequentlyAskedQuestion(@Argument Long id, @Argument String category, @Argument String answer, @Argument String question){
        FrequentlyAskedQuestion frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setId(id);
        frequentlyAskedQuestion.setAnswer(answer);
        frequentlyAskedQuestion.setCategory(FAQCategory.valueOf(category));
        frequentlyAskedQuestion.setQuestion(question);
        return frequentlyAskedQuestionService.editFrequentlyAskedQuestion(frequentlyAskedQuestion);
    }
}
