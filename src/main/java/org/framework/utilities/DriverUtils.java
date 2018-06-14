package org.framework.utilities;

import org.framework.Base;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverUtils extends Base {
	
	public static RemoteWebDriver getDriver(String driverType) throws Throwable {
		if (driverType.equalsIgnoreCase("iOS") && !isVirtualDevice() && !isNative()) {
			driver = AppiumiOSBase.createRealSafariAppiumDriver();
		} else if (driverType.equalsIgnoreCase("Android") && !isVirtualDevice() && !isNative()) {
			driver = AppiumAndroidBase.createRealChromeAppiumDriver();
		} else if (driverType.equalsIgnoreCase("Desktop")) {
			driver = BrowserHelper.openBrowser("");
		} else if (driverType.equalsIgnoreCase("Android") && isVirtualDevice() && isNative()) {
			driver = AppiumAndroidBase.createVirtualNativeAppiumDriver(deviceName);
		} else if (driverType.equalsIgnoreCase("Android") && !isVirtualDevice() && isNative()) {
			driver = AppiumAndroidBase.createRealNativeAppiumDriver();
		}
		return driver;
	}
}
