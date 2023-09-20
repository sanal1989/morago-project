package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.NotificationInput;
import com.habsida.moragoproject.service.NotificationService;
import org.springframework.graphql.data.method.annotation.Argument;
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
    public Notification findNotificationById(@Argument Long id){
        return notificationService.findById(id);
    }

    @MutationMapping
    public Notification createNotification(@Argument NotificationInput notificationInput){
        return notificationService.createNotification(notificationInput);
    }

    @MutationMapping
    public void deleteNotificationById(@Argument Long id){
        notificationService.deleteNotificationById(id);
    }

    @MutationMapping
    public Notification updateNotification(@Argument Long id, @Argument NotificationInput notificationInput){
        return notificationService.updateNotification(id, notificationInput);
    }
}
