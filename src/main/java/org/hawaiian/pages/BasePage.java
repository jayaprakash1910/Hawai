package org.hawaiian.pages;

import org.framework.Base;

public class BasePage extends Base {
	
	public static boolean isHawaiianNativeAppOpened() throws Throwable {
		instantiateDriver();
		Thread.sleep(2000);
		takeScreenshots();
		return HomePage.isCheckInButtonVisible();
	}
}
