package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.CallRepository;
import org.springframework.stereotype.Component;

@Component
public class CallDao {

    CallRepository callRepository;

    public CallDao(CallRepository callRepository) {
        this.callRepository = callRepository;
    }
}
