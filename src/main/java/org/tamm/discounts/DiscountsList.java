package org.tamm.discounts;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class DiscountsList extends Panel {
	public DiscountsList(String id) {
		super(id);
		add(new RefreshingView("discounts") {
			@Override
			protected Iterator getItemModels() {
				return new ModelIteratorAdapter(MyDataBase.getInstance()
						.listDiscounts().iterator()) {
					@Override
					protected IModel model(Object object) {
						return new CompoundPropertyModel((Discount) object);
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("name"));
				item.add(new PercentLabel("discount"));
				item.add(new Label("description"));
				item.add(new DateFmtLabel("until"));
			}

		});
	}
}
