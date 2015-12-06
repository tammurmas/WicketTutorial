package org.tamm.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.StringValidator;

public class FormPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private TextField<?> textField;
	private DropDownChoice<String> choice;
	private DateTextField startDate;
	private DateTextField endDate;
	
	public FormPage()
	{
		Form<?> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				PageParameters params=new PageParameters();
			    params.add("value", textField.getModel().getObject().toString());
			    if(choice.getModel().getObject() != null)
			    {
			    	params.add("choice", choice.getModel().getObject().toString());
			    }
			    else
			    {
			    	params.add("choice", "");
			    }
			    
				setResponsePage(ResponsePage.class, params);
			}
		};
		
		Button submit = new Button("submit", new Model<String>("Submit")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				System.out.println("Submit button pressed!");
			}
		};
		submit.setEnabled(false).setOutputMarkupId(true);
		
		form.add(submit);
		
		List<String> choices = new ArrayList<String>();
		choices.add("Eesti");
		choices.add("Leedu");
		
		choice = new DropDownChoice<String>("choice", Model.of(""), choices);
		choice.setOutputMarkupId(true);
		form.add(choice);
		
		textField = new TextField<String>("textField", Model.of(""));
		textField.add(new StringValidator(new Integer(3), new Integer(6)));
		textField.setOutputMarkupId(true);
		
		textField.add(new AjaxFormComponentUpdatingBehavior("keyup")
		{
			private static final long serialVersionUID = 953794236406467051L;
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				String value = getComponent().getDefaultModelObjectAsString();
				
				choice.setChoices(updateChoices(choices, value));
				target.add(choice);
				
				String id = getComponent().getMarkupId();
				target.appendJavaScript("document.getElementById('"+id+"').style.color = 'black';");
				
				if(!value.isEmpty())
				{
					submit.setEnabled(true);
				}
				else
				{
					submit.setEnabled(false);
				}
				target.add(submit);
			}
			
			private List<String> updateChoices(List<String> choices, String value) {
				List<String> newChoices = new ArrayList<>();
				
				for(String choice: choices)
				{
					if(choice.toLowerCase().startsWith(value.toLowerCase()))
					{
						newChoices.add(choice);
					}
				}
				
				return newChoices;
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e) {
				String id = getComponent().getMarkupId();
				target.appendJavaScript("document.getElementById('"+id+"').style.color = 'red';");
				
				submit.setEnabled(false);
				target.add(submit);
			}
		});
		
		form.add(textField);
		
		startDate = new DateTextField("startDate", getDateModel(), new PatternDateConverter ("dd.MM.yyyy", true));
		startDate.add(new RangeDatePicker());
		startDate.add(new AttributeAppender("class", Model.of("date")));
		form.add(startDate);
		
		endDate = new DateTextField("endDate", getDateModel(), new PatternDateConverter ("dd.MM.yyyy", true));
		endDate.add(new RangeDatePicker());
		endDate.add(new AttributeAppender("class", Model.of("date")));
		form.add(endDate);
		
		add(form);
	}
	
	private IModel<Date> getDateModel()
	{
		return new Model<Date>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Date getObject() {
				return new Date(System.currentTimeMillis());
			}
		};
	};
	
}
