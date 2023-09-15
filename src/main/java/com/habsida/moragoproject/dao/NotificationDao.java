package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.repository.NotificationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class NotificationDao {
    NotificationRepository notificationRepository;

    public NotificationDao(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findAll(){
        return notificationRepository.findAll();
    }

    public Notification findById(Long id){
        return notificationRepository.findById(id).get();
    }

    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }

    public Notification editNotification(Notification notification){
        Notification notificationFromDB = notificationRepository.findById(notification.getId()).get();
        if(!notification.getText().isEmpty() || !isNull(notification.getText())){
            notificationFromDB.setText(notification.getText());
        }
        if(!notification.getTitle().isEmpty() || !isNull(notification.getTitle())){
            notificationFromDB.setTitle(notification.getTitle());
        }
        return notificationRepository.save(notificationFromDB);
    }
}
