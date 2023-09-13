package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.CallRepository;
import com.habsida.moragoproject.entity.Call;
import com.habsida.moragoproject.entity.CallStatus;
import com.habsida.moragoproject.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if(call.getEndCall() != callFromDB.getEndCall()){
            callFromDB.setEndCall(call.getEndCall());
        }
        if(call.getStatus() != callFromDB.getStatus()){
            callFromDB.setStatus(call.getStatus());
        }
        if(!call.getChannelName().isEmpty()){
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
        if(!call.getUserCall().equals(callFromDB.getUserCall())){
            callFromDB.setUserCall(call.getUserCall());
        }
        if(!call.getUserAnswer().equals(callFromDB.getUserAnswer())){
            callFromDB.setUserAnswer(call.getUserAnswer());
        }
        if(!call.getTheme().equals(callFromDB.getTheme())){
            callFromDB.setTheme(call.getTheme());
        }
        if(!call.getUser().equals(callFromDB.getUser())){
            callFromDB.setUser(call.getUser());
        }
        return callRepository.save(callFromDB);
    }
}
