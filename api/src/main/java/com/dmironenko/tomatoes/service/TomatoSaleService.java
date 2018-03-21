package com.dmironenko.tomatoes.service;

import static java.util.UUID.randomUUID;

import com.dmironenko.tomatoes.model.Provider;
import com.dmironenko.tomatoes.model.TomatoSale;
import com.dmironenko.tomatoes.model.ValidationException;
import com.dmironenko.tomatoes.util.RandomDate;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TomatoSaleService {

	private static final int MIN_TOMATOES = 1;
	private static final int MAX_TOMATOES = 2000;

	private static int randomTomatoCount() {
		return ThreadLocalRandom.current().nextInt(MIN_TOMATOES, MAX_TOMATOES + 1);
	}

	/**
	 * Generates a list of TomatoSale. Each TomatoSale in list has:
	 * <ul>
	 * <li>id: randomly generated UUID</li>
	 * <li>tomatoes: randomly generated Integer from 1 to 2000 (both inclusive)</li>
	 * <li>provider: randomly generated String, one of the following: \"Heinz\", \"Hunt's\", \"Del Monte\", \"Le Ol' Granma\"</li>
	 * <li>timestamp: randomly-generated long from the beginning of this year to today</li>
	 * </ul>
	 *
	 * @param size size of list to return
	 * @return a list of generated TomatoSale sorted by timestamp
	 * @throws  ValidationException if {@literal size} is <= 0
	 */
	public List<TomatoSale> generateSales(final long size) {
		if (size <= 0L) {
			throw new ValidationException("size must be > 0");
		}

		log.debug("Generating {} tomato sales", size);

		final RandomDate randomDate = new RandomDate(ZonedDateTime.now());

		return LongStream.range(0, size)
			.mapToObj(index -> TomatoSale.builder()
				.id(randomUUID())
				.provider(Provider.random())
				.tomatoes(randomTomatoCount())
				.timestamp(randomDate.nextPastThisYearEpochSeconds())
				.build())
			.sorted(Comparator.comparing(TomatoSale::getTimestamp))
			.collect(Collectors.toList());
	}
}
