package org.framework.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.framework.Base;

public class PhysicalDeviceDetails extends Base {
	public static String udid;
	public static String deviceConnectDevice;
	
	public static String getUdid(String deviceName) {
		switch (deviceName) {
		
			//Samsung Edge S7
			case "Nexus6":
				udid = "emulator-5554";
				mobileMacId = "TBD";
	            break;
	            
			case "JP":
				udid = "48c4657f";
				mobileMacId = "TBD";
				break;
					
			case "Pixel2":
				udid = "emulator-5556";
				mobileMacId = "1c:56:fe:bb:90:0e";
				break;
				
			case "Android12":
				udid = "988838304a4e5a4e39";
				mobileMacId = "dc:ef:ca:ff:7c:6a";
				break;
				
			//iPhone 7
			case "iPhone":
				udid = "118675D2-2171-4F20-A9F4-824F74B6E195";
				mobileMacId = "TBD";
				break;
				
			//ZTE 
			case "ZTE":
				udid = "329F8368D6C2";
				mobileMacId = "00:db:70:a5:39:dd";
				break;
			}
		return udid;
	}
	
	public static boolean isMobile() {
		return !(deviceType.equalsIgnoreCase("Desktop")) && (deviceName.contains("Android") || deviceName.contains("iPhone"));
	}
	
	public static boolean isTab() {
		return !(deviceType.equalsIgnoreCase("Desktop")) && (deviceName.contains("ATab") || deviceName.contains("iPad"));
	}
	
	@SuppressWarnings("resource")
	public static String getDesktopSerialNumber() throws Exception {
		String serial = "";
		try {
			if (EnvironmentUtils.isWindows()) {
				Process process = Runtime.getRuntime().exec(new String[] {"wmic", "bios", "get", "serialnumber"});
				process.getOutputStream().close();
				Scanner sc = new Scanner(process.getInputStream());
				String property = sc.next();
				serial = sc.next();
				Log4j.info("Windows Desktop " + property + ": " + serial);
				
				return serial;
			} else if (EnvironmentUtils.isMAC()) {
				String st = null;
				if (osName.contains("Mac")) {
					String cmd = "system_profiler SPHardwareDataType | awk '/Serial/ {print $4}'";
					StringBuilder sb = new StringBuilder();
					
					Runtime run = Runtime.getRuntime();
					Process pr = run.exec(cmd);
					pr.waitFor();
					BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
					String line = "";
					
					while ((line=buf.readLine())!=null) {
						sb = sb.append(line).append("\n");
			        	st = sb.toString();
					}
				}
				serial = st;
				serial = serial.substring(serial.indexOf("Serial Number (system):") + 1);
				serial = serial.substring(0,  serial.indexOf("Hardware"));
				serial = serial.substring(serial.indexOf(":") + 1).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		
		return serial;
	}
	
	public static String getDesktopDeviceName() {
		String serial;
		String deviceName = "";
		try {
			serial = getDesktopSerialNumber();
			
			switch (serial) {
				case "5CD62848DR":
					deviceName = "Win02";
					break;
				
				case "5CD6290CJM":
					deviceName = "Win03";
					break;
					
				case "5CD643381V":
					deviceName = "Win05";
					break;
					
				case "LR0AFJGY":
					deviceName = "Win06";
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceName;
	}
	
}
