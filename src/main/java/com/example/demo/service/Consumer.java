package com.example.demo.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
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

	@KafkaListener(topics = "broadway2", groupId = "group_id")
	public void consume(ConsumerRecord<?, ?> consumerRecord) throws IOException {
		logger.info("received data='{}'", consumerRecord.toString());
	}
/*	
	@KafkaListener(topics = "broadway", groupId = "group_id")
	public void consumeSource(String message) throws IOException {
		logger.info(String.format("#### -> received message from c2 -> %s", message));
	}
*/
	@KafkaListener(topics = "broadway_err2", groupId = "group_id")
	public void consumeErr(String message, final Acknowledgment acknowledgment) throws IOException {
		logger.info(String.format("#### -> received error -> %s", message));
		// retry
		/*
		message.correct();
		this.producer.sendMessage(message);
		*/
		acknowledgment.acknowledge();
	}
}