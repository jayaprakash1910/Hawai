package org.framework.utilities;

import org.framework.Base;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class DriverUtils extends Base {
	
	public static RemoteWebDriver getDriver(String driverType) throws Throwable {
		if (driverType.equalsIgnoreCase("iOS") && EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			driver = AppiumiOSBase.createVirtualNativeAppiumDriver();
		} else if (driverType.equalsIgnoreCase("iOS") && !EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			driver = AppiumiOSBase.createRealSafariAppiumDriver();
		} else if (driverType.equalsIgnoreCase("Android") && !EnvironmentUtils.isVirtualDevice() && !EnvironmentUtils.isNative()) {
			driver = AppiumAndroidBase.createRealChromeAppiumDriver();
		} else if (driverType.equalsIgnoreCase("Desktop")) {
			driver = BrowserUtils.openBrowser("");
		} else if (driverType.equalsIgnoreCase("Android") && EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
			driver = AppiumAndroidBase.createVirtualNativeAppiumDriver(deviceName);
		} else if (driverType.equalsIgnoreCase("Android") && !EnvironmentUtils.isVirtualDevice() && EnvironmentUtils.isNative()) {
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
	
	public static void startEmulator() {
		if (System.getProperty("deviceType") != null) {
			if (EnvironmentUtils.isVirtualDevice() && System.getProperty("deviceType").equalsIgnoreCase("android")) {
				//AndroidAppiumBase.startEmulatorBatchFile(); //correct this name - call the method
			}
		} else if (EnvironmentUtils.isVirtualDevice() && PropertiesHelper.readProperties("deviceType").equalsIgnoreCase("android")) {
			//AndroidAppiumBase.startEmulatorBatchFile(); //correct this name - call the method
		}
	}
	
	public static void stopEmulator() {
		if (System.getProperty("deviceType").equalsIgnoreCase("Android") && EnvironmentUtils.isVirtualDevice()) {
			//call your emulator stopping method using batch command that you have written
		}
	}
}
