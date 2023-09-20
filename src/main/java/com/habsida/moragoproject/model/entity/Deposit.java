package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deposit extends AbstractAuditable{

    private String accountHolder;
    private String nameOfBank;
    private Double coin;
    private Double won;
    private EStatus status;

    @ManyToOne
    private User user;
}
