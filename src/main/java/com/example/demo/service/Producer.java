package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "broadway";
    private static final String TOPIC_ERR = "broadway_err";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        try {
            //double d = Double.parseDouble(message.getM());
            this.kafkaTemplate.send(TOPIC, message);
        } catch (NumberFormatException nfe) {
        	this.kafkaTemplate.send(TOPIC_ERR, message);
            //this.kafkaTemplate.send("broadway_sink2", "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"EQUIP\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"NUM_EQUIP\"},{\"type\":\"string\",\"optional\":false,\"field\":\"UNID\"}],\"optional\":false,\"name\":\"ACT_EQUIP\"},\"payload\":{\"EQUIP\":\"7KTUA28278\",\"NUM_EQUIP\":1,\"UNID\":\"WG33609\"}}");
        }
    }
}