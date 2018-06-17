package org.hawaiian.tests;

import org.framework.utilities.AppiumiOSBase;
import org.hawaiian.pages.BasePage;
import org.hawaiian.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends HomePage {

	@Test(groups = { "Nexus" })
    public void verifyCheckinPageOnNexus() throws Throwable {
		logHelper.info("Launching the Hawaiian native app in Nexus Phone");
		logHelper.info("device type " + System.getProperty("deviceType"));
		logHelper.info("device name " + System.getProperty("deviceName"));
		logHelper.info("native" + System.getProperty("native"));
		logHelper.info("virtual " + System.getProperty("virtualdevice"));
		
		hAssert.assertTrue(BasePage.isHawaiianNativeAppOpened(), "Home page did not open", "Home page opened successfully");
		clickCheckInButton();
    }
	
	//@Test(groups = { "Pixel" })
    public void verifyCheckinPageOnPixel() throws Throwable {
		logHelper.info("Launching the Hawaiian native app in Pixel Phone");
		hAssert.assertTrue(BasePage.isHawaiianNativeAppOpened(), "Home page did not open", "Home page opened successfully");
		clickCheckInButton();
	}
	
	
	//@Test(groups = { "iphone7sim" })
    public void verifyCheckingiossimulator() throws Throwable {
//		logHelper.info("Launching the Hawaiian native app in Pixel Phone");
//		hAssert.assertTrue(BasePage.isHawaiianNativeAppOpened(), "Home page did not open", "Home page opened successfully");
		AppiumiOSBase.createRealSafariAppiumDriver();
	}
}
