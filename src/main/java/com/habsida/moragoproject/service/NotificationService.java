package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.entity.Rating;
import com.habsida.moragoproject.model.input.NotificationInput;
import com.habsida.moragoproject.repository.NotificationRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class NotificationService {

    NotificationRepository notificationRepository;
    UserService userService;

    public NotificationService(NotificationRepository notificationRepository, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
    }

    public List<Notification> findAll(){
        return notificationRepository.findAll();
    }

    public List<Notification> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<Notification> pages = notificationRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public Notification findById(Long id){
        return notificationRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("Notification -> Notification doesn't find by Id " + id));
    }

    public Notification createNotification(NotificationInput notificationInput){
        Notification notification = new Notification();
        if(!isNull(notificationInput.getText()) && !notificationInput.getText().isEmpty()){
            notification.setText(notificationInput.getText());
        }
        if(!isNull(notificationInput.getTitle()) && !notificationInput.getTitle().isEmpty()){
            notification.setTitle(notificationInput.getTitle());
        }
        if(!isNull(notificationInput.getUser())){
            notification.setUser(userService.findById(notificationInput.getUser()));
        }
        return notificationRepository.save(notification);
    }

    public String deleteNotificationById(Long id){
        try{
            notificationRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "Notification with Id "+id+" deleted";
    }

    public Notification updateNotification(Long id, NotificationInput notificationInput){
        Notification notification = notificationRepository.findById(id).get();
        if(!isNull(notificationInput.getText()) && !notificationInput.getText().isEmpty()){
            notification.setText(notificationInput.getText());
        }
        if(!isNull(notificationInput.getTitle()) && !notificationInput.getTitle().isEmpty()){
            notification.setTitle(notificationInput.getTitle());
        }
        if(!isNull(notificationInput.getUser())){
            notification.setUser(userService.findById(notificationInput.getUser()));
        }
        return notificationRepository.save(notification);
    }
}
