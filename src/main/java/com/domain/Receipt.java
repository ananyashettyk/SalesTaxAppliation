package com.domain;

import java.util.List;

public class Receipt {

	private List<String> items;
	private double totalTax;
	private double total;

	public Receipt() {
	}

	public Receipt(List<String> list, double totalTax, double total) {
		this.items = list;
		this.totalTax = totalTax;
		this.total = total;
	}

	// Getters and setters
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalSalesTax) {
		this.totalTax = totalSalesTax;
	}

	@Override
	public String toString() {
		StringBuilder itemDetails = new StringBuilder();

		for (String item : items) {
			itemDetails.append(item).append("\n"); // Append each item followed by a newline
		}

		// Append the total tax and total cost
		itemDetails.append("Sales Taxes: ").append(String.format("%.2f", totalTax)).append("\n");
		itemDetails.append("Total: ").append(String.format("%.2f", total));

		return itemDetails.toString();
	}

}
