package org.tamm.containers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private Form<Void> form;
	private String queryTypeValue;

	public FormPage() {
		form = new Form<Void>("form");

		IModel<String> groupModel = new PropertyModel<String>(this, "queryTypeValue");
		
		RadioGroup<String> queryType = new RadioGroup<String>("queryType", groupModel);
		Radio<String> hvatr = new Radio<String>("hvatr", Model.of("hvatr"));
		Radio<String> vatr = new Radio<String>("vatr", Model.of("vatr"));

		TextField<Date> transaction = new TextField<Date>("transaction") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure() {
				super.onConfigure();
				if (queryType.getModel() == null) {
					setVisible(true);
				} else {
					if (queryType.getDefaultModelObjectAsString()
							.equals("vatr")) {
						setVisible(true);
					} else {
						setVisible(false);
					}
				}
			}
		};
		transaction.setOutputMarkupId(true);
		transaction.setOutputMarkupPlaceholderTag(true);

		queryType.add(vatr);
		queryType.add(hvatr);
		queryType.add(transaction);

		queryType.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				System.out.println("Updated model: "+queryType.getDefaultModelObjectAsString());
				target.add(transaction);
			}
		});

		addSubmitButton();
		form.add(queryType);
		add(form);
		System.out.println("FormPage created");
	}
	
	private void addSubmitButton()
	{
		form.add(new Button("submit", Model.of("Press here to submit")));
	}
	
	
	
	private List<Integer> getYearsList()
	{
		List<Integer> years = new ArrayList<Integer>();
		return years;
	}

}
