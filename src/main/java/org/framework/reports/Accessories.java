package org.framework.reports;

public class Accessories {
	//return time and date
	public static String timeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime()).toString();
	}
}
