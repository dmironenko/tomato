package com.dmironenko.tomatoes.rest;

import com.dmironenko.tomatoes.model.TomatoSale;
import com.dmironenko.tomatoes.model.ValidationException;
import com.dmironenko.tomatoes.service.TomatoSaleService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(value = "sales", description = "Endpoint for tomato sales management")
@RestController
@RequestMapping(path = "/sales")
@RequiredArgsConstructor
public class TomatoSaleController {

	private final TomatoSaleService tomatoSaleService;

	@ApiOperation(value = "Returns list of generated tomato sales", response = TomatoSale.class, responseContainer = "List")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successful retrieval of tomato sales"),
		@ApiResponse(code = 400, message = "Requested size of result is <= 0") }
	)
	@GetMapping(path = "/data")
	public List<TomatoSale> generateTomatoSales(
		@ApiParam(value = "Size of the resulting list of tomato sales", defaultValue = "3")
		@RequestParam(name = "size", required = false, defaultValue = "3") long size) {

		return tomatoSaleService.generateSales(size);
	}

}




