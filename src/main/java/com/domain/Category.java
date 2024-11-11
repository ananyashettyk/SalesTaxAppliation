package com.domain;

public enum Category {

	BOOKS, FOOD, MEDICAL_PRODUCTS;

	public static boolean isValidCategory(String category) {
		for (Category c : Category.values()) {
			if (c.name().equalsIgnoreCase(category)) {
				return true;
			}
		}
		return false;
	}
}
