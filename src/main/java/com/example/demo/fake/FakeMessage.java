package com.example.demo.fake;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FakeMessage {
    private Long id;
    @Setter
	private String text;

	public void correct() {
		this.text = text.replaceAll("[^0-9.]", "");
	}
}
