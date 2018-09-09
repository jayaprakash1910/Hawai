package org.framework.utilities;

import org.apache.log4j.Logger;
import org.framework.Base;

public class DeviceDetails extends Base {
	private static Logger Log = Logger.getLogger(DeviceDetails.class.getName());

	public static String getOSName() {
		String osName = System.getProperty("os.name");
		return osName;
	}

	public static String getOSVersion() throws Throwable {
		String osVersion = "";
		if (deviceType.equalsIgnoreCase("Desktop")) {
			osVersion = System.getProperty("os.version");
			return osVersion;
		} else if (deviceType.equalsIgnoreCase("Android")) {
		//	osVersion = PhysicalDeviceDetails.getAndroidDeviceOSVersion();
		} else if (deviceType.equalsIgnoreCase("iOS")) { 
			osVersion = iOSPlatformVersion;
		}
		return osVersion;
	}
}
