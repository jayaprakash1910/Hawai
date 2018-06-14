package org.framework.utilities;

import java.io.File;

import org.framework.Base;

public class EnvironmentUtils extends Base {
	
	public static boolean isNative() {
		if (System.getProperty("native") != null) {
			return System.getProperty("native").equalsIgnoreCase("yes");
		}
		return PropertiesHelper.readProperties("native").equalsIgnoreCase("yes");
	}
	
	public static String getExecutionDeviceType() {
		if (System.getProperty("deviceType") != null) {
			String deviceType = System.getProperty("deviceType");
			executeType = "maven";
			if (deviceType.contains("iOS")) {
				return "iOS";
			} else if (deviceType.contains("Android")) {
				return "Android";
			} else if (deviceType.contains("Desktop")) {
				return "Desktop";
			} 
		} else {
			executeType = "local";
		}
		return PropertiesHelper.readProperties("DeviceType");
	}
	
	public static String getDeviceName() {
		try {
			if (System.getProperty("deviceName") != null) {
				return System.getProperty("deviceName");
			} else if (deviceType.equalsIgnoreCase("Desktop")) {
					return PhysicalDeviceDetails.getDesktopDeviceName();
				}
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return PropertiesHelper.readProperties("DeviceName");
	}
	
	public static String getRemoteIp() {
		if (System.getProperty("remoteIp") != null) {
			return System.getProperty("remoteIp");
		}
		return PropertiesHelper.readProperties("RemoteIP");
	}
	
	public static String getEnvironmentName() {
		if (System.getProperty("environment") != null) {
			return System.getProperty("environment");
		} else {
			return PropertiesHelper.readProperties("Environment");
		}
	}
	
	public static boolean isWindows() throws Exception {
		osName = DeviceDetails.getOSName();
		return osName.toLowerCase().contains("windows");
	}
	
	public static boolean isMAC() throws Exception {
		osName = DeviceDetails.getOSName();
		return osName.toLowerCase().contains("mac");
	}
	
	public static boolean isVirtualDevice() {
		if (System.getProperty("virtualdevice") != null) {
			return System.getProperty("virtualdevice").equalsIgnoreCase("yes");
		} 
		return PropertiesHelper.readProperties("virtualdevice").equalsIgnoreCase("yes");
	}
	
	public static String getPageLocatorPath() throws Throwable {
		return System.getProperty("user.dir")+ File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator + "comcast" + File.separator + "MobileWiFi" +  File.separator + "pageLocator" + File.separator ;
	}

}
