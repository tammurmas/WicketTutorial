package org.tamm.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public class Page1 extends WebPage {
	public Page1() {
		Form form = new Form("form");
		add(form);
		form.add(new TextField("q", new Model(), Integer.class));
		Button b = new Button("do") {
			@Override
			public void onSubmit() {
				setResponsePage(new Page2(Page1.this));
			}
		};
		b.setDefaultFormProcessing(false);
		form.add(b);
		form.add(new FeedbackPanel("feedback"));
	}
}
