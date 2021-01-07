/**
 * 
 */
package com.org.usertrackingsystem.common.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 7000018390
 *
 */
public class CommonUtils {
	

	CommonUtils() {
		
	}
	
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static Date convertStringToDate(String strDate) {
		DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertStringToDate(String strDate, String inFormat) {
		DateFormat format = new SimpleDateFormat(inFormat);
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatDateStr(String strDate, String inFormat, String outFormat) {
		Date dt = convertStringToDate(strDate,inFormat);
		return convertDateToString(dt,outFormat);
	}
	
	public static String convertMonthAndYearAsString(Date getDate,String format) {
		Format formatter = new SimpleDateFormat(format);
		return formatter.format(getDate);
		
	}
	
	public static String convertDateToString(Date date, String outFormat) {
		DateFormat format;
		if(outFormat!=null) {
			format = new SimpleDateFormat(outFormat);
		}else {
			format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		}
		try {
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertDateToString(Date date) {
		DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		try {
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isEmpty(String str) {
		boolean isEmpty = false;
		if (str == null || str.trim().equals("") || str.isEmpty()) {
			isEmpty =  true;
		} else {
			isEmpty =  false;
		}
		return isEmpty;
	}
	
	public static <T> boolean isEmpty(List<T> list) {
		boolean isEmptyLst = false;
		if (list == null || list.isEmpty()) {
			isEmptyLst = true;
		} else {
			isEmptyLst =  false;
		}
		return isEmptyLst;
	}

	/**
	 * @param values
	 * @return
	 */
	public static <T> String buildCommaSeparatedString(T[] values) {
		StringBuilder sb = new StringBuilder();
		for (T n : values) {
			if (sb.length() > 0)
				sb.append(',');
			sb.append("'").append(n).append("'");
		}

		return sb.toString();
	}
	
	public static List<String> buildListFromString(String list) {
		return buildListFromString(list,null);
	}
	
	public static List<String> buildListFromString(String list, String delimeter) {
		if(isEmpty(delimeter)) {
			delimeter = ",";
		}
		if(isEmpty(list)) {
			return new ArrayList<>();
		}
		return Arrays.asList(list.split("\\s*"+delimeter+"\\s*"));
	}
	public static Date addDays(Date date, Integer numberOfDays) {
		if(null == date) {
			new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, numberOfDays); 
		return cal.getTime();
	}
	
	public static String[] buildStringArrayFromList(List<String> list) {
		if(isEmpty(list)) {
			return new String[] {};
		}else {
			return list.stream().toArray(String[]::new);
		}
	}
	public static String getPreviousMonthYear(String month,int year) {
		String[] monthYr =new String[2];
		Calendar c = Calendar.getInstance();
		int monthInt = getMonthInNumericalValue(month);
		c.set(Calendar.MONTH, monthInt-1);
		c.set(Calendar.YEAR, year);
		c.add(Calendar.MONTH, -1);
		monthYr[0]=getProperMonth(c.get(Calendar.MONTH));
		monthYr[1]=String.valueOf(c.get(Calendar.YEAR));
		String monthAndYear = monthYr[0] + "-" + monthYr[1];
		return monthAndYear;
	}

	public static String getProperMonth(int month) {
		String pmonth = null;
		switch (month) {
		case 0:
			pmonth = "JAN";
			break;
		case 1:
			pmonth = "FEB";
			break;
		case 2:
			pmonth = "MAR";
			break;
		case 3:
			pmonth = "APR";
			break;
		case 4:
			pmonth = "MAY";
			break;
		case 5:
			pmonth = "JUN";
			break;
		case 6:
			pmonth = "JUL";
			break;
		case 7:
			pmonth = "AUG";
			break;
		case 8:
			pmonth = "SEP";
			break;
		case 9:
			pmonth = "OCT";
			break;
		case 10:
			pmonth = "NOV";
			break;
		case 11:
			pmonth = "DEC";
			break;
		}
		return pmonth;
	}
	public static String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	public static int getMonthInNumericalValue(String dayVal) {

		int day = 0;

		switch (dayVal) {
		case "JAN":
			day = 1;
			break;

		case "FEB":
			day = 2;
			break;

		case "MAR":
			day = 3;
			break;

		case "APR":
			day = 4;
			break;

		case "MAY":
			day = 5;
			break;

		case "JUN":
			day = 6;
			break;

		case "JUL":
			day = 7;
			break;

		case "AUG":
			day = 8;
			break;

		case "SEP":
			day = 9;
			break;

		case "OCT":
			day = 10;
			break;

		case "NOV":
			day = 11;
			break;

		case "DEC":
			day = 12;
			break;

		}
		return day;
	}
}
