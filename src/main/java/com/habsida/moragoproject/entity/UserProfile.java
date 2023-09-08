package com.habsida.moragoproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UserProfile extends AbstractAuditable{

    private Boolean isFreeCallMade;

    public UserProfile() {
    }

    public UserProfile(Boolean isFreeCallMade) {
        this.isFreeCallMade = isFreeCallMade;
    }

    public Boolean getFreeCallMade() {
        return isFreeCallMade;
    }

    public void setFreeCallMade(Boolean freeCallMade) {
        isFreeCallMade = freeCallMade;
    }
}
