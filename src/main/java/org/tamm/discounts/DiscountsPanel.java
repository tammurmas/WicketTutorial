package org.tamm.discounts;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.resource.CharSequenceResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;

public class DiscountsPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private boolean inEditMode = false;

	public DiscountsPanel(String id) {
		super(id);
		add(new DiscountsList("content"));
		final Link<Discount> modeLink = new Link<Discount>("modeLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				inEditMode = !inEditMode;
				setContentPanel();
			}
		};

		add(modeLink);
		modeLink.add(new Label("linkLabel",
				new AbstractReadOnlyModel<Object>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object getObject() {
						return inEditMode ? "[display]" : "[edit]";
					}
				}));
		
		ResourceReference ref = new SharedResourceReference("discounts");
		add(new ResourceLink<IResource>("exportLink", ref));
		
		//add(new ResourceLink<Object>("exportLink", export));
	}

	void setContentPanel() {
		if (inEditMode) {
			addOrReplace(new DiscountsEditList("content"));
			// System.out.println("In edit mode now!");
		} else {
			// System.out.println("In readonly mode now!");
			addOrReplace(new DiscountsList("content"));
		}
	}
}
