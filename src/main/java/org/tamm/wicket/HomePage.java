package org.tamm.wicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.tamm.datefield.DateTimeFieldPage;
import org.tamm.discounts.Index;
import org.tamm.jquery.DefaultWindowPage;
import org.tamm.navigator.Sub1Page;
import org.tamm.panels.PanelsPage;


public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
 
    private List<Integer> list = new ArrayList<Integer>();
    //private int counter = 0;
     
    public HomePage() {
    	
    	Model<Integer> model = new Model<Integer>() {
			private static final long serialVersionUID = -2921734639027013889L;
			private int counter = 0;
            public Integer getObject() {
                return counter++;
            }
        };
    		
		ListView<Integer> listView = new ListView<Integer>("listView", list) {
			private static final long serialVersionUID = 5523271400675876247L;

			@Override
			protected void populateItem(ListItem<Integer> item) {
				Integer value = (Integer)item.getModelObject();
				item.add(new Label("value", value));
			}
		};

		WebMarkupContainer listContainer = new WebMarkupContainer("theContainer");
        listContainer.setOutputMarkupId(true);
        listContainer.add(listView);
        add(listContainer);
		
        final Label label = new Label("counter", model);
        label.setOutputMarkupId(true);
        
		AjaxFallbackLink<Void> link = new AjaxFallbackLink<Void>("increment") {
	        private static final long serialVersionUID = 1L;
	 
	        @Override
	        public void onClick(AjaxRequestTarget target) {
	            
	            if (target != null)
	            {
	            	//counter++;
	            	list.add((int)label.getDefaultModel().getObject());
	            	target.add(listContainer);
	            	target.add(label);
	            }
	        }
	    };
	    add(link);
	    add(label);
	    
	    add(new BookmarkablePageLink<Void>("group_page", GroupingPage.class){
			private static final long serialVersionUID = 1L;
	    });
	    
	    add(new BookmarkablePageLink<Void>("date_page", DateTimeFieldPage.class){
			private static final long serialVersionUID = 1L;
	    });
	    
	    add(new BookmarkablePageLink<Void>("discounts_page", Index.class){
			private static final long serialVersionUID = 1L;
	    });
	    
	    add(new BookmarkablePageLink<Void>("navigator", Sub1Page.class){
			private static final long serialVersionUID = 1L;
	    });
	    
	    add(new BookmarkablePageLink<Void>("panels", PanelsPage.class){
			private static final long serialVersionUID = 1L;
	    });
    }
}