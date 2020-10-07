package com.example.demo.sink;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Message {
    @Id
    private Long id;
	private String text;

	public void correct() {
		this.text = text.replaceAll("[^0-9.]", "");
	}
}
