package org.tamm.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

class Page2 extends WebPage {
	public Page2(final Page returnTo) {
		add(new Link("returnLink") {
			@Override
			public void onClick() {
				setResponsePage(returnTo);
			}
		});
	}
}
