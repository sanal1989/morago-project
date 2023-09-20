package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.model.enums.CallStatus;
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
public class Call extends AbstractAuditable{

    @Enumerated(EnumType.ORDINAL)
    private CallStatus callStatus;
    private String channelName;
    private Double sum;
    private Double commission;
    private Integer duration;
    private Boolean isEndCall;
    private Boolean status;

    private Boolean translatorHasRated;
    private Boolean userHasRated;

    @ManyToOne
    private User caller;

    @ManyToOne
    private User answerer;

    @ManyToOne
    private Theme theme;

}
