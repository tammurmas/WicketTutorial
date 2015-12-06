package org.tamm.discounts;

import java.text.NumberFormat;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public class PercentLabel extends WebComponent {
	private static final long serialVersionUID = 1L;

	public PercentLabel(String id) {
		super(id);
	}

	public PercentLabel(String id, IModel model) {
		super(id, model);
	}

	@Override
	public void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		NumberFormat fmt = NumberFormat.getPercentInstance(getLocale());
		fmt.setMaximumFractionDigits(2);
		fmt.setMinimumFractionDigits(0);
		Double number = (Double) getDefaultModelObject();
		String perc = number != null ? fmt.format(number) : "";
		replaceComponentTagBody(markupStream, openTag, perc);
	}
}
