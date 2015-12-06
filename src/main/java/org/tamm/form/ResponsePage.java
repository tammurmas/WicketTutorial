package org.tamm.form;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.IRequestParameters;

public class ResponsePage extends WebPage{
	private static final long serialVersionUID = 1L;

	public ResponsePage()
	{
		IRequestParameters params = getRequest().getRequestParameters();
		if(params.getParameterValue("value") != null)
		{
			add(new Label("result", params.getParameterValue("value").toString()));
			add(new Label("choice", params.getParameterValue("choice").toString()));
		}
		else
		{
			add(new Label("result", "Go and submit form!"));
		}
		
	}
}
