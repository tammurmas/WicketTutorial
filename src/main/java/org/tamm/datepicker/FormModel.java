package org.tamm.datepicker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormModel implements Serializable{
	private static final String FORMAT_DATE = "dd.MM.yyyy";
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String formatDate(Date date)
	{
		return new SimpleDateFormat(FORMAT_DATE).format(date);
	}
}
