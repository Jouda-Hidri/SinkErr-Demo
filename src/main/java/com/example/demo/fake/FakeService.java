package com.example.demo.fake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FakeService {
	
	private static final Logger logger = LoggerFactory.getLogger(FakeService.class);

	@Autowired
	private FakeProducer producer;
	
	@Autowired
	private ObjectMapper mapper;
	
	public String process(final FakeMessage message) throws JsonProcessingException {
		try {
			Double.parseDouble(message.getText());
			logger.info("process fake text succesful='{}'", message.getText());
			return "Alles Klar";
		} catch (NumberFormatException nfe) {
			String json = mapper.writeValueAsString(message);
			producer.sink(new Sink(message.getId(), "fake", json));
		}
		return "Shade";
	}
}
