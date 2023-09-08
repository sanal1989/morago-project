package com.habsida.moragoproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Call extends AbstractAuditable{

    @Enumerated(EnumType.STRING)
    private CallStatus callStatus;
    private String channelName;
    private Double commission;
    private Integer duration;
    private Boolean isEndCall;
    private Boolean status;
    private Double sum;
    private Boolean translatorHasRated;
    private Boolean userHasRated;

    @OneToOne
    private Theme theme;

    public Call() {
    }

    public Call(CallStatus callStatus, String channelName, Double commission, Integer duration, Boolean isEndCall, Boolean status, Double sum, Boolean translatorHasRated, Boolean userHasRated, Theme theme) {
        this.callStatus = callStatus;
        this.channelName = channelName;
        this.commission = commission;
        this.duration = duration;
        this.isEndCall = isEndCall;
        this.status = status;
        this.sum = sum;
        this.translatorHasRated = translatorHasRated;
        this.userHasRated = userHasRated;
        this.theme = theme;
    }

    public CallStatus getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatus callStatus) {
        this.callStatus = callStatus;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getEndCall() {
        return isEndCall;
    }

    public void setEndCall(Boolean endCall) {
        isEndCall = endCall;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Boolean getTranslatorHasRated() {
        return translatorHasRated;
    }

    public void setTranslatorHasRated(Boolean translatorHasRated) {
        this.translatorHasRated = translatorHasRated;
    }

    public Boolean getUserHasRated() {
        return userHasRated;
    }

    public void setUserHasRated(Boolean userHasRated) {
        this.userHasRated = userHasRated;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
