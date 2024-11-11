package com.serviceImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.domain.Category;
import com.domain.Item;
import com.service.SalesTaxService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
public class SalesTaxServiceImpl implements SalesTaxService {

	@Override
	public Map<String, Double> calculateSalesTaxes(List<Item> items) {
		Map<String, Double> map = new HashMap<>();

		for (Item item : items) {
			map.put(item.getName(), calculateTax(item));
		}

		return map;
	}

	@Override
	public double totalTaxes(Map<String, Double> tax) {
		return tax.values().stream().mapToDouble(Double::doubleValue).sum();
	}

	@Override
	public double calculateTotalCost(List<Item> items) {
		double total = 0;

		for (Item item : items) {
			total += item.getPrice();
		}
		log.info("Total cost of the items excluding tax: " + round(total));

		return round(total);
	}

//	o/p : 1 imported box of chocolates: 10.50
	@Override
	public List<String> itemDetails(List<Item> items, Map<String, Double> totalTaxes) {
		List<String> list = new LinkedList<>();
		for (Item item : items) {
			double totalCost = item.getPrice() + totalTaxes.get(item.getName());
			String str = String.format("%d %s of catagory %s at : %.2f", item.getQuantity(), item.getName(),
					item.getCategory(), totalCost);
			list.add(str);
		}

		return list;
	}

	private double calculateTax(Item item) {
		double taxRate = 0;

		if (!Category.isValidCategory(item.getCategory())) {
			taxRate = 0.10; // Basic sales tax rate
		}

		if (item.isImported()) {
			taxRate += 0.05; // Additional 5% import tax
		}

		double tax = item.getPrice() * taxRate;

		log.info("Tax applied for " + item.getQuantity() + " " + item.getName() + " is : " + round(tax));

		return round(tax);

	}

	private double round(double val) {
		return Math.ceil(val * 20) / 20;
	}

}
