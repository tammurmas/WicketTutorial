package org.tamm.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.validation.validator.StringValidator;

public class FormPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private static final ResourceReference MASKED_INPUT_JS = new JavaScriptResourceReference(FormPage.class, "maskedinput.js");
	private static final ResourceReference MASK_JS = new JavaScriptResourceReference(FormPage.class, "mask.js");
	
	private TextField<?> textField;
	private DropDownChoice<String> choice;
	private DateTextField startDate;
	private DateTextField endDate;
	private Button submit;
	private Form<?> form;
	
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptReferenceHeaderItem.forReference(MASKED_INPUT_JS));
		response.render(JavaScriptReferenceHeaderItem.forReference(MASK_JS));
	}
	
	public FormPage()
	{		
		createForm();
		addSubmitButton();
		addTextFieldAndChoice();
		addDateFields();
		add(form);
	}
	
	private void createForm() {
		form = new Form<Void>("form") {
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
		
	}

	private void addSubmitButton()
	{
		submit = new Button("submit", new Model<String>("Submit"));
		submit.setEnabled(false).setOutputMarkupId(true);
		form.add(submit);
	}
	
	private void addDateFields() {
		startDate = new DateTextField("startDate", getDateModel(),
				new PatternDateConverter("dd.MM.yyyy", true));
		startDate.add(new RangeDatePicker());
		startDate.add(new AttributeAppender("class", Model.of("date")));
		form.add(startDate);

		endDate = new DateTextField("endDate", getDateModel(),
				new PatternDateConverter("dd.MM.yyyy", true));
		endDate.add(new RangeDatePicker());
		endDate.add(new AttributeAppender("class", Model.of("date")));
		form.add(endDate);
	}

	private void addTextFieldAndChoice() {
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
				
				submit.setEnabled(!value.isEmpty());
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
