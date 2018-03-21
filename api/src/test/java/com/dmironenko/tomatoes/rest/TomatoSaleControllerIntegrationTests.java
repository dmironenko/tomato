package com.dmironenko.tomatoes.rest;

import static org.assertj.core.api.Assertions.assertThat;

import com.dmironenko.tomatoes.model.TomatoSale;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TomatoSaleControllerIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private static void assertTomatoSale(final TomatoSale tomatoSale) {
		assertThat(tomatoSale.getId()).isNotNull();
		assertThat(tomatoSale.getProvider()).isNotNull();
		assertThat(tomatoSale.getTomatoes()).isBetween(1, 2000);
		assertThat(tomatoSale.getTimestamp()).isNotNull();
	}

	@Test
	public void getSalesData_noSize_3Results() {
		// When
		final ResponseEntity<List<TomatoSale>> resultResponse = restTemplate
			.exchange("/sales/data", HttpMethod.GET, null, new ParameterizedTypeReference<List<TomatoSale>>() {
			});

		//Then
		assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		final List<TomatoSale> result = resultResponse.getBody();
		assertThat(result.size()).isEqualTo(3);

		result.forEach(TomatoSaleControllerIntegrationTests::assertTomatoSale);
	}

	@Test
	public void getSalesData_Size100_100Results() {
		// When
		final ResponseEntity<List<TomatoSale>> resultResponse = restTemplate
			.exchange("/sales/data?size=10", HttpMethod.GET, null, new ParameterizedTypeReference<List<TomatoSale>>() {
			});

		//Then
		assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		final List<TomatoSale> result = resultResponse.getBody();
		assertThat(result.size()).isEqualTo(10);

		result.forEach(TomatoSaleControllerIntegrationTests::assertTomatoSale);
	}

	@Test
	public void getSalesData_ZeroSize_BadRequest() {
		// When
		final ResponseEntity<String> resultResponse = restTemplate.getForEntity("/sales/data?size=0", String.class);

		//Then
		assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getSalesData_NegativeSize_BadRequest() {
		// When
		final ResponseEntity<String> resultResponse = restTemplate.getForEntity("/sales/data?size=-1", String.class);

		//Then
		assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}