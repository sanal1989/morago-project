package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Withdrawal extends AbstractAuditable{

    @Enumerated(EnumType.ORDINAL)
    private EStatus status;

    private String accountHolder;
    private String accountNumber;
    private String nameOfBank;
    private Double sum;

    @ManyToOne
    private User user;
}
