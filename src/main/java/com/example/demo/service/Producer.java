package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "broadway";
    private static final String TOPIC_ERR = "broadway_err";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        try {
            double d = Double.parseDouble(message);
            this.kafkaTemplate.send(TOPIC, message);
        } catch (NumberFormatException nfe) {
            this.kafkaTemplate.send(TOPIC_ERR, message);
        }
    }
}