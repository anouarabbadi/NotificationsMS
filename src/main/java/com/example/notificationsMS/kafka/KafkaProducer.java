package com.example.notificationsMS.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "test_topic";

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}