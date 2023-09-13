package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.CallDao;
import com.habsida.moragoproject.entity.Call;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {

    CallDao callDao;

    public CallService(CallDao callDao) {
        this.callDao = callDao;
    }

    public List<Call> findAll(){
        return callDao.findAll();
    }

    public Call findById(Long id){
        return callDao.findById(id);
    }

    public Call addCall(Call call){
        return callDao.addCall(call);
    }

    public void deleteCall(Long id){
        callDao.deleteCall(id);
    }

    public Call editCall(Call call){
        return callDao.editCall(call);
    }
}
