package com.dmironenko.tomatoes.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDate {
	private final Instant yearBegin;
	private final Instant today;

	public RandomDate(final ZonedDateTime today) {
		this.yearBegin = today.with(TemporalAdjusters.firstDayOfYear()).toInstant();
		this.today = today.toInstant();
	}

	/**
	 * Returns randomly-generated long from the beginning of this year to today
	 *
	 * @return a pseudorandom {@code long} between begin of the year and today
	 */
	public long nextPastThisYearEpochSeconds() {
		return ThreadLocalRandom.current().nextLong(yearBegin.getEpochSecond(), today.getEpochSecond());
	}
}
