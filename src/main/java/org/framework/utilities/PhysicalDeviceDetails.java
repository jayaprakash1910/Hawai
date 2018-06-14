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
	            break;
	            
			case "JP":
				udid = "48c4657f";
				mobileMacId = "TBD";
				break;
					
			case "Pixel2":
				udid = "emulator-5556";
				mobileMacId = "1c:56:fe:bb:90:0e";
				break;
				
			//Samsung Galaxy S7
			case "Android07":
				udid = "ce0416041a1bcb2302";
				mobileMacId = "ac:5f:3e:ea:e4:68";
				break;	
				
			//Google Nexus 6P
			case "Android08":
				udid = "01822a3d1b6a9db5";
				mobileMacId = "00:34:da:8d:57:f5";
				break;
				
			//Google Nexus 5x
			case "Android09":
				udid = "84B7N16128001516";
				mobileMacId = "1c:67:58:49:3e:fc";
				break;

			//Google Pixel
			case "Android10":
				udid = "FA71J0302902";
				mobileMacId = "ac:37:43:a8:4c:4c";
				break;
				
			case "Android12":
				udid = "988838304a4e5a4e39";
				mobileMacId = "dc:ef:ca:ff:7c:6a";
				break;
				
			//Samsung S7 Edge
			case "Android13":
				udid = "ce01171163976e0b05";
				mobileMacId = "b0:72:bf:4e:7f:9b";
				break;
				
			//Samsung Galaxy Tab (old)
			case "ATab04":
				udid = "3200178da7c5a000";
				mobileMacId = "b0:df:3a:0c:8e:c0";
				break;
				
			//Asus GenPad 8.0
			case "ATab05":
				udid = "G9NPCX081937296";
				mobileMacId = "70:8b:cd:96:33:f9";
				break;
				
			//Samsung Galaxy Tab (new)
			case "ATab06":
				udid = "6b7c19281d93da8b";
				mobileMacId = "a8:81:95:4e:9a:c8";
				break;
				
			//Asus GenPad 8.0
			case "ATab07":
				udid = "G9NPCX082008Z6K";
				mobileMacId = "70:8b:cd:96:34:87";
				break;
			
			//Kindle Fire Tab
			case "ATab08":
				udid  = "G0W0H40465330SHC";
				mobileMacId = "68:37:e9:f0:90:1d";
				break;

			//Samsung Galaxy Tab A
			case "ATab09":
				udid = "79defef05f6da3a6";
				mobileMacId = "a4:6c:f1:dd:43:37";
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
				
			case "iPhone04":
				udid = "F19MWFLBFF9R";
				mobileMacId = "TBD";
				break;
				
			//iPhone v8
			case "iPhone05":
				udid = "0cc85b01b9cb7ae7d1e96e65508299ab2307c24a";
				mobileMacId = "6c:4d:73:b9:82:58";
				break;
			
			case "iPadAir":
				udid = "2c71591fca7ab648f9e8726fdacb43c9f1185b08";
				mobileMacId = "98:01:a7:3d:32:81";
				break;
				
			case "iPad01":
				udid = "7ca71775a2d010273774e6e3ca5dc2a866a57a75";
				mobileMacId = "c8:f6:50:e6:d2:a7";
				break;
				
			}
		return udid;
	}
	
	public static String getDeviceMACAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String getDeviceUDID() throws Throwable {
		String osName = System.getProperty("os.name");
		String udidValue = null;
		try { 
			if (osName.contains("Windows")) {
				Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "adb devices"});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        String[] str = st.split("\n");
		        String[] udid = null;
		        for(int i = 0; i < str.length; i++) {
		        	if(str.length > 1) {
		        		udid = str[1].split("device");
		        	}
		    	}
		        
		        for (int j = 0; j < udid.length; j++) {
        			if(udid[j].trim() != "" || udid[j].trim() != null) {
        				udidValue = udid[j].trim();
        				break;
        			}
        		}
			} else if (osName.contains("Mac")) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return udidValue;
	}
	
	@SuppressWarnings("unused")
	public static String getAndroidWiFiMACAddress() throws Throwable {
		String osName = System.getProperty("os.name");
		String udidValue = getDeviceUDID();
		String wifiMACAddress = null;
		
		try { 
			if (osName.contains("Windows")) {
				Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "adb -s " + udidValue + " shell ip addr show wlan0"});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        String[] str = st.split("\n");
		        String[] macAddress = null;
		        for(int i = 0; i < str.length; i++) {
		        	if(str[i].contains("ether")) {
		        		macAddress = str[i].split("ether");
		        		break;
		        	}
		    	}
		        
		        for (int j = 0; j < macAddress.length; j++) {
        			if ((macAddress[j].trim() != "" || macAddress[j].trim() != null) && (macAddress[j].length() > 0)) {
        				if(macAddress[j].trim().contains("ff:ff:ff:ff:ff:ff")) {
        					wifiMACAddress = macAddress[j].trim().substring(0, Math.min(macAddress[j].trim().length(), 17));
        				}
        			}
        		}
			} else if (osName.contains("Mac")) {
				Process p = Runtime.getRuntime().exec(new String[]{"adb -s " + udidValue + " shell ip addr show wlan0  | grep 'link/ether '| cut -d' ' -f6"});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        String[] str = st.split("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wifiMACAddress;
	}
	
	public static String getAndroidSerialNumber() throws Throwable {
		String osName = System.getProperty("os.name");
		String udidValue = getDeviceUDID();
		String serialNumber = null;
		
		try { 
			if (osName.contains("Windows")) {
				Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "adb -s " + udidValue + " getprop ril.serialnumber"});
				StringBuilder sb = new StringBuilder();
		        
		        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		        String line, st = null; 
		        while((line = reader.readLine()) != null) { 
		        	sb = sb.append(line).append("\n");
		        	st = sb.toString();
		        } 
		        serialNumber = st.trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serialNumber;
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
			if (isWindows()) {
				Process process = Runtime.getRuntime().exec(new String[] {"wmic", "bios", "get", "serialnumber"});
				process.getOutputStream().close();
				Scanner sc = new Scanner(process.getInputStream());
				String property = sc.next();
				serial = sc.next();
				Log4j.info("Windows Desktop " + property + ": " + serial);
				
				return serial;
			} else if (isMAC()) {
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
			if (isWindows()) {
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
