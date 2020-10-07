package com.example.demo.service;

import lombok.Getter;

@Getter
public class Message {
	private long id;
	private String text;

	public void correct() {
		this.text = text.replaceAll("[^0-9.]", "");
	}
}
