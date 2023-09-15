package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.NotificationDao;
import com.habsida.moragoproject.model.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class NotificationService {

    NotificationDao notificationDao;

    public NotificationService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public List<Notification> findAll(){
        return notificationDao.findAll();
    }

    public Notification findById(Long id){
        return notificationDao.findById(id);
    }

    public Notification addNotification(Notification notification){
        return notificationDao.addNotification(notification);
    }

    public void deleteNotification(Long id){
        notificationDao.deleteNotification(id);
    }

    public Notification editNotification(Notification notification){
        return notificationDao.editNotification(notification);
    }
}
