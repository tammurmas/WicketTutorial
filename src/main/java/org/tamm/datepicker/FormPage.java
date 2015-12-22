package org.tamm.datepicker;

import java.util.Date;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.DateValidator;

public class FormPage extends WebPage {
	private static final long serialVersionUID = 1L;
	private static final String DISPLAY_DATE_PATTERN = "dd.MM.yy";
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	private Form<Void> form;
	private FormModel formModel;
	public FormPage()
	{
		formModel = new FormModel();
		form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				System.out.println(formModel.formatDate(formModel.getStartDate()));
				System.out.println(formModel.formatDate(formModel.getEndDate()));
				System.out.println(form.hasError());
			};
		};
		addDateFields();
		add(new FeedbackPanel("feedback"));
		add(form);
	}
	
	private void addDateFields() {
		RangeDatePicker startDatePicker = new RangeDatePicker();
		DateTextField startDate = new DateTextField(START_DATE, new PropertyModel<Date>(formModel, START_DATE),
				new PatternDateConverter(DISPLAY_DATE_PATTERN, true));
		startDate.add(startDatePicker);
		startDate.add(new AttributeAppender("class", Model.of("date")));
		form.add(startDate);
		startDate.add(new DateValidator(startDatePicker.getMinDateModel().getObject(), startDatePicker.getMaxDateModel().getObject()));

		RangeDatePicker endDatePicker = new RangeDatePicker();
		DateTextField endDate = new DateTextField("endDate", new PropertyModel<Date>(formModel, END_DATE),
				new PatternDateConverter(DISPLAY_DATE_PATTERN, true));
		endDate.add(new RangeDatePicker());
		endDate.add(new AttributeAppender("class", Model.of("date")));
		endDate.add(new DateValidator(endDatePicker.getMinDateModel().getObject(), endDatePicker.getMaxDateModel().getObject()));
		form.add(endDate);
		
		form.add(new DateRangeValidator(startDate, endDate));
	}
}
