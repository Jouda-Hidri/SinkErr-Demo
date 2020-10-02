package com.example.demo.service;

public class Message {
	private String m;

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public void correct() {
		this.m = m.replaceAll("[^0-9.]", "");
	}
}
