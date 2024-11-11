package com.domain;

public class Item {

	private int quantity;
	private String name;
	private double price;
	private boolean isImported;
	private String category;

	// Constructor
	public Item(int quantity, String name, double price, boolean isImported, String category) {
		this.quantity = quantity;
		this.name = name;
		this.price = price;
		this.isImported = isImported;
		this.category = category;
	}

	public Item() {
	}

	// Getters and setters
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean imported) {
		isImported = imported;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
