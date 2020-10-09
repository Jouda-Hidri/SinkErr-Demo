package com.example.demo.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.sink.Sink;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.fake.FakeService;
import com.example.demo.sink.Producer;

@Service
public class DummyService {
	
	private static final Logger logger = LoggerFactory.getLogger(FakeService.class);

	@Autowired
	private Producer producer;
	
	@Autowired
	private ObjectMapper mapper;

	public String process(final DummyMessage message) throws JsonProcessingException {
		try {
			Double.parseDouble(message.getText());
			logger.info("process dummy text succesful='{}'", message.getText());
			return "Alles Klar";
		} catch (NumberFormatException nfe) {
			String json = mapper.writeValueAsString(message);
			producer.sink(new Sink(message.getId(), "dummy", json));
		}
		return "Shade";
	}
}
