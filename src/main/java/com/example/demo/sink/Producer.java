package com.example.demo.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private KafkaTemplate<String, Sink> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, JsonNode> kafkaRetryTemplate;
	
	@Autowired
	private ObjectMapper mapper;

	public void sink(final Sink message) {
		logger.info(String.format("#### -> Sink -> %s", message.getPayload()));
		kafkaTemplate.send("sink", message);
	}

	public void retry(final Sink message) throws JsonMappingException, JsonProcessingException {
		logger.info(String.format("#### -> retry -> %s", message.getPayload()));
		JsonNode payload = mapper.readTree(message.getPayload());
		kafkaRetryTemplate.send(message.getDest(), payload);
	}

}
