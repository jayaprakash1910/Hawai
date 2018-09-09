package org.proj.tests;

import static org.testng.Assert.assertTrue;

import org.framework.Base;
import org.framework.utilities.BrowserUtils;
import org.testng.annotations.Test;

public class SampleTest extends Base {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
	
	@Test(groups = { "SampleTest" })
    public void launchYahooMail() throws Throwable {
		BrowserUtils.openBrowser("http://www.yahoo.com");
//		logHelper.info("Yahoo Home page is launched");
//		driver.findElementByCssSelector("#uh-signin").click();
//		Thread.sleep(3000);
//		logHelper.info("Yahoo email login page opened");
//		
    }
	
	//@Test(groups = { "SampleTest" })
    public void verifyYahooLogo() throws Throwable {
		BrowserUtils.openBrowser("http://www.yahoo.com");
		assertTrue(driver.findElementByCssSelector("#uh-logo").isDisplayed());
		logHelper.info("Yahoo Logo is visible");
    }

}
