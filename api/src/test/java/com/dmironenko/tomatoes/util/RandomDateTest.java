package com.dmironenko.tomatoes.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RandomDateTest {

	private static final int INTERATION_COUNT = 10000;

	@Test
	public void nextPastThisYearEpochMilli() {
		// Given
		final LocalDateTime today = LocalDate.of(2018, 3, 1).atStartOfDay();
		final ZonedDateTime todayZoned = ZonedDateTime.of(today, ZoneId.systemDefault());

		final LocalDateTime firstDayOfYear = LocalDate.of(2018, 1, 1).atStartOfDay();
		final ZonedDateTime firstZonedDayOfYear = ZonedDateTime.of(firstDayOfYear, ZoneId.systemDefault());

		final RandomDate randomDate = new RandomDate(todayZoned);

		for (int i = 0; i < INTERATION_COUNT; i++) {
			// When
			final long epochMilli = randomDate.nextPastThisYearEpochSeconds();

			// Then
			final ZonedDateTime zonedDateTime = Instant.ofEpochSecond(epochMilli).atZone(ZoneId.systemDefault());

			Assertions.assertThat(zonedDateTime).isBetween(firstZonedDayOfYear, todayZoned);
		}
	}
}