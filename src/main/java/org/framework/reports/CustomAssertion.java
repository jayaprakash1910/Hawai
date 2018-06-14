package org.framework.reports;


import java.util.Map;

import org.framework.Base;
import org.framework.utilities.ScreenshotUtilsHelper;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;

public class CustomAssertion extends SoftAssert {
	
	public static String passMessage = "";
	public static String failMessage = ""; 
	
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		if (passMessage.equals("")) {
			Base.logger.log(LogStatus.PASS, "------- Please provide a success step message ----------- ");
		} else {
			Base.logger.log(LogStatus.PASS, passMessage);
			Base.Log4j.info(passMessage);
		}
	}
	
	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		try {
			Base.logger.log(LogStatus.FAIL, failMessage);
			Base.Log4j.info(failMessage);
			Base.logger.log(LogStatus.INFO, Base.logger.addScreenCapture(ScreenshotUtilsHelper.getScreenshot()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doAssert(IAssert<?> a) {
		onBeforeAssert(a);
		try {
			a.doAssert();
			onAssertSuccess(a);
		} catch (AssertionError ex) {
			onAssertFailure(a, ex);
			Base.m_errors.put(ex, a);
		} finally {
			onAfterAssert(a);
		}
	}

	public void assertAll() {
		if (!Base.m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder("The following asserts failed:");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert<?>> ae : Base.m_errors.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("\n\t");
				sb.append(ae.getKey().getMessage());
			}
			throw new AssertionError(sb.toString());
		}
	}
	
	public void assertTrue(boolean condition, String fMessage, String pMessage) {
		passMessage = pMessage;
		failMessage = fMessage;
		assertTrue(condition);
	}
	
	public void assertEquals(String actual, String expected, String fMessage, String pMessage) {
		passMessage = pMessage;
		failMessage = fMessage;
		assertEquals(actual, expected);
	}
	
	public void assertFalse(boolean condition, String fMessage, String pMessage) {
		passMessage = pMessage;
		failMessage = fMessage;
		assertFalse(condition);
	}
}