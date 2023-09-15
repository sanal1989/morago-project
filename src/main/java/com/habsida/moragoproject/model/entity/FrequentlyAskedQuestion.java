package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.enums.FAQCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FrequentlyAskedQuestion extends AbstractAuditable{

    @Enumerated(EnumType.ORDINAL)
    private FAQCategory category;
    private String answer;
    private String question;


}
