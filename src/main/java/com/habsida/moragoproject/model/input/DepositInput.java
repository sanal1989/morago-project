package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositInput {

    private String accountHolder;
    private Double coin;
    private String nameOfBank;
    private EStatus status;
    private Double won;

    private Long user;
}
