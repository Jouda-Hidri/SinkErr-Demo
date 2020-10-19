package com.example.demo.fake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FakeProducer {
	private static final Logger logger = LoggerFactory.getLogger(FakeProducer.class);

	@Autowired
	private KafkaTemplate<String, Sink> kafkaTemplate;

	public void sink(final Sink sink) {
		logger.info(String.format("#### -> Sink -> %s", sink.getPayload()));
		kafkaTemplate.send("sink", sink);
	}

}
