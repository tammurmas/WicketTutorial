package org.tamm.discounts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

public class PercentageField extends TextField {
	public PercentageField(String id) {
		super(id, double.class);
	}

	public PercentageField(String id, IModel model) {
		super(id, model, double.class);
	}

	@Override
	public final IConverter getConverter(Class type) {
		return new IConverter() {
			@Override
			public Object convertToObject(String value, Locale locale) {
				try {
					return getNumberFormat(locale).parseObject(value);
				} catch (ParseException e) {
					throw new ConversionException(e);
				}
			}

			@Override
			public String convertToString(Object value, Locale locale) {
				return getNumberFormat(locale).format((Double) value);
			}

			private NumberFormat getNumberFormat(Locale locale) {
				DecimalFormat fmt = new DecimalFormat("##");
				fmt.setMultiplier(100);
				return fmt;
			}
		};
	}
}
