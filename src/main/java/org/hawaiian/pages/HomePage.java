package org.hawaiian.pages;

import java.lang.invoke.MethodHandles;

import org.framework.utilities.BrowserUtils;
import org.framework.utilities.PropertiesHelper;
import org.framework.webobjects.WebObjects;

public class HomePage extends BasePage {
	
	public static String currentClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	public static String checkinButton = PropertiesHelper.instantiateProperties(currentClassName, "checkinButton");
	
	public static boolean isCheckInButtonVisible() {
		logHelper.info("Checking Check in button visible");
		BrowserUtils.waitForPageLoad(5);
		return WebObjects.isVisible(checkinButton);
	}
	
	public static void clickCheckInButton() throws Throwable {
		logHelper.info("Clicking Check in button");
		WebObjects.click(checkinButton);
		Thread.sleep(3000);
		takeScreenshots();
	}

}
