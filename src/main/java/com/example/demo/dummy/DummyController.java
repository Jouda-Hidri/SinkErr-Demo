package com.example.demo.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/dummy")
public class DummyController {

    @Autowired
    private DummyService service;
    
    @PostMapping(value = "/publish")
    public String publish(@RequestBody DummyMessage message) throws JsonProcessingException { // todo 400 instead of throw
        return service.process(message);
    }

}