package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.CallRepository;
import com.habsida.moragoproject.model.entity.Call;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class CallDao {

    CallRepository callRepository;

    public CallDao(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public List<Call> findAll(){
        return callRepository.findAll();
    }

    public Call findById(Long id){
        return callRepository.findById(id).get();
    }

    public Call addCall(Call call){
        return callRepository.save(call);
    }

    public void deleteCall(Long id){
        callRepository.deleteById(id);
    }

    public Call editCall(Call call){
        Call callFromDB = callRepository.findById(call.getId()).get();
        if(call.getCallStatus() != callFromDB.getCallStatus()){
            callFromDB.setCallStatus(call.getCallStatus());
        }
        if(call.getTranslatorHasRated() != callFromDB.getTranslatorHasRated()){
            callFromDB.setTranslatorHasRated(call.getTranslatorHasRated());
        }
        if(call.getUserHasRated() != callFromDB.getUserHasRated()){
            callFromDB.setUserHasRated(call.getUserHasRated());
        }
        if(call.getIsEndCall() != callFromDB.getIsEndCall()){
            callFromDB.setIsEndCall(call.getIsEndCall());
        }
        if(call.getStatus() != callFromDB.getStatus()){
            callFromDB.setStatus(call.getStatus());
        }
        if(!call.getChannelName().isEmpty() && !isNull(call.getChannelName())){
            callFromDB.setChannelName(call.getChannelName());
        }
        if(call.getCommission() != callFromDB.getCommission()){
            callFromDB.setCommission(call.getCommission());
        }
        if(call.getSum() != callFromDB.getSum()){
            callFromDB.setSum(call.getSum());
        }
        if(call.getDuration() != callFromDB.getDuration()){
            callFromDB.setDuration(call.getDuration());
        }
        if(!call.getCaller().equals(callFromDB.getCaller())){
            callFromDB.setCaller(call.getCaller());
        }
        if(!call.getAnswerer().equals(callFromDB.getAnswerer())){
            callFromDB.setAnswerer(call.getAnswerer());
        }
        if(!call.getTheme().equals(callFromDB.getTheme())){
            callFromDB.setTheme(call.getTheme());
        }
        return callRepository.save(callFromDB);
    }
}
