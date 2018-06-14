package org.hawaiian.pages;

import org.framework.Base;
import org.framework.utilities.DriverUtils;

public class BasePage extends Base {
	
	public static boolean isHawaiianNativeAppOpened() throws Throwable {
		DriverUtils.instantiateDriver();
		Thread.sleep(2000);
		takeScreenshots();
		return HomePage.isCheckInButtonVisible();
	}
}
