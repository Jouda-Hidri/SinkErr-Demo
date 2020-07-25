package com.example.demo.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	
    private final Producer producer;

    @Autowired
    Consumer(Producer producer) {
        this.producer = producer;
    }

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "broadway", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> received message -> %s", message));
    }
    
    @KafkaListener(topics = "broadway_err", groupId = "group_id")
    public void consumeErr(String message) throws IOException {
        logger.info(String.format("#### -> received error -> %s", message));
        // retry
        String corrected = message.replaceAll("[^0-9.]", "");
        this.producer.sendMessage(corrected);
    }
}