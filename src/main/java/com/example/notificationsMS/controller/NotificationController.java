package com.example.notificationsMS.controller;

import com.example.notificationsMS.model.Notification;
import com.example.notificationsMS.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Créer une notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    // Lire toutes les notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // Lire une notification par ID
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationService.findById(id);
    }

    // Mettre à jour une notification par ID
    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification updatedNotification) {
        Notification existingNotification = notificationService.findById(id);
        if (existingNotification != null) {
            existingNotification.setTitle(updatedNotification.getTitle());
            existingNotification.setMessage(updatedNotification.getMessage());
            existingNotification.setTimestamp(updatedNotification.getTimestamp());
            return notificationService.saveNotification(existingNotification);
        }
        return null;
    }

    // Supprimer une notification par ID
    @DeleteMapping("/{id}")
    public String deleteNotification(@PathVariable Long id) {
        boolean isDeleted = notificationService.deleteNotificationById(id);
        return isDeleted ? "Notification deleted successfully!" : "Notification not found!";
    }

    // Envoyer une notification par ID
    @PostMapping("/send/{id}")
    public String sendNotification(@PathVariable Long id) {
        Notification notification = notificationService.findById(id);
        if (notification != null) {
            notificationService.sendNotification(notification.getMessage(), notification.getTitle());
            return "Notification sent successfully!";
        }
        return "Notification not found!";
    }
}
