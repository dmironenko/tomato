package com.dmironenko.tomatoes.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.concurrent.ThreadLocalRandom;

public enum Provider {
	HEINZ("Heinz"),
	HUNTS("Hunt's"),
	DEL_MONTE("Del Monte"),
	LE_OL_GRANMA("Le Ol\' Granma");

	private final String name;

	Provider(final String name) {
		this.name = name;
	}

	public static Provider random() {
		final Provider[] values = Provider.values();
		final int randomIndex = ThreadLocalRandom.current().nextInt(0, values.length);
		return values[randomIndex];
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
