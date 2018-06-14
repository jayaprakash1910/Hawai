package org.framework;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.framework.reports.Accessories;
import org.framework.reports.CustomAssertion;
import org.framework.reports.HardAssertion;
import org.framework.reports.LoggerHelper;
import org.framework.utilities.BrowserHelper;
import org.framework.utilities.DeviceDetails;
import org.framework.utilities.DriverUtils;
import org.framework.utilities.ExcelUtils;
import org.framework.utilities.FileUtilityHelper;
import org.framework.utilities.PhysicalDeviceDetails;
import org.framework.utilities.PropertiesHelper;
import org.framework.utilities.ScreenshotUtilsHelper;
import org.framework.utilities.StringUtilsHelper;
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
	public static String androidAppPath = System.getProperty("user.dir") + File.separator + "MOCK.apk";
	public static String configPropertiesPath = Path_TestData + File.separator + "config.properties";
	public static final String regressionTestSuite = Path_TestData + "CaptivePortal_TestCases.xlsx";
	public static String currentTestMethodName;
	public static int successCounter, failureCounter;
	public static AppiumDriverLocalService appiumService;
	public static String tempTestcaseImagesPath = System.getProperty("user.dir") + File.separator ;
	public static String screenshotPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator;
	
	public static String deleteFolderFilePath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator
			+ "comcast" + File.separator + "MobileWiFi" + File.separator + "testData" + File.separator + "DeleteFolder.bat";
	public static String executeType = null;
	public static String deviceType = getExecutionDeviceType();
	public static String browserName = getBrowserName();
	public static String environmentName = getEnvironmentName();
	public static String deviceName = getDeviceName();
	public static String deviceConnectHost = getRemoteIp();
	public static String testCaseName;
	public static String serviceUrl = "http://" + deviceConnectHost + ":4723/wd/hub";
	public static String addRegistryFilePath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator
			+ "comcast" + File.separator + "MobileWiFi" + File.separator + "testData" + File.separator + "AddRegistry.bat";
	public static String deleteRegistryFilePath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator
			+ "comcast" + File.separator + "MobileWiFi" + File.separator + "testData" + File.separator + "DeleteRegistry.bat";
	
	public static String aaaErrorCodesExcelPath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator
			+ "comcast" + File.separator + "MobileWiFi" + File.separator + "testData" + File.separator + "TestData.xlsx";
	
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static String pageLocatorsPath = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "nativeapp" + File.separator + "pageLocator" + File.separator ;
	public static double failCounter;
	public static double passCounter;
	public static String userId;
	public static String killProcessBatFilePath = System.getProperty("user.dir") + File.separator + "KillTaskProcesses.bat";
	public static String listProcessBatFilePath = System.getProperty("user.dir") + File.separator + "ListTaskManagerProcesses.bat";
	public static String adbUninstallSettingsBat = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator
			+ "comcast" + File.separator + "MobileWiFi" + File.separator + "testData" + File.separator + "adbUninstallSettings.bat";
	
	public static String adbUninstallUnlockBat = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator
			+ "comcast" + File.separator + "MobileWiFi" + File.separator + "testData" + File.separator + "adbUninstallUnlock.bat";
	
	public static String startDesktopJenkinsBatFilePath = "C:" + File.separator + "Jenkins" + File.separator + "execute.bat" + File.separator;
	public static String osName = DeviceDetails.getOSName();
	public static String iOSPlatformVersion = "11.2";
	public static String mobileMacId = null;
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	
	public static String getDeviceType() throws Throwable {
		return deviceType;
	}
	
	public static String getPageLocatorPath() throws Throwable {
		return System.getProperty("user.dir")+ File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "org" + File.separator + "comcast" + File.separator + "MobileWiFi" +  File.separator + "pageLocator" + File.separator ;
	}
	
	public static boolean isVirtualDevice() {
		if (System.getProperty("virtualdevice") != null) {
			return System.getProperty("virtualdevice").equalsIgnoreCase("yes");
		} 
		return PropertiesHelper.readProperties("virtualdevice").equalsIgnoreCase("yes");
	}
	
	@BeforeSuite (alwaysRun = true)
	public static void startReport() throws Throwable {
		initiateLog4jConfigurator();
		deleteImageProcessingFolder();
		deleteJenkinsReportFolder();
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
			StringUtilsHelper.getCurrentTestMethodName(result);
			LoggerHelper.setTestResultStatus(result);
			Log4j.info("Test Case '" + testCaseName + "' execution status: '" + getTestResultStatus(result) + "'");
			BrowserHelper.quitBrowser();
		} catch (Throwable e) {
			Log4j.info(e.getMessage());
		}
	}
	
	@AfterSuite (alwaysRun = true)
	public void endReport() throws Throwable {
        extent.flush();
        extent.close();
        captivePortalReportClosureActivities();
    }
	
	public static void instantiateDriver() throws Throwable {
		DriverUtils.getDriver(deviceType);
	}
	
	public static boolean isNative() {
		if (System.getProperty("native") != null) {
			return System.getProperty("native").equalsIgnoreCase("yes");
		}
		return PropertiesHelper.readProperties("native").equalsIgnoreCase("yes");
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
	
	public static String getEnvironmentName() {
		if (System.getProperty("environment") != null) {
			return System.getProperty("environment");
		} else {
			return PropertiesHelper.readProperties("Environment");
		}
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
        .addSystemInfo("System's OS Version", "tempOSVersion") 
        .addSystemInfo("Device Type", deviceType)
        .addSystemInfo("Device Name", deviceName);
	}
		
	public void captivePortalReportClosureActivities() throws Throwable {
        FileUtilityHelper.replaceHTMLContent("", DeviceDetails.getOSVersion());
        FileUtilityHelper.replaceContent();
        FileUtilityHelper.replacePassPercentagePosition();
        FileUtilityHelper.replacePassFailPercent();
        ScreenshotUtilsHelper.copyImagesToWord();
        FileUtilityHelper.generateJenkinsReport();
   	}
	
	public static void initiateLog4jConfigurator() {
		DOMConfigurator.configure("log4j.xml");
	}

	public static String filePath() {
		String strDirectoy = "ResultFile";
		String screenshotPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator;
		new File(screenshotPath + strDirectoy + "_" + timeStamp).mkdirs();
		return screenshotPath + strDirectoy + "_" + timeStamp + File.separator;
	}
	
	public static String createNewHTMLReport() throws Exception {
		File file = new File(Base.filePath() + "Results_" + Base.timeStampBeforeSuite + ".html");// "Results.html"
		file.createNewFile();
		return file.toString();
	}
	
	public static void takeScreenshots() throws Throwable {
		BrowserHelper.waitForPageLoad(10);
		if (!deviceType.equalsIgnoreCase("Desktop")) {
			ScreenshotUtilsHelper.captureScreenshot();
		} else if (deviceType.equalsIgnoreCase("Desktop")) {
			ScreenshotUtilsHelper.captureDesktopScreenshot(FileUtilityHelper.getTestcaseImagesPath() + File.separator + "pic" + "_" + FileUtilityHelper.getImageTimeStamp() +".png");
		}
		Thread.sleep(1000);
	}
	
	public static void deleteImageProcessingFolder() throws Throwable {
		String directory = "ImageProcessing";
		File file = new File (tempTestcaseImagesPath + directory + File.separator);
		
		if (isWindows()) {
			if(file.exists()){
				FileUtilityHelper.deleteDirFromCMDPrompt(tempTestcaseImagesPath + directory + File.separator);
			}
		} else if (isMAC()) {
			if (file.exists()) {
				Runtime.getRuntime().exec("rm -R " + file.getAbsolutePath());
			}
			
		} else if (file.exists()) {
			FileUtilityHelper.deleteDir(tempTestcaseImagesPath + directory + File.separator);
		}
	}
	
	public static void deleteJenkinsReportFolder() throws Throwable {
		String jenkinsReportPath = System.getProperty("user.dir") + File.separator + "JenkinsReport" + File.separator;
		File file = new File (jenkinsReportPath);
		
		if (isWindows()) {
			if(file.exists()){
				FileUtilityHelper.deleteDirFromCMDPrompt(jenkinsReportPath);
			}
		} else if (isMAC()) {
			if (file.exists()) {
				Runtime.getRuntime().exec("rm -R " + file.getAbsolutePath());
			}
			
		} else if (file.exists()) {
			FileUtilityHelper.deleteDir(jenkinsReportPath);
		}
	}
	
	public static void deleteFailedTestsScreenshotsFolder() throws Throwable {
		String directory = "FailedTestsScreenshots";
		String failedTestCaseScreenshotPath = System.getProperty("user.dir") + File.separator + "FailedTestsScreenshots" + File.separator;
		File file = new File(failedTestCaseScreenshotPath);
		
		if (isWindows()) {
			if(file.exists()){
				FileUtilityHelper.deleteDirFromCMDPrompt(failedTestCaseScreenshotPath);
			}
		} else if (isMAC()) {
			if (file.exists()) {
				Runtime.getRuntime().exec("rm -R " + file.getAbsolutePath());
			}
			
		} else if (file.exists()) {
			FileUtilityHelper.deleteDir(failedTestCaseScreenshotPath + directory + File.separator);
		}
	}
	
	public static String generateSheetName() throws Throwable {
		String environment = PropertiesHelper.readProperties("Environment");
		if (environment.equalsIgnoreCase("QA")) {
			return "AP_Setup_QA";
		} else if (environment.equalsIgnoreCase("STAGE")) {
			return "AP_Setup_STAGE";
		} else if (environment.equalsIgnoreCase("PROD")) {
			return "AP_Setup_PROD";
		}
		return null;
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
	
	public static ArrayList<String> getExcelTestNamesToExecute(String sheetName) throws Throwable {
		String filePath = Base.Path_TestData;

		FileInputStream excelFile = new FileInputStream(filePath + "CaptivePortal_TestCases.xlsx");
		ArrayList<String> testsFromExcel = new ArrayList<String>();

		// Access the required test data sheet
		ExcelWBook = new XSSFWorkbook(excelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);

		int rowNum = ExcelWSheet.getPhysicalNumberOfRows();
		int colIndex = 2;
		String cellValue, testCaseName;

		for (int rowIndex = 1; rowIndex < rowNum; rowIndex++) {
			Row row = CellUtil.getRow(rowIndex, ExcelWSheet);
			cellValue = ExcelUtils.getCellValue(row, colIndex).trim();

			if (cellValue.equalsIgnoreCase("Yes")) {
				// Add the test case name to the array list
				testCaseName = ExcelUtils.getCellValue(row, 1);

				testsFromExcel.add(testCaseName);
			}
		}
		return testsFromExcel;
	}
	
	public static void startAppiumServer() {
		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
	
	public static void stopAppiumServer() {
		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.stop();
	}

	public static double getPassPercent() {
		double passPercent = (passCounter/(passCounter + failCounter) * 100);
		return (double) Math.round(passPercent * 100) / 100;
	}
	
	public static double getFailPercent() {
		double passPercent = (failCounter/(passCounter + failCounter) * 100);
		return (double) Math.round(passPercent * 100) / 100;
	}
	
	//this is required only when you run with two jobs.. with new static ip implementation this is not required anymore. We are not using this method now.
	public static void startDesktopJenkinsSlave() {
		
		try { 
			if (isWindows()) {
				Log4j.info("Continuing to restart Jenkins Slave");
				Runtime.getRuntime().exec(startDesktopJenkinsBatFilePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
			Log4j.info("Failed to start jenkins slave...");
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
}
