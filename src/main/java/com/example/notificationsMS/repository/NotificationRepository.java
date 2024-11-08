package com.example.notificationsMS.repository;

import com.example.notificationsMS.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
