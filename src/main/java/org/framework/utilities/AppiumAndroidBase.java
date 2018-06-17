package org.framework.utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.framework.Base;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class AppiumAndroidBase extends Base {
	
	protected static RemoteWebDriver driver = null;
	
	@SuppressWarnings("rawtypes")
	public static RemoteWebDriver createRealChromeAppiumDriver() throws Throwable {
		Log4j.info("Coming into real chrome ");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", deviceType);
		capabilities.setCapability("deviceName", PhysicalDeviceDetails.deviceName);
		capabilities.setCapability("udid", PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName));
		capabilities.setCapability("newCommandTimeout", 60);
		
		try {
			URL url = new URL(serviceUrl);
			driver = new AndroidDriver(url, capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10);
			BrowserUtils.openBrowser();
		} catch (Exception e) {
			Log4j.info("Error performing Appium test: " + e.getMessage());
			takeScreenshots();
		} 
		return driver;
	}
	
	@SuppressWarnings("rawtypes")
	public static RemoteWebDriver createVirtualNativeAppiumDriver(String userInputDeviceName) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		Log4j.info("Coming into virtual native ");
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", deviceType);
		capabilities.setCapability("deviceName", userInputDeviceName);
		capabilities.setCapability("udid", PhysicalDeviceDetails.getUdid(userInputDeviceName));
		capabilities.setCapability("app", androidAppPath);
		//capabilities.setCapability("appPackage", PropertiesHelper.readProperties("androidAppPackage"));
		//capabilities.setCapability("appActivity", PropertiesHelper.readProperties("androidAppActivity"));
		capabilities.setCapability("avd",userInputDeviceName);
		capabilities.setCapability("newCommandTimeout", 60);
		
		try {
			URL url = new URL(serviceUrl);
			driver = new AndroidDriver(url, capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10);
		} catch (Exception e) {
			Log4j.info("Error performing Appium test: " + e.getMessage());
			takeScreenshots();
		} 
		return driver;
	}
	
	@SuppressWarnings("rawtypes")
	public static RemoteWebDriver createRealNativeAppiumDriver() throws Throwable {
		Log4j.info("Coming into real native ");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", deviceType);
		capabilities.setCapability("deviceName", PhysicalDeviceDetails.deviceName);
		capabilities.setCapability("udid", PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName));
		capabilities.setCapability("app", androidAppPath);
		//capabilities.setCapability("appPackage", PropertiesHelper.readProperties("androidAppPackage"));
		//capabilities.setCapability("appActivity", PropertiesHelper.readProperties("androidAppActivity"));
		capabilities.setCapability("newCommandTimeout", 60);
		
		try {
			URL url = new URL(serviceUrl);
			driver = new AndroidDriver(url, capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10);
		} catch (Exception e) {
			Log4j.info("Error performing Appium test: " + e.getMessage());
			takeScreenshots();
		} 
		return driver;
	}
}