package org.tamm.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class LabelsGroup extends Panel {
	
	private static final long serialVersionUID = 1L;

	public LabelsGroup(String id) {
		super(id);
		add(new Label("dexter", "Omelette du fromage"));
		//add(new Label("boo", "That's all you can say!"));
		}
}
