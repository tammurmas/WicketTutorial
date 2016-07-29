package org.tamm.datefield;

import java.util.Calendar;
import java.util.Date;

import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.form.datepicker.DatePicker;


public class DateTimeField extends FormComponentPanel<Date> {
	private static final long serialVersionUID = 6808200342399266557L;
	private Date date;
	private Integer hours;
	private Integer minutes;
	private final DateTextField dateField;
	private final TextField<Integer> hoursField;
	private final TextField<Integer> minutesField;

	public DateTimeField(String id) {
		this(id, null);
	}

	public DateTimeField(String id, IModel<Date> model) {
		super(id, model);
		setType(Date.class);
		PropertyModel<Date> dateFieldModel = new PropertyModel<Date>(this,
				"date");
		add(dateField = newDateTextField("date", dateFieldModel));
		Options options = new Options();
		options.set("changeMonth", true).set("changeYear", true);
		DatePicker datePicker = new DatePicker("datePicker", dateFieldModel, options);
		add(datePicker);
		hoursField = new TextField<Integer>("hours",
				new PropertyModel<Integer>(this, "hours"), Integer.class);
		add(hoursField);
		hoursField.add(new RangeValidator<Integer>(0, 59));
		hoursField.setLabel(new Model<String>("hours"));
		minutesField = new TextField<Integer>("minutes",
				new PropertyModel<Integer>(this, "minutes"), Integer.class);
		add(minutesField);
		minutesField.add(new RangeValidator<Integer>(0, 59));
		minutesField.setLabel(new Model<String>("minutes"));
	}

	protected DateTextField newDateTextField(String id,
			PropertyModel<Date> dateFieldModel) {
		return DateTextField.forShortStyle(id, dateFieldModel, false);
	}

	@Override
	protected void onBeforeRender() {
		date = (Date) getModelObject();
		if (date != null) {
			Calendar calendar = Calendar.getInstance(getLocale());
			calendar.setTime(date);
			hours = calendar.get(Calendar.HOUR_OF_DAY);
			minutes = calendar.get(Calendar.MINUTE);
		}
		dateField.setRequired(isRequired());
		super.onBeforeRender();
	}

	@Override
	public void convertInput() {
		Date date = (Date) dateField.getConvertedInput();
		if (date != null) {
			Calendar calendar = Calendar.getInstance(getLocale());
			calendar.setTime(date);
			Integer hours = (Integer) hoursField.getConvertedInput();
			Integer minutes = (Integer) minutesField.getConvertedInput();
			if (hours != null) {
				calendar.set(Calendar.HOUR_OF_DAY, hours % 24);
				calendar.set(Calendar.MINUTE, (minutes != null) ? minutes : 0);
			}
			setConvertedInput(calendar.getTime());
		} else {
			setConvertedInput(null);
		}
	}

	@Override
	public String getInput() {
		return dateField.getInput() + ", " + hoursField.getInput() + ":" + minutesField.getInput();
	}
}
