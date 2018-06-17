package org.framework.utilities;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.framework.Base;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class StringUtils extends Base {

		private static int getRandomNumber(ArrayList<String> listName) {
		Random rand = new Random();
		return rand.nextInt(listName.size());
	}

	
	public static String generateRandomStrAlphabetic(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
	
	public static String generateRandomNumeric(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	
	public static String generateRandomStrAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	public static int generateRandomInt(int maxNumber) {
		return new Random().nextInt(maxNumber);
	}
	
	public static int generateRandomIntInRange(int lowerBound, int upperBound) {
		return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
	}

	
	public static void getCurrentTestMethodName(ITestResult result) {
		Base.currentTestMethodName = result.getMethod().getMethodName();
	}
	
	public static String getFirstNCharacters(String str, int n) {
		return str.substring(0, Math.min(str.length(), n));
	}
	
	public static String removeLastCharacter(String str) {
		if(str != null && str.length() > 0 && Character.isLetter(str.charAt(str.length()-1)) ) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public ArrayList<String> getGroupNamesFromTestMethods(Method method) {
		ArrayList<String> groupName = new ArrayList<String>();
		Test testClass = method.getAnnotation(Test.class);
		
		for (int i = 0; i < testClass.groups().length; i++) {
			System.out.println(testClass.groups()[i]);
			groupName.add(testClass.groups()[i]);
		}
		
		return groupName;
	}
	
	public static String generateRandomUUID() {
		UUID uuid = UUID.randomUUID();
	    return uuid.toString();
	}
	
	public static String stringToHexadecimal(String text) throws UnsupportedEncodingException {
	    byte[] myBytes = text.getBytes("UTF-8");

	    return DatatypeConverter.printHexBinary(myBytes);
	}
	
	public static String getTextWithOutDecimals(String sampleText) {
		if (sampleText.contains(".0")) {
			sampleText = sampleText.substring(0, (sampleText.length() - 2));
		} else if (sampleText.contains(".00")) {
			sampleText = sampleText.substring(0, (sampleText.length() - 3));
		}
		return sampleText;
	}
	
	public static String replaceCharacterInAString(String str, String oldStr, String newStr) {
		return str.replace(oldStr, newStr);
	}
}
