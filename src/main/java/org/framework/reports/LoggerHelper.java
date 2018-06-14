package org.framework.reports;

import org.apache.log4j.Logger;
import org.framework.Base;
import org.framework.utilities.ScreenshotUtilsHelper;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class LoggerHelper extends Base {
	private static Logger Log4j = Logger.getLogger(LoggerHelper.class.getName());
	public static String failedTestCaseName;

	public void info(String message) {
		try {
			if (!message.contains("unknown server-side error") || !message.contains("waiting for title to contain")
					|| !message.contains("AndroidDriverCapabilities [{")
					|| !message.contains("java.lang.NullPointerException")) {
				logger.log(LogStatus.INFO, message);
			}
			Log4j.info(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fail(ITestResult result) throws Exception {
		failedTestCaseName = result.getName();
		String throwableErrorData = result.getThrowable().toString();
		logger.log(LogStatus.FAIL, "Test Case Failed: " + throwableErrorData);
		if (throwableErrorData.contains("An unknown server-side error occurred") ||
				throwableErrorData.contains("java.lang.NullPointerException") || 
				throwableErrorData.toLowerCase().contains("exception")) {
			logger.log(LogStatus.INFO, logger.addScreenCapture(ScreenshotUtilsHelper.getScreenshot()));
		}
		logger.log(LogStatus.FAIL, "Test Case Failed: " + failedTestCaseName);
	}

	public static void pass(ITestResult result) {
		logger.log(LogStatus.PASS, "Test Case Passed: " + result.getName());
	}

	public static void setTestResultStatus(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			fail(result);
			failCounter++;
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			pass(result);
			passCounter++;
		}
		extent.endTest(logger);
	}
	
	public static void printFailMessage() throws Exception {
		Base.logger.log(LogStatus.FAIL, HardAssertion.flMessage);
		logger.log(LogStatus.INFO, logger.addScreenCapture(ScreenshotUtilsHelper.getScreenshot()));
	}
}
