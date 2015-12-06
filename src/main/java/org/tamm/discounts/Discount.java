package org.tamm.discounts;

import java.io.Serializable;
import java.util.Date;

public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	double discount;
	String description;
	Date from;
	Date until;
	
	public Discount(){}
	
	public Discount(String name, double percentage, String description, Date from, Date until) {
		this.name = name;
		this.discount = percentage;
		this.description = description;
		this.from = from;
		this.until = until;
	}

	@Override
	public String toString() {
		return "Discount [name=" + name + ", discount=" + discount
				+ ", description=" + description + ", from=" + from
				+ ", until=" + until + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
