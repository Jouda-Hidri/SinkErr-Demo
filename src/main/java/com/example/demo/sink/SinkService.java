package com.example.demo.sink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinkService {

	@Autowired
	private MessageRepo repo;

	public List<Message> show() {
		List<Message> messages = new ArrayList<Message>();
		repo.findAll().iterator().forEachRemaining(messages::add);
		return messages;
	}

	public Message fix(Message message) {
		Optional<Message> old = repo.findById(message.getId());
		if (old.isPresent()) {
			old.get().setText(message.getText());
			repo.save(old.get());
			return old.get();
			// todo particial update
		}
		return null;
	}
}
