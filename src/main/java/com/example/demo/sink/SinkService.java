package com.example.demo.sink;

import java.util.ArrayList;
import java.util.List;

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
}
