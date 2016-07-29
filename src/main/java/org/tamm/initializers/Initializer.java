package org.tamm.initializers;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.request.resource.CharSequenceResource;
import org.apache.wicket.request.resource.IResource;
import org.tamm.discounts.MyDataBase;

public class Initializer implements IInitializer {
	@Override
	public void init(Application application) {
		IResource export = new CharSequenceResource("text/csv", MyDataBase.getInstance().exportDiscounts(),"discounts.csv");
		Application.get().getSharedResources().add("discounts", export);
	}

	@Override
	public void destroy(Application application) {
		
	}
}
