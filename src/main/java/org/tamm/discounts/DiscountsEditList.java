package org.tamm.discounts;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.tamm.datefield.DateTimeField;
import org.apache.wicket.behavior.SimpleAttributeModifier;

public final class DiscountsEditList extends Panel {
	private List<Discount> discounts;

	public DiscountsEditList(String id) {
		super(id);
		Form form = new Form("form");
		add(form);

		Button newButton = new Button("newButton") {
			@Override
			public void onSubmit() {
				DiscountsEditList.this.replaceWith(new NewDiscountForm(
						DiscountsEditList.this.getId()));
			}
		};
		form.add(newButton);

		Button saveButton = new Button("saveButton") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				MyDataBase.getInstance().update(discounts);
				info("discounts updated");
			}
		};
		form.add(saveButton);

		form.add(new FeedbackPanel("feedback"));
		RefreshingView discountsView = new RefreshingView("discounts") {
			@Override
			protected Iterator getItemModels() {
				if (discounts == null) {
					discounts = MyDataBase.getInstance().listDiscounts();
				}

				ModelIteratorAdapter adapter = new ModelIteratorAdapter(
						discounts.iterator()) {

					@Override
					protected IModel model(Object object) {
						return EqualsDecorator
								.decorate(new CompoundPropertyModel(
										(Discount) object));
					}
				};
				return adapter;
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("name"));
				item.add(new PercentageField("discount"));
				item.add(new RequiredTextField("description"));
				item.add(new DateTimeField("from"));
				item.add(new DateTimeField("until"));
				final Discount discount = (Discount) item.getModelObject();

				final Link removeLink = new Link("remove") {
					@Override
					public void onClick() {
						MyDataBase.getInstance().remove(discount);
					}
				};

				/*PackageResourceReference resourceReference = new PackageResourceReference(getClass(), "remove_icon.gif");
				Image img  = new Image("icon", resourceReference);
				removeLink.add(img);*/
				
				item.add(removeLink);
				/*
				 * removeLink.add(new SimpleAttributeModifier("onclick",
				 * "if(!confirm('remove discount for " + discount.getName() +
				 * " ?')) return false;"));
				 */
			}

		};
		discountsView.setItemReuseStrategy(ReuseIfModelsEqualStrategy
		        .getInstance());
		    form.add(discountsView);
	}
}
