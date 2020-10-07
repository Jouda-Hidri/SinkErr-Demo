package com.example.demo.fake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.sink.Message;

@Service
public class FakeService {
	
    private static final Logger logger = LoggerFactory.getLogger(FakeService.class);
    private static final String TOPIC = "sink";
    private static final String TOPIC_ACK = "sink_ack";
	
	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;
	
	public String process(final Message message) {
		try {
			Double.parseDouble(message.getText());
			return "Alles Klar";
		} catch (NumberFormatException nfe) {
			logger.info(String.format("#### -> Producing message -> %s", message.getText()));
			this.kafkaTemplate.send(TOPIC, message);
		}
		return "Shade";
	}
}
