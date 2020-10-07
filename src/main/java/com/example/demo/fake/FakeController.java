package com.example.demo.fake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.sink.Message;

@RestController
@RequestMapping(value = "/fake")
public class FakeController {

    @Autowired
    private FakeService service;
    
    @PostMapping(value = "/publish")
    public String send(@RequestBody Message message) {
        return this.service.process(message);
    }

}