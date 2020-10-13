package com.example.demo.fake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/fake")
public class FakeController {

    @Autowired
    private FakeService service;
    
    @PostMapping(value = "/publish")
    public String publish(@RequestBody FakeMessage message) throws JsonProcessingException { // todo 400 instead of throw
        return this.service.process(message);
    }

}