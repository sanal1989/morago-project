package com.habsida.moragoproject.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class FrequentlyAskedQuestion extends AbstractAuditable{

    @Enumerated(EnumType.ORDINAL)
    private FAQCategory category;
    private String answer;
    private String question;

    public FrequentlyAskedQuestion() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public FAQCategory getCategory() {
        return category;
    }

    public void setCategory(FAQCategory category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
