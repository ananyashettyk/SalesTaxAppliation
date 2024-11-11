package com.service;

import com.domain.Item;
import com.serviceImpl.SalesTaxServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTaxServiceImplTest {

	private SalesTaxServiceImpl salesTaxService;

	@BeforeEach
	public void setup() {
		salesTaxService = new SalesTaxServiceImpl();
	}

	@Test
	public void testGenerateReport() {
		List<Item> items = buildItem();

		// When
		Map<String, Double> taxes = salesTaxService.calculateSalesTaxes(items);
		double totalTaxes = salesTaxService.totalTaxes(taxes);
		List<String> itemDetails = salesTaxService.itemDetails(items, taxes);
		double totalCost = salesTaxService.calculateTotalCost(items);

		// Then
		assertEquals(0.00, taxes.get("book"));
		assertEquals(1.50, taxes.get("music CD"));
		assertEquals(0.00, taxes.get("chocolate bar"));

		// Check total tax
		assertEquals(1.50, totalTaxes);

		// Check item details
		assertEquals("1 book of catagory BOOKS at : 12.49", itemDetails.get(0));
		assertEquals("1 music CD of catagory DIGITAL at : 16.49", itemDetails.get(1));
		assertEquals("1 chocolate bar of catagory FOOD at : 0.85", itemDetails.get(2));

		// Check total cost
		assertEquals(29.85, totalCost + totalTaxes);
	}

	private List<Item> buildItem() {
		Item item1 = new Item();
		item1.setQuantity(1);
		item1.setName("book");
		item1.setPrice(12.49);
		item1.setImported(false);
		item1.setCategory("BOOKS");

		Item item2 = new Item();
		item2.setQuantity(1);
		item2.setName("music CD");
		item2.setPrice(14.99);
		item2.setImported(false);
		item2.setCategory("DIGITAL");

		Item item3 = new Item();
		item3.setQuantity(1);
		item3.setName("chocolate bar");
		item3.setPrice(0.85);
		item3.setImported(false);
		item3.setCategory("FOOD");

		List<Item> list = new LinkedList<>();
		list.add(item1);
		list.add(item2);
		list.add(item3);

		return list;
	}
}