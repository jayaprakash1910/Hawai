package org.framework.utilities;

import org.framework.Base;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class DriverUtils extends Base {
	
	public static RemoteWebDriver getDriver(String driverType) throws Throwable {
		if (driverType.equalsIgnoreCase("iOS") && EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			Log4j.info("Came in virtual ios 1" );
			driver = AppiumiOSBase.createVirtualNativeAppiumDriver();
		} else if (driverType.equalsIgnoreCase("iOS") && !EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			Log4j.info("Came in virtual ios 2");
			driver = AppiumiOSBase.createRealSafariAppiumDriver();
		} else if (driverType.equalsIgnoreCase("Android") && !EnvironmentUtils.isVirtualDevice() && !EnvironmentUtils.isNative()) {
			Log4j.info("Came in virtual ios 3");
			driver = AppiumAndroidBase.createRealChromeAppiumDriver();
		} else if (driverType.equalsIgnoreCase("Desktop")) {
			Log4j.info("Came in virtual ios 4");
			driver = BrowserUtils.openBrowser("");
		} else if (driverType.equalsIgnoreCase("Android") && EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			Log4j.info("Came in virtual ios 5");
			driver = AppiumAndroidBase.createVirtualNativeAppiumDriver(deviceName);
		} else if (driverType.equalsIgnoreCase("Android") && !EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			Log4j.info("Came in virtual ios 6");
			driver = AppiumAndroidBase.createRealNativeAppiumDriver();
		}
		return driver;
	}
	
	public static void instantiateDriver() throws Throwable {
		DriverUtils.getDriver(deviceType);
	}
	
	public static void startAppiumServer() {
		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
	
	public static void stopAppiumServer() {
		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.stop();
	}
}
