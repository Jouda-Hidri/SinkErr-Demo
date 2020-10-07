package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FakeService;
import com.example.demo.service.Message;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private FakeService service;
    
    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestBody Message message) {
        return this.service.process(message);
    }

}