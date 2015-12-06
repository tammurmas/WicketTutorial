package org.tamm.wicket;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
		
	}
	
	@Test
	public void linkClickTest(){
		//start and render the test page
		tester.startPage(HomePage.class);
		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
		//simulate a click on bookmarkable link
		tester.clickLink("group_page");
		//assert rendered label
		tester.assertRenderedPage(GroupingPage.class);	
	}
	
	@Test
	public void ajaxLinkClickTest(){
		//start and render the test page
		tester.startPage(HomePage.class);
		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
		
		tester.executeAjaxEvent("increment", "click");
		tester.assertComponentOnAjaxResponse("counter");
	}
}
