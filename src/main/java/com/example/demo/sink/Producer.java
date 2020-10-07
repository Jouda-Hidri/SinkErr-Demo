package com.example.demo.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.fake.FakeService;

@Component
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(FakeService.class);
	private static final String TOPIC = "sink";
	private static final String TOPIC_ACK = "sink_ack";
	private static final String TOPIC_FAKE = "fake"; // fake service

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	public void sink(final Message message) {
		logger.info(String.format("#### -> Sink -> %s", message.getText()));
		kafkaTemplate.send(TOPIC, message);
	}
	
	public void retry(final Message message) {
		logger.info(String.format("#### -> retry Fake -> %s", message.getText()));
		kafkaTemplate.send(TOPIC_FAKE, message);
	}
}
