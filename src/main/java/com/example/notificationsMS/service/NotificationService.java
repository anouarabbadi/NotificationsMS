package com.example.notificationsMS.service;

import com.example.notificationsMS.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    Notification saveNotification(Notification notification);
    List<Notification> getAllNotifications();
    Notification findById(Long id);
    boolean deleteNotificationById(Long id);
    void sendNotification(String body, String subject);  // Mise à jour pour accepter le corps et le sujet
}
