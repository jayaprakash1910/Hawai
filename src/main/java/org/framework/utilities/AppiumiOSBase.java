package org.framework.utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.framework.Base;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumiOSBase extends Base {
	//protected static RemoteWebDriver driver = null;
	
	@SuppressWarnings("rawtypes")
	public static RemoteWebDriver createRealSafariAppiumDriver() throws Throwable {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOSPlatformVersion);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.UDID, "auto");
		capabilities.setCapability("startIWDP", true);
		capabilities.setCapability("webkitResponseTimeout", 120);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "120");

		try {
			PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName);//to get the udid for reporting
			URL url = new URL(serviceUrl);
			driver = new IOSDriver(url, capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10);
		} catch (Exception e) {
			Log4j.info("Error performing Appium test: " + e.getMessage());
			takeScreenshots();
		}
		return driver;
	}
}
