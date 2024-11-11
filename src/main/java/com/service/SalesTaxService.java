package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.domain.Item;

@Service
public interface SalesTaxService {

	Map<String, Double> calculateSalesTaxes(List<Item> products);

	List<String> itemDetails(List<Item> items, Map<String, Double> totalTaxes);

	double totalTaxes(Map<String, Double> tax);

	double calculateTotalCost(List<Item> items);
}
