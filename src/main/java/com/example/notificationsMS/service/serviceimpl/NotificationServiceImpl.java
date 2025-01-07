package com.example.notificationsMS.service.serviceimpl;

import com.example.notificationsMS.model.Notification;
import com.example.notificationsMS.repository.NotificationRepository;
import com.example.notificationsMS.service.NotificationService;
import com.example.notificationsMS.service.KeycloakUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender javaMailSender;
    private final KeycloakUserInfo keycloakUserInfo;  // Injecter KeycloakUserInfo

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender javaMailSender, KeycloakUserInfo keycloakUserInfo) {
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
        this.keycloakUserInfo = keycloakUserInfo;
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteNotificationById(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void sendNotification(String body, String subject) {
        // Récupérer l'email de l'utilisateur authentifié via Keycloak
        String recipientEmail = keycloakUserInfo.getEmail();

        if (recipientEmail != null) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("${spring.mail." +
                    "}");  // L'email de l'expéditeur
            simpleMailMessage.setTo(recipientEmail);            // L'email de l'utilisateur authentifié
            simpleMailMessage.setText(body);                    // Corps du message
            simpleMailMessage.setSubject(subject);              // Sujet du message

            // Envoyer le mail
            javaMailSender.send(simpleMailMessage);
        } else {
            System.out.println("Email de l'utilisateur non disponible.");
        }
    }
}