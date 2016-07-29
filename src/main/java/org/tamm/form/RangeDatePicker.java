package org.tamm.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.joda.time.DateTime;

/**
 * 
 * @author Urmas
 * 
 *         Implementation of a DatePicker object with a predefined range of
 *         allowed dates
 */
public class RangeDatePicker extends DatePicker {
	private static final long serialVersionUID = 1L;
	private static final String FORMAT_DATE = "MM/dd/yyyy";
	private final IModel<Date> maxDateModel;
	private final IModel<Date> minDateModel;

	public RangeDatePicker() {
		maxDateModel = new Model<Date>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Date getObject() {
				// return new DateTime().toDate();
				return new Date(System.currentTimeMillis());
			}
		};
		
		minDateModel = new Model<Date>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Date getObject() {
				return new DateTime(2015, 12, 4, 0, 0).toDate();// aasta, kuu,
																// päev
			}
		};
	}

	@Override
	protected void configure(Map<String, Object> map, IHeaderResponse resp,
			Map<String, Object> init) {
		super.configure(map, resp, init);

		map.put("dateFormat", "dd/mm/yyyy");
		map.put("maxDate", new SimpleDateFormat(FORMAT_DATE).format(maxDateModel.getObject()));
		map.put("minDate", new SimpleDateFormat(FORMAT_DATE).format(minDateModel.getObject()));
	}

	@Override
	protected boolean enableMonthYearSelection() {
		return true;
	}
	
	public IModel<Date> getMaxDateModel() {
		return maxDateModel;
	}

	public IModel<Date> getMinDateModel() {
		return minDateModel;
	}
}
