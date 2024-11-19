package com.example.notificationsMS.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class kafkaConsumer {

    @KafkaListener(topics = "test_topic", groupId = "notifications-groupID")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}