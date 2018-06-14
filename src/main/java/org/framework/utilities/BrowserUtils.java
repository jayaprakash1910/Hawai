package org.framework.utilities;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.framework.Base;
import org.framework.webobjects.WebObjects;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils extends Base {
	private static Logger Log = Logger.getLogger(BrowserUtils.class.getName());
	public static String currentClassName = MethodHandles.lookup().lookupClass().getSimpleName();
	//protected static AppiumDriver<MobileElement> driver;
	
	public static String getBrowser() {
		String browser = null;
		try {
			browser = browserName;
			logHelper.info("Browser selected: " + browser);
			
			switch (browser) {
			case "Chrome":
				logHelper.info("Chrome browser is instantiated!");
				break;
			case "IE":
				break;
			case "Firefox":
				logHelper.info("Firefox browser is instantiated!");
				break;
			default:
				logHelper.info("Firefox browser is instantiated by default!");
				break;
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return browser;
	}
	
	public static String getBrowserName() {
		if (deviceType.contains("iOS")) {
			return "Safari";
		} else if (deviceType.equalsIgnoreCase("Android")) {
			return "Chrome";
		} else {
			return PropertiesHelper.readProperties("BrowserName");
		}
	}
	
	public static void closeBrowser() throws Throwable{
		try {
			if (!driver.toString().isEmpty()){
				driver.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
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

	public static String getRandomURL() throws Throwable {
		String url = null;
		try {
			url = StringUtils.getRandomPublicURL();
		//	logHelper.info("Public URL launched is: '" + url + "'");
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}

		return url;
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

	public static void waitForElementToLoad(WebElement element, long time) {
		element = (new WebDriverWait(driver, time)).until(ExpectedConditions.visibilityOf(element));
	}

	public static void openNewTab() throws Throwable {
		int vkControl = KeyEvent.VK_CONTROL;
		Robot robot = new Robot();
		robot.keyPress(vkControl);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(vkControl);
		robot.keyRelease(KeyEvent.VK_T);
	}
	
	public static void switchToFrame(String value) {
		driver.switchTo().frame(value);
	}
	
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	public static void openBrowser() throws Throwable {
		driver.get(getRandomURL());
	}

	public static String getPageSourceContent() throws Throwable {
		try {
			return driver.getPageSource();
		} catch(Exception e) {
			e.printStackTrace();
			Log.info(e.getMessage());
		}
		return null;
	}
	
	public static final String wifi_logout = "http://wifilogin#.xfinity.com/logout.php?cm=$&a=@";
	public static final String File_TestData = "TestData.xlsx";
	public static boolean xwifiFlag = false;
	public static boolean blnExcFlag = false;

	public static boolean checkBrowsing() throws Throwable {
		logHelper.info("Check if user is able to browse");
		boolean flag = false;

		try {
			driver.get("http://www.google.com");
			String urlText = driver.getCurrentUrl();
			if (urlText.contains("https://www.google.com")) {
				takeScreenshots();
				
				WebElement textSearch = WebObjects.getWebElement("//*[@id='lst-ib']");
				WebObjects.setText("//*[@id='lst-ib']", "cnn");
				textSearch.sendKeys(Keys.ENTER);
				String actualSearchText = WebObjects.getText("//*[@id='rso']//div/cite[contains(text(), 'www.cnn.com')]").trim();
				flag = actualSearchText.contains("www.cnn.com");
				takeScreenshots();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logHelper.info(e.getMessage());
		}
		return flag;
	}

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
			driver.get(BrowserUtils.getRandomURL());
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
		driver.get(BrowserUtils.getRandomURL());
		}
		
		takeScreenshots();
	}
}
