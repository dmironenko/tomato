package com.dmironenko.tomatoes.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dmironenko.tomatoes.model.TomatoSale;
import com.dmironenko.tomatoes.model.ValidationException;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TomatoSaleServiceTest {

	private final TomatoSaleService tomatoSaleService = new TomatoSaleService();

	private static void assertTomatoSale(final TomatoSale tomatoSale) {
		assertThat(tomatoSale.getId()).isNotNull();
		assertThat(tomatoSale.getProvider()).isNotNull();
		assertThat(tomatoSale.getTomatoes()).isBetween(1, 2000);
		assertThat(tomatoSale.getTimestamp()).isNotNull();
	}

	@Test(expected = ValidationException.class)
	public void generateSales_size0_exception() {
		tomatoSaleService.generateSales(0);
	}

	@Test(expected = ValidationException.class)
	public void generateSales_negativeSize_exception() {
		tomatoSaleService.generateSales(-1);
	}

	@Test
	public void generateSales_3_3SalesGenerated() {
		// when
		final List<TomatoSale> tomatoSales = tomatoSaleService.generateSales(3);

		// then
		assertThat(tomatoSales).hasSize(3);
		assertThat(tomatoSales).isSortedAccordingTo(Comparator.comparing(TomatoSale::getTimestamp));

		tomatoSales.forEach(TomatoSaleServiceTest::assertTomatoSale);
	}

}