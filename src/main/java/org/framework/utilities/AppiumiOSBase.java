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
	
	@SuppressWarnings("rawtypes")
	public static RemoteWebDriver createRealSafariAppiumDriver() throws Throwable {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.APP, "/Users/jgadel200/Documents/workspace/HawaiianAirlines.app");//required for sim
		//capabilities.setCapability("bundleId", "com.hawaiianairlines.newapp");
		//capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");//required for sim
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOSPlatformVersion);//required for sim
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);//required for sim
		//capabilities.setCapability(MobileCapabilityType.UDID, "auto");
		capabilities.setCapability(MobileCapabilityType.UDID, "118675D2-2171-4F20-A9F4-824F74B6E195");//required for sim
		capabilities.setCapability("startIWDP", true);//required for sim
		capabilities.setCapability("webkitResponseTimeout", 120);//optional for sim
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "120");//optional for sim
		//capabilities.setCapability("noReset", true);
		//capabilities.setCapability("showIOSLog", true);
		
		try {
			PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName);//to get the udid for reporting
			URL url = new URL(serviceUrl);
			driver = new IOSDriver(url, capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10);
		} catch (Exception e) {
			Log4j.info("Error performing Appium test: " + e.getMessage());
			takeScreenshots();
		}
		return driver;
	}
	
	@SuppressWarnings("rawtypes")
	public static RemoteWebDriver createVirtualNativeAppiumDriver() throws Throwable {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APP, "/Users/jgadel200/Documents/workspace/HawaiianAirlines.app");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, iOSPlatformVersion);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability("startIWDP", true);
		capabilities.setCapability(MobileCapabilityType.UDID, PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName));
		capabilities.setCapability("webkitResponseTimeout", 120);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "120");
		
		try {
			PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName);//to get the udid for reporting
			URL url = new URL(serviceUrl);
			driver = new IOSDriver(url, capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10);
		} catch (Exception e) {
			Log4j.info("Error performing Appium test: " + e.getMessage());
			takeScreenshots();
		}
		return driver;
	}
}
