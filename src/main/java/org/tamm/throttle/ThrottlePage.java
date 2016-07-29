package org.tamm.throttle;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.ThrottlingSettings;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;
/**
 * Throttle file edited
 * */
public class ThrottlePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public ThrottlePage() {
		
		TextField<String> textField = new TextField<String>("textField",
				new Model<String>());
		
		Form<?> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;
			
			public void onSubmit()
			{
				System.out.println(textField.getDefaultModelObjectAsString());
			}
		};
		
		textField.add(new AjaxFormComponentUpdatingBehavior("keyup") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				System.out.println(textField.getDefaultModelObjectAsString());
			}
			
			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
				super.updateAjaxAttributes(attributes);

				attributes.setThrottlingSettings(new ThrottlingSettings(
						textField.getMarkupId(), Duration.seconds(2), true));
			}
		});

		form.add(textField);
		add(form);
	}
}
