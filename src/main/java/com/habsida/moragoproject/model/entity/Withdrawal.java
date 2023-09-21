package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
                fetch = FetchType.LAZY)
    private User user;
}
