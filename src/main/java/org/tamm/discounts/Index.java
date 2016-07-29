package org.tamm.discounts;

import org.apache.wicket.markup.html.WebPage;

public class Index extends WebPage{
	private static final long serialVersionUID = 1L;

	public Index()
	{
		add(new DiscountsPanel("discounts"));
	}
}
