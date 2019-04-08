package com.anytrek.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

public class GlobalUtil {

	public static String randomKey(int digit) { 
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",  
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z" };
        List<String> list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
        }  
        String afterShuffle = sb.toString();  
        String result = afterShuffle.substring(0, digit);  
        return result;  
	}
	
	public static String getUniqueKey() {
		return UUID.randomUUID().toString();
	}

	public static Timestamp nowTimestamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public static Date nowDate() {

		return Calendar.getInstance().getTime();
	}

	public static String formatDate(String format, String value) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(value);
		return df.format(date);
	}

	public static String formatDate(String format, Date date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static java.sql.Date nowSqlDate() {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		return date;
	}

	public static String truncateStr(String str, int len, String elide) {

		if (str == null) {
			return "";
		}
		int strLen = str.length();
		if (len >= strLen || len < 1) {
			return str;
		}
		str = str.substring(0, len);
		str += elide;
		return str;
	}

	@SuppressWarnings("unchecked")
	public static List<Object> deepCopy(List<Object> src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		List<Object> dest = (List<Object>) in.readObject();
		return dest;
	}

	/**
	 * 转换规则，将字符串中的_下划线删除，并将下划线后后一字母改成大写
	 * 
	 * @param dbField
	 * @return
	 */
	public static String getEntityPropertyNameByDbField(String dbField) {
		StringBuilder temp = new StringBuilder(dbField);
		int index = 0;
		while ((index = temp.indexOf("_", index)) >= 0) {
			int length = temp.length();
			// 去掉下划线
			temp.deleteCharAt(index);
			length -= 1;
			// 下一字母大写
			if (index < length - 2) {
				temp.replace(index, index + 1, String.valueOf(temp.charAt(index)).toUpperCase());
			}
		}
		return temp.toString();
	}

	public static String getTimestamp() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		StringBuilder result = new StringBuilder();
		result.append("[");
		if (hour < 10) {
			result.append("0").append(hour);
		} else {
			result.append(hour);
		}
		result.append(":");
		if (minute < 10) {
			result.append("0").append(minute);
		} else {
			result.append(minute);
		}
		result.append("(UTC)]");
		return result.toString();
	}

	/**
	 * 是否合法的IMEI
	 * 
	 * @param imei
	 * @return
	 */
	public static boolean isValidImei(String imei) {
		if (imei == null)
			return false;
		else 
			return true;
//		if (imei.startsWith("0"))
//			return false;
//		return StringTools.isLong(imei, true);
	}

	public static boolean isSameDate(java.sql.Date date1, java.sql.Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否正确的timeZone
	 * 
	 * @param timeZoneString
	 * @return
	 */
	public static boolean isValidTimeZone(String timeZoneString) {
		try {
			if (timeZoneString == null || timeZoneString.equals("")) {
				return false;
			}
			int timeZone = Integer.parseInt(timeZoneString);
			if (Math.abs(timeZone / 100) >= 12) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断两个时间的小时部分是否相同
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean hourIsSame(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		return c1.get(Calendar.HOUR_OF_DAY) == c2.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 产生六位随机数字
	 * @return
	 */
	public static String generateRandomNumbers(int numberCount) {
		if(numberCount < 1) {
			throw new RuntimeException("NumberCount must be greater than or equal to 1");
		}
		Random random = new Random();
		StringBuilder result = new StringBuilder(numberCount);
		for (int i = 0; i < numberCount; i++) {
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
	
	/**
	 * 将UTC时间转换成自1970年开始的毫秒数
	 * @param format
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static long parseUTCTimeToMills(String format, String time) throws ParseException {
		SimpleDateFormat timeFormat = new SimpleDateFormat(format);
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return timeFormat.parse(time).getTime();
	}
	
	/**
	 * 将时间格式化成字符串
	 * @param format
	 * @param date
	 * @return
	 */
	public static String formatTimeToString(String format, Date date) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(format);
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return timeFormat.format(date);
	}

}
