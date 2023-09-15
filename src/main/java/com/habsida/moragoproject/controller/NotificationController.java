package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.service.NotificationService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NotificationController {

    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @QueryMapping
    public List<Notification> findAllNotification(){
        return notificationService.findAll();
    }

    @QueryMapping
    public Notification findNotificationById(Long id){
        return notificationService.findById(id);
    }

    @MutationMapping
    public Notification addNotification(Notification notification){
        return notificationService.addNotification(notification);
    }

    @MutationMapping
    public void deleteNotification(Long id){
        notificationService.deleteNotification(id);
    }

    @MutationMapping
    public Notification editNotification(Notification notification){
        return notificationService.editNotification(notification);
    }
}
