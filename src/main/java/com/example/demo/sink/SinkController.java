package com.example.demo.sink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sink")
public class SinkController {

    @Autowired
    private SinkService service;
	
    @GetMapping(value = "/show")
    @ResponseBody
    public List<Message> show() {
        return service.show();
    }
}
