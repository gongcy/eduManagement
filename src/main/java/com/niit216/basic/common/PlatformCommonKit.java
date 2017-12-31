package com.niit216.basic.common;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class PlatformCommonKit {
	
	public static Object checkSqlField(Object field) {
		if(field==null) return "'NULL'";
		if(field instanceof String) {
			return "'"+field+"'";
		} else if(field instanceof Date) {
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return "'"+sdf2.format((Date)field)+"'";
		} else if(field instanceof Integer) {
			return field==null?"":""+field+"";
		}
		return field;
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if (obj instanceof String) {
			if("NULL".equals(obj.toString().trim().toUpperCase()) || "".equals(obj.toString().trim())){
			  return true;
			}else{
			  return false;
			}
		} else if (obj instanceof Collection) {
			return ((Collection<?>) obj).size() == 0;
		} else if (obj instanceof Map) {
			return ((Map<?,?>) obj).size() == 0;
		} else if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		} else {
			return false;
		}
	}
	
	/**
	 * @see #getTimeInterval(Timestamp, Timestamp, boolean)
	 * @param timestamp1
	 * @param timestamp2
	 * @return
	 */
	public static String getTimeInterval(Timestamp timestamp1,
			Timestamp timestamp2) {
		return getTimeInterval(timestamp1, timestamp2, false);
	}

	/**
	 * 取时间差
	 * 
	 * @param timestamp1
	 * @param timestamp2
	 * @param showSecond
	 *            是否显示秒
	 * @return
	 */
	public static String getTimeInterval(Timestamp timestamp1,
			Timestamp timestamp2, boolean showSecond) {
		if (timestamp1 == null || timestamp2 == null)
			return "";
		StringBuffer buf = new StringBuffer();
		long time1 = timestamp1.getTime();
		long time2 = timestamp2.getTime();
		long time = Math.abs(time1 - time2);
		// day
		long day = time / (24 * 60 * 60 * 1000L);
		// hour
		time = time - day * 24 * 60 * 60 * 1000L;
		long hour = time / (60 * 60 * 1000L);
		// minute
		time = time - hour * 60 * 60 * 1000L;
		long minute = time / (60 * 1000L);
		// second
		time = time - minute * 60 * 1000L;
		long second = time / 1000L;

		if (day != 0) {
			buf.append(day + "天");
		}
		if (hour != 0) {
			buf.append(hour + "时");
		}
		if (minute != 0) {
			buf.append(minute + "分");
		}

		if (showSecond || buf.length() == 0) {
			if (second != 0) {
				buf.append(second + "秒");
			}
		}

		return buf.toString();
	}
	
	public static boolean isNumeric(String str){ 
		if(str.matches("\\d+(\\.?\\d+)?")) {
			return true; 
		} else {
			return false;
		}
	}
}
