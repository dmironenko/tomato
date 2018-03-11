package com.dmironenko.tomatoes.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TomatoSale {
	private UUID id;
	private int tomatoes;
	private Provider provider;
	private long timestamp;
}
