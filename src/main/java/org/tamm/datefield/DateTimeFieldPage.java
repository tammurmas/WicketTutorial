package org.tamm.datefield;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

public class DateTimeFieldPage extends WebPage {
	private static final long serialVersionUID = -6085660236311761608L;
	private Date date = new Date();

	public DateTimeFieldPage() {
		Form<Date> form = new Form<Date>("form") {
			private static final long serialVersionUID = 8919748256944948311L;

			@Override
			protected void onSubmit() {
				info("new date value: " + date);
			}
		};
		add(form);
		PropertyModel<Date> model = new PropertyModel<Date>(this, "date");
		form.add(new DateTimeField("dateTime", model));
		add(new FeedbackPanel("feedback"));
	}
}
