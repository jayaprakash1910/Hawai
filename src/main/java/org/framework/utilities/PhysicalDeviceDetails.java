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
				
			//iPhone v7
			case "iPhone01":
				udid = "d4f096bd2e3adac528e7074deac42ec761d0c260";
				mobileMacId = "78:88:6d:72:e4:cb";
				break;
				
			//iPhone v7
			case "iPhone02":
				udid = "13a05502f35fa938f06e289136a3034932e0ea95";
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
	
	public static boolean uninstallAppiumSettings(String udid) {
		String osName = System.getProperty("os.name");
		boolean result = false;
		try { 
			if (osName.contains("Windows")) {
				Process p = Runtime.getRuntime().exec(new String[]{adbUninstallSettingsBat, udid});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        if (st != null) {
		        	result = st.trim().toLowerCase().contains("success");
		        	if (result) {
		        		Log4j.info("ADB uninstall settings status: 'Success'");
		        		System.out.println("ADB uninstall settings status: 'Success'");
		        	} else {
		        		Log4j.info("Failed to uninstall adb settings");
			        	System.out.println("Failed to uninstall adb settings");
			        }
		        } else {
		        	System.out.println("Failed to uninstall adb settings");
		        }
			} else if (osName.contains("Mac")) {
				StringBuilder sb = new StringBuilder();
				String line = "";
				String str = null, st = null;
				
				Runtime run = Runtime.getRuntime();
				Process pr = run.exec("whoami");
				pr.waitFor();
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				
				while ((line=buf.readLine())!=null) {
					sb = sb.append(line).append("\n");
		        	str = sb.toString();
				}
				
				str = str.trim();
				str = File.separator + "Users" + File.separator + str + File.separator + "Library" + File.separator + "Android" + File.separator + "sdk" + File.separator;
				buf.close();
				
				Process p = Runtime.getRuntime().exec(str + File.separator + "platform-tools" + File.separator + "adb -s " + udid + " uninstall io.appium.settings");
				
				StringBuilder sbr = new StringBuilder();
				String line2 = "";
				p.waitFor();
				
				BufferedReader buf2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				while ((line2=buf2.readLine())!=null) {
					sbr = sbr.append(line2).append("\n");
		        	st = sbr.toString();
				}
				
				st = st.trim();
				
				if (st != null) {
		        	result = st.trim().toLowerCase().contains("success");
		        	if (result) {
		        		Log4j.info("ADB uninstall settings status: 'Success'");
		        		System.out.println("ADB uninstall settings status: 'Success'");
		        	} else {
		        		Log4j.info("Failed to uninstall adb settings");
			        	System.out.println("Failed to uninstall adb settings");
			        }
		        } else {
		        	Log4j.info("Failed to uninstall adb settings");
		        	System.out.println("Failed to uninstall adb settings");
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean uninstallAppiumUnlock(String udid) {
		String osName = System.getProperty("os.name");
		boolean result = false;
		try { 
			if (osName.contains("Windows")) {
				Process p = Runtime.getRuntime().exec(new String[]{adbUninstallUnlockBat, udid});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        if (st != null) {
		        	result = st.trim().toLowerCase().contains("success");
		        	if (result) {
		        		Log4j.info("ADB uninstall unlock status: 'Success'");
		        		System.out.println("ADB uninstall unlock status: 'Success'");
		        	} else {
		        		Log4j.info("Failed to uninstall adb unlock!!");
			        	System.out.println("Failed to uninstall adb unlock!!");
			        }
		        } else {
		        	Log4j.info("Failed to uninstall adb unlock!!");
		        	System.out.println("Failed to uninstall adb unlock!!");
		        }
			}  else if (osName.contains("Mac")) {
				StringBuilder sb = new StringBuilder();
				String line = "";
				String str = null, st = null;
				
				Runtime run = Runtime.getRuntime();
				Process pr = run.exec("whoami");
				pr.waitFor();
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				
				while ((line=buf.readLine())!=null) {
					sb = sb.append(line).append("\n");
		        	str = sb.toString();
				}
				
				str = str.trim();
				str = File.separator + "Users" + File.separator + str + File.separator + "Library" + File.separator + "Android" + File.separator + "sdk" + File.separator;
				buf.close();
				
				Process p = Runtime.getRuntime().exec(str + File.separator + "platform-tools" + File.separator + "adb -s " + udid + " uninstall io.appium.unlock");
				
				StringBuilder sbr = new StringBuilder();
				String line2 = "";
				p.waitFor();
				
				BufferedReader buf2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				while ((line2=buf2.readLine())!=null) {
					sbr = sbr.append(line2).append("\n");
		        	st = sbr.toString();
				}
				
				st = st.trim();
				
				if (st != null) {
		        	result = st.trim().toLowerCase().contains("success");
		        	if (result) {
		        		Log4j.info("ADB uninstall unlock status: 'Success'");
		        		System.out.println("ADB uninstall unlock status: 'Success'");
		        	} else {
		        		Log4j.info("Failed to uninstall adb unlock!!");
			        	System.out.println("Failed to uninstall adb unlock!!");
			        }
		        } else {
		        	Log4j.info("Failed to uninstall adb unlock!!");
		        	System.out.println("Failed to uninstall adb unlock!!");
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean resetAndroidDeviceSettings(String udid) {
		boolean result = false;
		try {
			if (uninstallAppiumSettings(udid)) {
				Thread.sleep(3000);
				if (uninstallAppiumUnlock(udid)) {
					result = true;
					Log4j.info("Successfully finished resetting Android device!!");
					System.out.println("Successfully finished resetting Android device!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
	
	public static String getAndroidDeviceOSVersion() throws Throwable {
		String st = null, str = null;
		
		try { 
			if (EnvironmentUtils.isWindows()) {
				Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "adb -s " + PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName) + " shell getprop ro.build.version.release"});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
			} else if (osName.contains("Mac")) {
				StringBuilder sb = new StringBuilder();
				String line = "";
				
				Runtime run = Runtime.getRuntime();
				Process pr = run.exec("whoami");
				pr.waitFor();
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				
				while ((line=buf.readLine())!=null) {
					sb = sb.append(line).append("\n");
		        	str = sb.toString();
				}
				
				str = str.trim();
				str = File.separator + "Users" + File.separator + str + File.separator + "Library" + File.separator + "Android" + File.separator + "sdk" + File.separator;
				buf.close();
				
				Process p = Runtime.getRuntime().exec(str + File.separator + "platform-tools" + File.separator + "adb -s " + PhysicalDeviceDetails.getUdid(PhysicalDeviceDetails.deviceName) + " shell getprop ro.build.version.release");
				
				StringBuilder sbr = new StringBuilder();
				String line2 = "";
				p.waitFor();
				
				BufferedReader buf2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				while ((line2=buf2.readLine())!=null) {
					sbr = sbr.append(line2).append("\n");
		        	st = sb.toString();
				}
				
				st = st.trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st.trim();
	}
}
