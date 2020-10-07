package com.example.demo.fake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.sink.Message;
import com.example.demo.sink.Producer;

@Service
public class FakeService {

	@Autowired
	private Producer producer;

	public String process(final Message message) {
		try {
			Double.parseDouble(message.getText());
			return "Alles Klar";
		} catch (NumberFormatException nfe) {
			producer.sink(message);
		}
		return "Shade";
	}
}
