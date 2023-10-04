package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundById;
import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.enums.CallStatus;
import com.habsida.moragoproject.model.input.CallInput;
import com.habsida.moragoproject.repository.CallRepository;
import com.habsida.moragoproject.repository.ThemeRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CallService {

    CallRepository callRepository;
    UserRepository userRepository;
    ThemeRepository themeRepository;

    public CallService(CallRepository callRepository, UserRepository userRepository, ThemeRepository themeRepository) {
        this.callRepository = callRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
    }

    public List<Call> findAll(){
        return callRepository.findAll();
    }

    public Call findById(Long id){
        return callRepository.findById(id)
                .orElseThrow(()-> new NotFoundById("Call -> Call doesn't find " + id));
    }

    public Call createCall(CallInput callInput){
        Call call = new Call();
        if(!isNull(callInput.getCallStatus()) && callInput.getCallStatus().isEmpty()){
            call.setCallStatus(CallStatus.valueOf(callInput.getCallStatus()));
        }else {
            call.setCallStatus(CallStatus.OK);
        }
        if(!isNull(callInput.getChannelName()) && callInput.getChannelName().isEmpty()){
            call.setChannelName(callInput.getChannelName());
        }else {
            call.setChannelName("EMPTY");
        }
        if(!isNull(callInput.getCommission())){
            call.setCommission(callInput.getCommission());
        }else {
            call.setCommission(0d);
        }
        if(!isNull(callInput.getSum())){
            call.setSum(callInput.getSum());
        }else {
            call.setSum(0d);
        }
        if(!isNull(callInput.getDuration())){
            call.setDuration(callInput.getDuration());
        }else {
            call.setDuration(0);
        }
        if(!isNull(callInput.getIsEndCall())){
            call.setIsEndCall(callInput.getIsEndCall());
        }else {
            call.setIsEndCall(false);
        }
        if(!isNull(callInput.getStatus())){
            call.setStatus(callInput.getStatus());
        }else {
            call.setStatus(false);
        }
        if(!isNull(callInput.getTranslatorHasRated())){
            call.setTranslatorHasRated(callInput.getTranslatorHasRated());
        }else {
            call.setTranslatorHasRated(false);
        }
        if(!isNull(callInput.getUserHasRated())){
            call.setUserHasRated(callInput.getUserHasRated());
        }else {
            call.setUserHasRated(false);
        }
        if(!isNull(callInput.getCaller())){
            call.setCaller(userRepository.findById(callInput.getCaller())
                    .orElseThrow(()-> new NotFoundById("Call -> User doesn't find by Id " + callInput.getCaller())));
        }
        if(!isNull(callInput.getAnswerer())){
            call.setAnswerer(userRepository.findById(callInput.getAnswerer())
                    .orElseThrow(()-> new NotFoundById("Call -> User doesn't find by Id " + callInput.getAnswerer())));
        }
        if(!isNull(callInput.getTheme())){
            call.setTheme(themeRepository.findById(callInput.getTheme())
                    .orElseThrow(()-> new NotFoundById("Call -> Theme doesn't find by Id " + callInput.getTheme())));
        }
        return callRepository.save(call);
    }

    public String deleteCallById(Long id){
        try{
            callRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundById(e.getMessage());
        }
        return "Call with Id "+id+" deleted";
    }

    public Call updateCall(Long id, CallInput callInput){
        Call call = callRepository.findById(id).get();
        if(!isNull(callInput.getCallStatus()) && callInput.getCallStatus().isEmpty()){
            call.setCallStatus(CallStatus.valueOf(callInput.getCallStatus()));
        }else {
            call.setCallStatus(CallStatus.OK);
        }
        if(!isNull(callInput.getChannelName()) && callInput.getChannelName().isEmpty()){
            call.setChannelName(callInput.getChannelName());
        }else {
            call.setChannelName("EMPTY");
        }
        if(!isNull(callInput.getCommission())){
            call.setCommission(callInput.getCommission());
        }else {
            call.setCommission(0d);
        }
        if(!isNull(callInput.getSum())){
            call.setSum(callInput.getSum());
        }else {
            call.setSum(0d);
        }
        if(!isNull(callInput.getDuration())){
            call.setDuration(callInput.getDuration());
        }else {
            call.setDuration(0);
        }
        if(!isNull(callInput.getIsEndCall())){
            call.setIsEndCall(callInput.getIsEndCall());
        }else {
            call.setIsEndCall(false);
        }
        if(!isNull(callInput.getStatus())){
            call.setStatus(callInput.getStatus());
        }else {
            call.setStatus(false);
        }
        if(!isNull(callInput.getTranslatorHasRated())){
            call.setTranslatorHasRated(callInput.getTranslatorHasRated());
        }else {
            call.setTranslatorHasRated(false);
        }
        if(!isNull(callInput.getUserHasRated())){
            call.setUserHasRated(callInput.getUserHasRated());
        }else {
            call.setUserHasRated(false);
        }
        return callRepository.save(call);
    }
}
