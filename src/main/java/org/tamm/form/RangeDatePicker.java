package org.tamm.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.joda.time.DateTime;

/**
 * 
 * @author Urmas
 * 
 * Implementation of a DatePicker object with a predefined range of allowed dates 
 */
class RangeDatePicker extends DatePicker {
	private static final long serialVersionUID = 1L;
	private static final String FORMAT_DATE = "MM/dd/yyyy";
	private static final Date MIN_DATE = new DateTime(2015, 12, 2, 0, 0).toDate();
	private static final Date MAX_DATE = new Date();

	@Override
	protected void configure(Map<String, Object> map, IHeaderResponse resp,
			Map<String, Object> init) {
		super.configure(map, resp, init);

		map.put("maxDate", new SimpleDateFormat(FORMAT_DATE).format(MAX_DATE));
		map.put("minDate", new SimpleDateFormat(FORMAT_DATE).format(MIN_DATE));
	}

	@Override
	protected boolean enableMonthYearSelection() {
		return true;
	}
}
