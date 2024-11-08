package com.example.notificationsMS.service.serviceimpl;

import com.example.notificationsMS.model.Notification;
import com.example.notificationsMS.repository.NotificationRepository;
import com.example.notificationsMS.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Save a new notification
    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Retrieve all notifications
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Find a notification by its ID
    @Override
    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    // Delete a notification by its ID
    @Override
    public boolean deleteNotificationById(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Logic for sending a notification
    @Override
    public void sendNotification(Notification notification) {
        // Implement the logic to send the notification here
        // For example, sending an email, SMS, or push notification
        // This could involve calling an external API or a messaging service
        System.out.println("Sending notification: " + notification.getTitle() + " - " + notification.getMessage());
    }
}