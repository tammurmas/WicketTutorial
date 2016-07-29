package org.tamm.discounts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyDataBase {
	private static MyDataBase instance = null;
	private static List<Discount> discounts;

	private List<String> cheeses = Arrays.asList("Gouda", "Edam");

	public static MyDataBase getInstance() {
		if (instance == null) {
			instance = new MyDataBase();
			discounts = new ArrayList<Discount>();
			discounts.add(new Discount("Gouda discount", 20,
					"Gouda down up to 20%!", new Date(), new Date()));
			discounts.add(new Discount("Hollandi leibjuust", 20,
					"Holland up to 20%!", new Date(), new Date()));
		}

		return instance;
	}

	private MyDataBase() {
	}

	public List<Discount> listDiscounts() {
		return discounts;
	}

	public void update(List<Discount> discounts2) {
		System.out.println("Database updated!");
	}

	public void add(Discount discount) {
		System.out.println("New discount added!");
	}

	public List<String> listCheeses() {
		return cheeses;
	}

	public void remove(Discount discount) {
		System.out.println("Discount removed!");
	}

	public CharSequence exportDiscounts() {
		StringBuilder b = new StringBuilder();
		for (Discount discount : discounts) {
			b.append(discount.getName()).append(',');
			b.append(discount.getDescription()).append('\n');
		}
		return b;
	}
}
