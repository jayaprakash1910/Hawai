package org.framework.utilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.framework.Base;

public class CalendarHelper extends Base {
	private static Logger Log4j = Logger.getLogger(CalendarHelper.class.getName());

	public static String getDateFormat() {
		String currentDate = null;
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			
			currentDate = dateFormat.format(date);
		} catch (Exception e) {
			Log4j.info(e.getMessage());
		}

		return currentDate;
	}

	public static String getCalendarDate() {
		String currentDate = null;
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			currentDate = dateFormat.format(cal);
		} catch (Exception e) {
			Log4j.info(e.getMessage());
		}

		return currentDate;
	}

	public static String getLocalDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public static String getCurrentTime() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DateTimeFormatter.ofPattern("hh:mm:ss.nnnnnnnnn"));
	}

	public static double getPageLoadTime(String beforeTime, String afterTime) {
		try {
			String[] beforeTimeData = beforeTime.split(":");
			String[] afterMinuteData = afterTime.split(":");
			if (Integer.parseInt(afterMinuteData[0]) == Integer.parseInt(beforeTimeData[0])) {
				return (Double.parseDouble(afterMinuteData[1]) - Double.parseDouble(beforeTimeData[1]));
			} else {
				return 0;
			}
		} catch (Exception e) {
			Log4j.info(e.getMessage());
		}
		return 0; 
	}

	public static String getLocalDate() throws ParseException {
		String currentDate = null;
		Date date = null;
		try {
			SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.US);
			date = dtf.parse(currentDate);
		} catch (Exception e) {
			Log4j.info(e.getMessage());
		}
		return date.toString();
	}

	public static long getDifferenceBetweenTimes(String time1, String time2) {
		long result = 0;
		SimpleDateFormat dtf = new SimpleDateFormat("hh:mm:ss");

		try {
			Date firstDate = dtf.parse(time1);
			Date secondDate = dtf.parse(time2);

			result = secondDate.getTime() - firstDate.getTime();
			result = result / 1000;
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return result;
	}

	public static String getMinuteFromCurrentTime() {
		String minutes = null;

		try {
			LocalDateTime now = LocalDateTime.now();
			minutes = now.format(DateTimeFormatter.ofPattern("mm"));
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return minutes;
	}
	
	public static String formatDate(String userDate) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
			LocalDate localDate = LocalDate.now();

			userDate = dtf.format(localDate);
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		} 
		
		return userDate;
	}
	
	public static String getTimeStamp() {
		String formatStr = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy HH.mm.ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			formatStr = sdf.format(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}  
		
		return formatStr;
	}
}
