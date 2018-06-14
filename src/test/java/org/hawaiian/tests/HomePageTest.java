package org.hawaiian.tests;

import org.hawaiian.pages.BasePage;
import org.hawaiian.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends HomePage {

	@Test(groups = { "Nexus" })
    public void verifyCheckinPageOnNexus() throws Throwable {
		logHelper.info("Launching the Hawaiian native app in Nexus Phone");
		hAssert.assertTrue(BasePage.isHawaiianNativeAppOpened(), "Home page did not open", "Home page opened successfully");
		clickCheckInButton();
    }
	
	@Test(groups = { "Pixel" })
    public void verifyCheckinPageOnPixel() throws Throwable {
		logHelper.info("Launching the Hawaiian native app in Pixel Phone");
		hAssert.assertTrue(BasePage.isHawaiianNativeAppOpened(), "Home page did not open", "Home page opened successfully");
		clickCheckInButton();
	}
}
