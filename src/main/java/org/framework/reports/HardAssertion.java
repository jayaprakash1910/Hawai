package org.framework.reports;

import org.framework.Base;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class HardAssertion {
	
	public static String flMessage;
	LoggerHelper logger = new LoggerHelper();
	
	public void assertTrue(boolean condition, String fMessage, String pMessage) throws Exception {
		flMessage = fMessage;
		if (condition) {
			Base.logger.log(LogStatus.PASS, pMessage);
			Base.Log4j.info(pMessage);
		} else {
			LoggerHelper.printFailMessage();
		}
		Assert.assertTrue(condition);
	}
}
