package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Item;
import com.domain.Receipt;
import com.service.SalesTaxService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/tax")
@Api(value = "SalesTaxController", tags = "REST Apis")
public class SalesTaxController {

	private final SalesTaxService salesTaxService;

	public SalesTaxController(SalesTaxService salesTaxService) {
		this.salesTaxService = salesTaxService;
	}

	@PostMapping("/generateReport")
	public Receipt generateReport(@RequestBody List<Item> items) {
		Map<String, Double> tax = salesTaxService.calculateSalesTaxes(items);
		double totalTax = salesTaxService.totalTaxes(tax);

		List<String> itemList = salesTaxService.itemDetails(items, tax);

		double totalCost = salesTaxService.calculateTotalCost(items);
		double total = totalTax + totalCost;

		log.info("Total cost of all the items excluding tax : " + total);
		return new Receipt(itemList, totalTax, total);
	}

//	Input 1:
//		1 book at 12.49
//		1 music CD at 14.99
//		1 chocolate bar at 0.85
//
//	Output 1:
//		1 book: 12.49
//		1 music CD: 16.49
//		1 chocolate bar: 0.85
//		Sales Taxes: 1.50
//		Total: 29.83

}
