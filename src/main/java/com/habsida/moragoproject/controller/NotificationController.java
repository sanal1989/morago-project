package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.NotificationInput;
import com.habsida.moragoproject.service.NotificationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
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
    public String deleteNotificationById(@Argument Long id){
        return notificationService.deleteNotificationById(id);
    }

    @MutationMapping
    public Notification updateNotification(@Argument Long id, @Argument NotificationInput notificationInput){
        return notificationService.updateNotification(id, notificationInput);
    }
}
