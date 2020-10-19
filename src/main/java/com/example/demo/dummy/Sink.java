package com.example.demo.dummy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Sink {
    private Long id; // todo auto generated
    private String dest;
    @Setter
    private String payload;
    
    public Sink(final Long id, final String dest, final String payload) {
    	this.id = id;
    	this.dest = dest;
    	this.payload = payload;
    }
}
