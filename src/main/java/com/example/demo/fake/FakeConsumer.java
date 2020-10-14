package com.example.demo.fake;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.demo.sink.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FakeConsumer {
	
	@Autowired
	private FakeService service;
	
	@Autowired
	private ObjectMapper mapper;

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@KafkaListener(topics = "dummy", groupId = "group_id")
	public void consumeDummyRetrial(ConsumerRecord<?, ?> consumerRecord) throws IOException {
		String json = consumerRecord.value().toString();
		logger.info("received retried data='{}'", json);
		FakeMessage message = mapper.readValue(json, FakeMessage.class);
		service.process(message);
	}
}
