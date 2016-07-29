package org.tamm.discounts;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public final class NewDiscountForm extends Panel {

	public NewDiscountForm(String id) {

		super(id);
		final Form form = new Form("form", new CompoundPropertyModel(
				new Discount()));
		add(form);
		form.add(new DropDownChoice("name", MyDataBase.getInstance().listCheeses()).setRequired(true));
		form.add(new PercentageField("discount"));
		form.add(new TextField("description").setRequired(true));
		form.add(new FeedbackPanel("feedback"));

		form.add(new Button("saveButton") {
			@Override
			public void onSubmit() {
				Discount discount = (Discount) form.getModelObject();
				MyDataBase.getInstance().add(discount);
				DiscountsPanel discountsPanel = (DiscountsPanel) NewDiscountForm.this
						.getParent();
				discountsPanel.info("saved new discount " + discount);
				discountsPanel.setContentPanel();
			}
		});

		Button cancelButton = new Button("cancelButton") {
			@Override
			public void onSubmit() {
				((DiscountsPanel) NewDiscountForm.this.getParent())
						.setContentPanel();
			}
		};
		form.add(cancelButton);
		cancelButton.setDefaultFormProcessing(false);
	}
}
