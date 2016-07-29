package org.tamm.datepicker;

import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.validation.ValidationError;

public class DateRangeValidator extends AbstractFormValidator {
	private static final long serialVersionUID = 1L;
	
    private final FormComponent<?>[] components;
    private FormComponent<Date> startDate;
    private FormComponent<Date> endDate;
    
    public DateRangeValidator(FormComponent<Date> s, FormComponent<Date> e)
    {
    	startDate = s;
    	endDate = e;
    	components = new FormComponent<?>[] { startDate, endDate };
    	startDate.setRequired(true);
    }

	@Override
	public FormComponent<?>[] getDependentFormComponents() {
		return components;
	}

	@Override
	public void validate(Form<?> form) {
		
	}
}
