package org.tamm.wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;

public class GroupingPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public class LabelsFragment extends Fragment {
		private static final long serialVersionUID = 1L;

		public LabelsFragment(String id) {
			super(id, "fragment", GroupingPage.this);
			add(new Label("foo", "FOO"));
		}
	}

	public GroupingPage() {

		//LabelsGroup group = new LabelsGroup("group");
		
		LabelsFragment group = new LabelsFragment("group");
			
		group.setOutputMarkupPlaceholderTag(true);
		add(group);

		add(new AjaxFallbackLink<Void>("link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				group.setVisible(!group.isVisible());
				if (target != null) {
					target.add(group);
				}
			}
		});
	}
}
