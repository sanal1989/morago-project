package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationDao {
    NotificationRepository notificationRepository;

    public NotificationDao(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
