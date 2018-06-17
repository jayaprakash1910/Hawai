package org.framework;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.framework.reports.Accessories;
import org.framework.reports.CustomAssertion;
import org.framework.reports.HardAssertion;
import org.framework.reports.LoggerHelper;
import org.framework.utilities.BrowserUtils;
import org.framework.utilities.DeviceDetails;
import org.framework.utilities.EnvironmentUtils;
import org.framework.utilities.FileUtilityHelper;
import org.framework.utilities.ScreenshotUtils;
import org.framework.utilities.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	public static Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
	public static LoggerHelper logHelper = new LoggerHelper();
	public static CustomAssertion Assert = new CustomAssertion();
	public static HardAssertion hAssert = new HardAssertion();
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static SoftAssert softAssert;
	public static Method method;
	public static RemoteWebDriver driver = null;
	public static WebDriver webDriver;
	public static DesiredCapabilities capabilities;
	public static String timeStamp = Accessories.timeStamp().replace(" ", "_").replace(":", "_").replace(".", "_");
	public static String timeStampBeforeSuite = Accessories.timeStamp().replace(" ", "_").replace(":", "_").replace(".", "_");
	public static Logger Log4j = Logger.getLogger(Base.class.getName());
	public static String workingDir = System.getProperty("user.dir").replace(File.separator, "/");
	public static String wodUserFirstName, wodUserLastName, aaUserFirstName, aaUserLastName;
	public static String wodComplimentaryPassTime, amdocsAmenityPassTime;
	public static String[] uniqueBaseFolderNamesWithMethodsArray;
	public static String[] uniqueBaseFolderNamesWithExcludeMethodsArray;
	public static final String Path_TestData = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" 
			+ File.separator + "resources";
	public static String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
	public static String androidAppPath = desktopPath + File.separator + "MOCK.apk";
	public static String iOSAppPath = desktopPath + File.separator + "HawaiianAirlines.app";
	public static String configPropertiesPath = Path_TestData + File.separator + "config.properties";
	public static final String regressionTestSuite = Path_TestData + "CaptivePortal_TestCases.xlsx";
	public static String currentTestMethodName;
	public static int successCounter, failureCounter;
	public static AppiumDriverLocalService appiumService;
	public static String tempTestcaseImagesPath = System.getProperty("user.dir") + File.separator ;
	public static String screenshotPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator;
	
	public static String deleteFolderFilePath = System.getProperty("user.dir") + File.separator + "DeleteFolder.bat";
	public static String executeType = null;
	public static String deviceType = EnvironmentUtils.getExecutionDeviceType();
	public static String browserName = BrowserUtils.getBrowserName();
	public static String environmentName = EnvironmentUtils.getEnvironmentName();
	public static String deviceName = EnvironmentUtils.getDeviceName();
	public static String deviceConnectHost = EnvironmentUtils.getRemoteIp();
	public static String testCaseName;
	public static String serviceUrl = "http://" + deviceConnectHost + ":4723/wd/hub";
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static String pageLocatorsPath = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "nativeapp" + File.separator + "pageLocator" + File.separator ;
	public static double failCounter;
	public static double passCounter;
	public static String userId;
	public static String killProcessBatFilePath = System.getProperty("user.dir") + File.separator + "KillTaskProcesses.bat";
	public static String listProcessBatFilePath = System.getProperty("user.dir") + File.separator + "ListTaskManagerProcesses.bat";
	public static String startDesktopJenkinsBatFilePath = "C:" + File.separator + "Jenkins" + File.separator + "execute.bat" + File.separator;
	public static String osName = DeviceDetails.getOSName();
	public static String iOSPlatformVersion = "11.2";
	public static String mobileMacId = null;
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	
	@BeforeSuite (alwaysRun = true)
	public static void startReport() throws Throwable {
		initiateLog4jConfigurator();
		FileUtilityHelper.deleteImageProcessingFolder();
		FileUtilityHelper.deleteJenkinsReportFolder();
		generateInitialExtentReport();
	}
	
	@BeforeMethod (alwaysRun = true)
	public void beforeMethodActivity(Method method) {
		try {
			softAssert = new SoftAssert();
			m_errors.clear();//required for reporting
			createTestCaseInReport(method);
		} catch (Throwable e) {
			Log4j.info(e.getMessage());
		}
	}
	
	@AfterMethod (alwaysRun = true)
	public void getResult(ITestResult result) {
		try {
			StringUtils.getCurrentTestMethodName(result);
			LoggerHelper.setTestResultStatus(result);
			Log4j.info("Test Case '" + testCaseName + "' execution status: '" + getTestResultStatus(result) + "'");
			BrowserUtils.quitBrowser();
		} catch (Throwable e) {
			Log4j.info(e.getMessage());
		}
	}
	
	@AfterSuite (alwaysRun = true)
	public void endReport() throws Throwable {
        extent.flush();
        extent.close();
        testReportClosureActivities();
    }

	public String getTestResultStatus(ITestResult result) {
		if (result.getStatus() == 2) {
			return "FAIL";
		} else if (result.getStatus() == 1) {
			return "PASS";
		}
		return null;
	}
	
	public static void createTestCaseInReport(Method method) throws Throwable{
		testCaseName = method.getName().toString();
		Log4j.info("Current executing test method is: " + testCaseName);
		logger = extent.startTest(method.getName());
	}
	
	public static void generateInitialExtentReport() throws Exception {
		extent = new ExtentReports (createNewHTMLReport(), true);
		addSystemInfoToCaptivePortalExtentReportSummaryPage();
        extent.loadConfig(new File(System.getProperty("user.dir") + File.separator + "extent-config.xml"));
	}
	
	public static void addSystemInfoToCaptivePortalExtentReportSummaryPage() {
		extent
        .addSystemInfo("Environment", environmentName)
        .addSystemInfo("Device Type", deviceType)
        .addSystemInfo("Device Name", deviceName);
	}
		
	public void testReportClosureActivities() throws Throwable {
        FileUtilityHelper.replaceHTMLContent("", DeviceDetails.getOSVersion());
        FileUtilityHelper.replaceContent();
        FileUtilityHelper.replacePassPercentagePosition();
        FileUtilityHelper.replacePassFailPercent();
        ScreenshotUtils.copyImagesToWord();
        FileUtilityHelper.generateJenkinsReport();
   	}
	
	public static void initiateLog4jConfigurator() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	public static String createNewHTMLReport() throws Exception {
		File file = new File(FileUtilityHelper.filePath() + "Results_" + Base.timeStampBeforeSuite + ".html");// "Results.html"
		file.createNewFile();
		return file.toString();
	}
	
	public static void takeScreenshots() throws Throwable {
		BrowserUtils.waitForPageLoad(10);
		if (!deviceType.equalsIgnoreCase("Desktop")) {
			ScreenshotUtils.captureScreenshot();
		} else if (deviceType.equalsIgnoreCase("Desktop")) {
			ScreenshotUtils.captureDesktopScreenshot(FileUtilityHelper.getTestcaseImagesPath() + File.separator + "pic" + "_" + FileUtilityHelper.getImageTimeStamp() +".png");
		}
		Thread.sleep(1000);
	}
	
	@SuppressWarnings("unused")
	public static String getLatestInterfacesFileName() throws Exception {
		File interfacesPath = new File("C:" + File.separator + "ProgramData" + File.separator + "Microsoft" + File.separator + "Wlansvc" + File.separator + "Profiles" + File.separator + "Interfaces" + File.separator);
		String[] names = interfacesPath.list();
		String file = null;
		
		for(String name : names) {
			String tempLastModifiedFolder = FileUtilityHelper.getLatestFile(interfacesPath.toString()).toString();
			file = tempLastModifiedFolder;
		}
		
		return file;
	}

	public static void purgeOldFiles() {
		FileUtilityHelper.deleteFilesOlderThan30Days();
	}
	
	public static void resetTCResultCounter() {
		successCounter = 0; 
		failureCounter = 0;
	}
	
	public static boolean getTestCaseFinalStatus() {
  		return failureCounter == 0;
  	}
	
	public static double getPassPercent() {
		double passPercent = (passCounter/(passCounter + failCounter) * 100);
		return (double) Math.round(passPercent * 100) / 100;
	}
	
	public static double getFailPercent() {
		double passPercent = (failCounter/(passCounter + failCounter) * 100);
		return (double) Math.round(passPercent * 100) / 100;
	}
	
}
