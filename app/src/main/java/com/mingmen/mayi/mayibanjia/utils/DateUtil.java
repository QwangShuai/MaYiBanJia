package com.mingmen.mayi.mayibanjia.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * 
 * @author ly
 * 
 */
public class DateUtil {

	/**
	 * 将日期转换为字符串
	 * 
	 * @return
	 */
	public static String dateYMD() {
		return dateFormat(new Date(), "yyyy-MM-dd");
	}

	public static String dateFormat() {
		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateFormat(String pattern) {
		return dateFormat(new Date(), pattern);
	}

	public static String dateFormat(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date dateFormat1(Date date, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String source = dateFormat(date, pattern);
		return sdf.parse(source);
	}

	public static Date dateFormat2(String date, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date StrToDate(String source, String patten) {
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成最近几天的日期
	 * 
	 * @param day
	 * @return
	 */
	public static Map<String, Integer> genDate(int day) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i <= day; i++) {
			Calendar calendar = Calendar.getInstance();
			int date = calendar.get(Calendar.DAY_OF_MONTH) - (day - i);

			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), date);
			String d = dateFormat(calendar.getTime(), "yyyy-MM-dd");
			map.put(d, i);
		}
		return map;
	}

	/**
	 * 生成几天图表的x轴说明
	 * 
	 * @param day
	 * @return
	 */
	public static List<String> genDateArray(int day) {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i <= day; i++) {
			Calendar calendar = Calendar.getInstance();
			int date = calendar.get(Calendar.DAY_OF_MONTH) - (day - i);

			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), date);
			list.add(dateFormat(calendar.getTime(), "yyyy-MM-dd"));
		}
		return list;
	}

	/**
	 * 判断两个日期是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean judgeDateSample(Date date1, Date date2, String patten) throws ParseException {
		date1 = dateFormat1(date1, patten);
		date2 = dateFormat1(date2, patten);
		if (date1.getTime() == date2.getTime())
			return true;
		return false;
	}
//
//	/**
//	 * 创建当前时间并增加分组
//	 */
//	public static String dateTime(int dl) {
//		Calendar rightNow = Calendar.getInstance();
//		rightNow.setTime(new Date());
//		rightNow.add(Calendar.MINUTE, dl);// 增加分钟
//		Date dt1 = rightNow.getTime();
//		return dateFormat(dt1, Pattern.YMDHMS);
//	}

	/**
	 * 比较日期大小
	 */
	public static int compare_date(Date dt1, Date dt2) {
		try {
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static int compare_date(String DATE1, String DATE2) {
		try {
			
		
			System.out.println("compare_date");
			if (dateFormat2(DATE1, "yyyy-MM-dd HH:mm:ss").getTime() >dateFormat2(DATE2, "yyyy-MM-dd HH:mm:ss").getTime()) {
				System.out.println("DATE2 在DATE1前");
				return 1;
			} else if (dateFormat2(DATE1, "yyyy-MM-dd HH:mm:ss").getTime() < dateFormat2(DATE2, "yyyy-MM-dd HH:mm:ss").getTime()) {
				System.out.println("DATE2在DATE1后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取SimpleDateFormat
	 */
	private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 */
	public static Date StringToDate(String date, String parttern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	/**
	 * 获取两个日期相差 d1为过去日期 d2为当前日期 type 1天 2小時 3分鐘
	 */
	public static long dqsj(Date d1, Date d2, String type) {
		long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		long minutes = diff/ (1000 * 60);
		if (type.equals("1")) {
			return days;
		} else if (type.equals("2")) {
			return hours;
		} else if (type.equals("3")){
			return minutes;
		}else{
			return diff;
		}
	}

	public static String addDate(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.MINUTE, minute); // 日期加分钟
		date = c.getTime();
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String addDateType(Date date, int type, int volume, String pattern) {
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		if (type == 1) {
			// 年
			c.add(Calendar.YEAR, volume); // 日期加年
		} else if (type == 2) {
			// 月
			c.add(Calendar.MONTH, volume); // 日期加月
		} else if (type == 3) {
			// 日
			c.add(Calendar.DAY_OF_YEAR, volume); // 日期加日
		} else if (type == 4) {
			// 时
			c.add(Calendar.HOUR_OF_DAY, volume); // 日期加时
		} else if (type == 5) {
			// 分
			c.add(Calendar.MINUTE, volume); // 日期加分钟
		} else if (type == 6) {
			// 秒
			c.add(Calendar.SECOND, volume); // 日期加秒
		}
		date = c.getTime();
		return dateFormat(date, pattern);
	}
	/**
	 * 获取当前日期一周前的日期
	 *
	 * @return
	 * @author wcb
	 * @date 2018-8-9 下午4:41:59
	 */
	public static String timeQuantum() {
	    Date dateNow = new Date();
	    Date dateBefore = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dateNow);
	    cal.add(Calendar.DAY_OF_MONTH, -7);
	    dateBefore = cal.getTime();
        return dateFormat(dateBefore, "yyyy-MM-dd HH:mm:ss");
    }
	/**
	 * 获取当前日期一周前的日期
	 *
	 * @return
	 * @author wcb
	 * @date 2018-8-9 下午4:41:59
	 */
	public static String timeDateqt() {
	    Date dateNow = new Date();
	    Date dateBefore = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dateNow);
	    cal.add(Calendar.DAY_OF_MONTH, -7);
	    dateBefore = cal.getTime();
        return dateFormat(dateBefore, "yyyy-MM-dd");
    }
	/**
	 * 获取当前日期某月之前的日期
	 *
	 * @param month
	 * @return
	 * @author wcb
	 * @date 2018-8-9 下午4:43:19
	 */
	public static String timeQuantum(Integer month) {
        Date dateNow = new Date();
        Date dateBefore = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        cal.add(Calendar.MONTH, -month);
        dateBefore = cal.getTime();
        return dateFormat(dateBefore, "yyyy-MM-dd HH:mm:ss");
    }
	public static void main(String[] args) throws ParseException {
		System.out.println(dateFormat2("2018-07-12 17:35:56", "yyyy-MM-dd HH:mm:ss").getTime()>dateFormat2("2018-07-12 17:32:56", "yyyy-MM-dd HH:mm:ss").getTime());

		
	}
}