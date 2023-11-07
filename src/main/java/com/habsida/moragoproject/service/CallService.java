package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.enums.CallStatus;
import com.habsida.moragoproject.model.input.CallInput;
import com.habsida.moragoproject.repository.CallRepository;
import com.habsida.moragoproject.repository.ThemeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CallService {

    CallRepository callRepository;
    UserService userService;
    ThemeService themeService;

    public CallService(CallRepository callRepository,
                       @Lazy UserService userService,
                       ThemeService themeService) {
        this.callRepository = callRepository;
        this.userService = userService;
        this.themeService = themeService;
    }

    public List<Call> findAll(){
        return callRepository.findAll();
    }

    public List<Call> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<Call> pages = callRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public Call findById(Long id){
        return callRepository.findById(id)
                .orElseThrow(()-> new NotFoundByIdException("Call -> Call doesn't find " + id));
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
            call.setCaller(userService.findById(callInput.getCaller()));
        }
        if(!isNull(callInput.getAnswerer())){
            call.setAnswerer(userService.findById(callInput.getAnswerer()));
        }
        if(!isNull(callInput.getTheme())){
            call.setTheme(themeService.findById(callInput.getTheme()));
        }
        return callRepository.save(call);
    }

    public String deleteCallById(Long id){
        try{
            callRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
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
