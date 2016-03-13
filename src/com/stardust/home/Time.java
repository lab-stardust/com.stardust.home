package com.stardust.home;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Home Planetarium
 * Time class
 * @author Stardust Laboratory
 * @version 1.1
 */
public class Time {
	public static final long[] OFFSET = { 900000, 1800000, 3600000, 10800000, 86400000, 604800000 };
	public static final String[] OFFSET_LABEL = { "15 min", "30 min", "1 hour", "3 hours", "1 day", "7 days" };

	public static String getStringTime(long currentTime) {
		String ret = "";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currentTime);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		ret = year + "/";

		if (month < 10) {
			ret += "0" + month;
		} else {
			ret += month;
		}

		ret += "/";

		if (date < 10) {
			ret += "0" + date;
		} else {
			ret += date;
		}

		ret += "\n";

		if (hour < 10) {
			ret += "0" + hour;
		} else {
			ret += "" + hour;
		}

		ret += ":";

		if (minute < 10) {
			ret += "0" + minute;
		} else {
			ret += minute;
		}

		return ret;
	}

	public static double st(long currentTime, float longitude) {
		double ret = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currentTime);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		float ut = hour + minute / 60.0f - TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 3600000.0f;
		ret = gst(year, month, date) + 1.00273791 * ut + longitude / 15.0f;
		ret = Data.normAngle(ret * 15);
		return ret;
	}

	private static double gst(int year, int month, int date) {
		int y = year;
		int m = month;

		if (m < 3) {
			m = m + 12;
			y = y - 1;
		}

		double ret = Math.floor(30.59 * (m - 2)) + date;
		ret += Math.floor(365.25 * y);
		ret += Math.floor(y / 400.0) - Math.floor(y / 100.0) + 1721088.5;
		ret = (ret - 2451545.0) / 36525.0;
		ret = (24110.54841 + 8640184.812866 * ret + 0.093104 * ret * ret - 0.0000062 * ret * ret * ret) / 86400.0;
		ret = 24 * (ret - Math.floor(ret));
		return ret;
	}

	public static double jd(long currentTime) {
		double ret, y, m;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currentTime);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		y = year;
		m = month;

		if (m < 3) {
			m += 12;
			y--;
		}

		ret = Math.floor(30.59 * (m - 2)) + date + hour / 24.0 + minute / 1440.0;
		ret += Math.floor(365.25 * y) + Math.floor(y / 400.0) - Math.floor(y / 100.0) + 1721088.5;
		return ret;
	}
}
