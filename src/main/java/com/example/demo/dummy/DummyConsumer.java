package com.example.demo.dummy;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.demo.sink.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DummyConsumer {
	
	@Autowired
	private DummyService service;
	
	@Autowired
	private ObjectMapper mapper;

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@KafkaListener(topics = "dummy", groupId = "group_id")
	public void consumeDummyRetrial(ConsumerRecord<?, ?> consumerRecord) throws IOException {
		String json = consumerRecord.value().toString();
		logger.info("received retried data='{}'", json);
		DummyMessage message = mapper.readValue(json, DummyMessage.class);
		service.process(message);
	}
}
