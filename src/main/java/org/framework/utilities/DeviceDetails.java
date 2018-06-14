package org.framework.utilities;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.framework.Base;

public class DeviceDetails extends Base {
	private static Logger Log = Logger.getLogger(DeviceDetails.class.getName());

	public static String getSystemUser() {
		String sysUserName = System.getProperty("user.name");
		return sysUserName;
	}

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
			osVersion = PhysicalDeviceDetails.getAndroidDeviceOSVersion();
		} else if (deviceType.equalsIgnoreCase("iOS")) { 
			osVersion = iOSPlatformVersion;
		}
		return osVersion;
	}

	public static String getDeviceMACAddress() {
		byte[] mac = null;
		InetAddress ip;
		StringBuilder sb = null;

		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			mac = network.getHardwareAddress();
			sb = new StringBuilder();
			if (mac != null) {
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}
				return sb.toString();
			}
			else {
				Log.info("WiFi is not connected!");
				Log.info("Execution Terminated!!");
				System.exit(0);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
			Log.info(e.getMessage());
			
		} catch (SocketException e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
		return null;
	}
}
