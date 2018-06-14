package org.framework.utilities;

import java.util.concurrent.TimeUnit;

import org.framework.Base;
import org.framework.webobjects.WebObjects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollUtils extends Base {
	public static String elementHeaderText = "div.ob-widget-header";
	//protected static RemoteWebDriver driver = null;

	public static void scrollToPage(int movePixelsBy) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0," + movePixelsBy + ")", "");
    }
	
	/*public static void scrollToTopOfPage() {
		WebElement element;
        logHelper.info("Scrolling the page to top of the page");
        JavascriptExecutor je = (JavascriptExecutor) driver;
        //je.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
        je.exeuteScript("arguments[0].scrollIntoView();", element);
    }*/
	
	public static boolean scrollToElement() {
		
		try {
			for (int positionIndex = 510; positionIndex <= 2010; positionIndex = positionIndex+500) {
				System.out.println("element not found and scrolling to  " + positionIndex);
				scrollToPage(positionIndex);
				if (WebObjects.isVisible(elementHeaderText )) {
					int yPosition =	WebObjects.getWebElement(elementHeaderText).getLocation().y;
					scrollToPage(yPosition);
					System.out.println("scrolled position " + positionIndex);
					break;
				} 
			}
		} catch (Exception e) {
			Log4j.info(e.getMessage());
	}
		return false;
	}
	
	public static boolean isElementVisibleOnScreen(WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			return element.isDisplayed();
		} catch (Exception e) {
			Log4j.info(e.getMessage());
		}
		return false;
	}

	public static void scrollToElement(WebElement element) {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.perform();
			Thread.sleep(500);
		} catch (Exception e) {
			Log4j.info(e.getMessage());
			System.out.println("The element '" + element.getText().trim() + "' is not found!!");
		}
	}
}
