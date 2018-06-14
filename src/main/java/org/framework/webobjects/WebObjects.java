package org.framework.webobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.framework.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebObjects extends Base {
	public static Logger Log = Logger.getLogger(Base.class.getName());
	//protected static RemoteWebDriver driver = null;
	
	public static boolean isVisible(String myelement) {
		try {
			WebElement element = getWebElement(myelement);
			return element.isDisplayed();
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		return false;
	}
	
	public static void click(String myelement) {
		try {
			if (isVisible(myelement)) {
				WebElement element = getWebElement(myelement);
				element.click();
			}
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
	}
	
	public static void selectByIndex(String myelement, int index) {
		try {
			if (isVisible(myelement)) {
				WebElement element = getWebElement(myelement);
				Select selElement = new Select(element);
				selElement.selectByIndex(index);
			}
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
	}
	
	public static void selectByValue(String myelement, String value) {
		try {
			if (isVisible(myelement)) {
				WebElement element = getWebElement(myelement);
				Select selElement = new Select(element);
				selElement.selectByValue(value);
			}
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
	}
	
	public static String getAttributeValue(String myelement, String property) {
		try {
			if (isVisible(myelement)) {
				WebElement element = getWebElement(myelement);
				return element.getAttribute(property);
			} 
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		return "";
	}
	
	public static String getText(String myelement) {
		try {
			if (isVisible(myelement)) {
				WebElement element = getWebElement(myelement);
				return element.getText();
			}
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		return "";
	}
	
	public static WebElement getWebElement(String elementLocator) {
		try {
			if (elementLocator.contains("//")) {
				return driver.findElement(By.xpath(elementLocator));
			} else if (elementLocator.contains(".") || elementLocator.contains(">") || elementLocator.contains("#") || elementLocator.contains("nth-of-type")) {
				return driver.findElement(By.cssSelector(elementLocator));
			} else {
				return driver.findElement(By.id(elementLocator));
			}
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		return null;
	}
	
	public static List<WebElement> getWebElementsList(String elementLocator) {
		try {
			return driver.findElements(By.xpath(elementLocator));
		} catch (Exception e) {
			Log.info(e.getCause());
		}
		return null;
	}
	
	public static int getSizeOfWebElements(List<WebElement> elements) {
		try {
			return elements.size();
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		return 0;
	}
	
	public static void setText(String elementLocator, String data) {
		try {
			getWebElement(elementLocator).sendKeys(data);
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
	}
}
