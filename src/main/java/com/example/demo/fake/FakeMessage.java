package com.example.demo.fake;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class FakeMessage {
    @Id
    private Long id;
    @Setter
	private String text;

	public void correct() {
		this.text = text.replaceAll("[^0-9.]", "");
	}
}
