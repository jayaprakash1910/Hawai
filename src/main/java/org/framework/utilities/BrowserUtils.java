package org.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.framework.Base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils extends Base {
	private static Logger Log = Logger.getLogger(BrowserUtils.class.getName());
	public static String currentClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	//protected static AppiumDriver<MobileElement> driver;
	
	public static String getBrowserName() {
		if (deviceType.contains("iOS")) {
			return "Safari";
		} else if (deviceType.equalsIgnoreCase("Android")) {
			return "Chrome";
		} else {
			return PropertiesHelper.readProperties("BrowserName");
		}
	}
	
	public static void quitBrowser() throws Throwable {
		try {
			if (!driver.toString().isEmpty()){
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
	}

	public static String getCurrentURL() throws Throwable {
		String currentURL = null;
		try {
			currentURL = driver.getCurrentUrl();
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return currentURL;
	}

	public static void waitForPageLoad(int time) {
		try {
			new WebDriverWait(driver, time); //provide time in seconds
		} catch (Exception e) {
			Log4j.info(e.getMessage());
		}
	}

	public static void openBrowser() throws Throwable {
		driver.get("");
	}

	public static final String wifi_logout = "http://wifilogin#.xfinity.com/logout.php?cm=$&a=@";
	public static final String File_TestData = "TestData.xlsx";
	public static boolean xwifiFlag = false;
	public static boolean blnExcFlag = false;

	public static int getURLStatus(String currentURL) throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(currentURL).openConnection();
		return connection.getResponseCode();
	}
	
	public static RemoteWebDriver openBrowser(String appURL) throws Throwable {
        String currentOS = DeviceDetails.getOSName();
        DesiredCapabilities capabilities;
        
        if (browserName.equalsIgnoreCase("") || browserName == null)
        	browserName = PropertiesHelper.readProperties("BrowserName");
        else
       
        if (browserName.equalsIgnoreCase("Firefox")) {
               if (currentOS.contains("Windows")) {
                     System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + File.separator
                                   + "lib" + File.separator + "drivers" + File.separator + "geckodriver.exe");
               } else if (currentOS.contains("Mac")) {
                     System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + File.separator
                                   + "lib" + File.separator + "drivers" + File.separator + "geckodriver");
               }
               capabilities = DesiredCapabilities.firefox();
               capabilities.setCapability("marionette", true);
               webDriver = new FirefoxDriver(capabilities);
               webDriver.manage().window().maximize();

        } else if (browserName.equalsIgnoreCase("ie")) {
               File file = new File("Drivers" + File.separator + "IEDriverServer.exe");
               System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
               webDriver = new InternetExplorerDriver();
               webDriver.manage().window().maximize();

        } else if (browserName.equalsIgnoreCase("Chrome")) {
        	
               if (currentOS.contains("Windows")) {
                     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "lib"
                                   + File.separator + "drivers" + File.separator + "chromedriver.exe");
               } else if (currentOS.contains("Mac")) {
                     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "lib"
                                   + File.separator + "drivers" + File.separator + "chromedriver");
               }
               ChromeOptions options = new ChromeOptions();
               options.addArguments("--test-type");
               webDriver = new ChromeDriver(options);
               webDriver.manage().window().maximize();
        }

        driver = (RemoteWebDriver) webDriver;
        try {
			driver.manage().deleteAllCookies();
			// need 30 seconds for testing the 2 hour continuous tests, some times browser won't load on time
			int implicitWaitTime = 30;

			if ("ie".equalsIgnoreCase(browserName)) {
				driver.manage().timeouts().implicitlyWait(implicitWaitTime * 2, TimeUnit.SECONDS);
			} else {
				driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info("May be TimeOut Exception - " + e.getMessage());
		}
        logHelper.info("'" + browserName + "' browser launched successfully!! ");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		if (!appURL.isEmpty()) {
			driver.get(appURL);
			Thread.sleep(500);
		} else {
			driver.get("");
			System.out.println("Driver name is - " + driver.getSessionId());
			if (!(BrowserUtils.getURLStatus(BrowserUtils.getCurrentURL()) == 200)) {
				System.out.println("Browser title - " + driver.getTitle());
				logHelper.info("Failed to launch the " + "'" + browserName + "' browser!! ");
				relaunchRandomURL("");
			} 
		}
        return driver;
 }
	public static void relaunchRandomURL(String appURL) throws Throwable {
		logHelper.info("Trying to Relaunch the Browser");
		if (!appURL.isEmpty()) {
			driver.get(appURL);
		} else {
		driver.get("");
		}
		
		takeScreenshots();
	}
}
