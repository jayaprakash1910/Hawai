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
		System.out.println("First Line");
		BrowserUtils.openBrowser("http://www.yahoo.com");
//		driver.findElementByCssSelector("#uh-signin").click();
//		Thread.sleep(3000);
		System.out.println("First Line");
    }
	
	@Test(groups = { "SampleTest" })
    public void verifyYahooLogo() throws Throwable {
		System.out.println("First Line");
		BrowserUtils.openBrowser("http://www.yahoo.com");
		//assertTrue(driver.findElementByCssSelector("#uh-logo").isDisplayed());
		System.out.println("First Line");
	}
	

}
