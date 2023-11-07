package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.FrequentlyAskedQuestionInput;
import com.habsida.moragoproject.service.FrequentlyAskedQuestionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public List<FrequentlyAskedQuestion> findAllFrequentlyAskedQuestionPagination(@Argument int offset, @Argument int limit){
        return frequentlyAskedQuestionService.findAll(offset, limit);
    }

    @QueryMapping
    public FrequentlyAskedQuestion findFrequentlyAskedQuestionById(@Argument Long id){
        return frequentlyAskedQuestionService.findById(id);
    }

    @MutationMapping
    public FrequentlyAskedQuestion createFrequentlyAskedQuestion(@Argument FrequentlyAskedQuestionInput frequentlyAskedQuestionInput){
        return frequentlyAskedQuestionService.createFrequentlyAskedQuestion(frequentlyAskedQuestionInput);
    }

    @MutationMapping
    public void deleteFrequentlyAskedQuestionById(@Argument Long id){
        frequentlyAskedQuestionService.deleteFrequentlyAskedQuestionById(id);
    }

    @MutationMapping
    public FrequentlyAskedQuestion updateFrequentlyAskedQuestion(@Argument Long id, @Argument FrequentlyAskedQuestionInput frequentlyAskedQuestionInput){
        return frequentlyAskedQuestionService.updateFrequentlyAskedQuestion(id, frequentlyAskedQuestionInput);
    }
}
