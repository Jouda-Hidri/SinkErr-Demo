package com.example.demo.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Consumer {

	private final Producer producer;

	@Autowired
	Consumer(Producer producer) {
		this.producer = producer;
	}
	
	@Autowired
	private MessageRepo repo;
	
	@Autowired
	private ObjectMapper mapper;

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "broadway", groupId = "group_id")
	public void consume(ConsumerRecord<?, ?> consumerRecord) throws IOException {
		String json = consumerRecord.value().toString();
		logger.info("received data='{}'", json);
		// todo persist
		Message message = mapper.readValue(json, Message.class);
		repo.save(message);
	}

	@KafkaListener(topics = "broadway_err", groupId = "group_id")
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