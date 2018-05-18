package com.qcgx.frame.utils.base;

import java.util.*;
import java.text.*;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.secu.CheckHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.base.eum.EnglishWeekEnum;
import com.qcgx.frame.utils.base.eum.ChineseWeekEnum;

/**
 * <p>日期类型数据通用操作工具类</p>
 * @author FLY @date 2017-05-23<br>
 * @version 1.0<br>
 */
public class DateHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private DateHelper() {}
	
	/**
	 * 获取当前时间的字符串<p>
	 * @return 字符串<br>
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		int tempMins = calendar.get(Calendar.MINUTE);
		int tempHour = calendar.get(Calendar.HOUR_OF_DAY);
		String mins = StringHelper.leftPadding(String.valueOf(tempMins), 2, "0");
		String hour = StringHelper.leftPadding(String.valueOf(tempHour), 2, "0");
		return hour.concat(":").concat(mins);
	}
	
	/**
	 * <p>将字符串转换为日期对象</p>
	 * @param str 日期字符串<br>
	 * @return 日期对象<br>
	 */
	public static Date getDate(String str) {
		Date rtnDate = null;
		try {
			// 字符串合法性判断
			if (StringHelper.isNotEmpty(str)) {
				// 变量声明
				Map.Entry<?, ?> entry = null; 
				// 日期字符串格式合法性判断
				Iterator<?> iterator = Constants.DATE_STYLE_MAP.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<?, ?>) iterator.next();
					if (str.matches(entry.getKey().toString())) {
						rtnDate = new SimpleDateFormat(entry.getValue().toString()).parse(str); break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取日期的毫秒数</p>
	 * @param date 日期实例<br>
	 * @return 毫秒数<br>
	 */
	public static long getTime(Date date) {
		return CommHelper.isNull(date) ? 0 : date.getTime();
	}
		
	/**
	 * <p>获取日期对象的号数</p>
	 * @param date 日期对象<br>
	 * @return 日期号数<br>
	 */
	public static int getDay(Date date) {
		return CommHelper.isNull(date) ? Constants.ZERO_INT : getCalendar(date).get(Calendar.DATE);
	}
	
	/**
	 * <p>获取日期对象的星期</p>
	 * @param date 日期对象<br>
	 * @return 星期<br>
	 */
	public static int getWeek(Date date) {
		return CommHelper.isNull(date) ? Constants.ZERO_INT : getCalendar(date).get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * <p>获取日期对象的月份</p>
	 * @param date 日期对象<br>
	 * @return 日期月份<br>
	 */
	public static int getMonth(Date date) {
		return CommHelper.isNull(date) ? Constants.ZERO_INT : getCalendar(date).get(Calendar.MONTH) + 1;
	}
	
	/**
	 * <p>获取日期对象的年份</p>
	 * @param date 日期对象<br>
	 * @return 日期年份<br>
	 */
	public static int getYear(Date date) {
		return CommHelper.isNull(date) ? Constants.ZERO_INT : getCalendar(date).get(Calendar.YEAR);
	}
	
	/**
	 * <p>获取日期对象月份的天数</p>
	 * @param date 日期对象<br>
	 * @return 天数<br>
	 */
	public static int getMonthDays(Date date) {
		return CommHelper.isNull(date) ? Constants.ZERO_INT  : getCalendar(date).getActualMaximum(Calendar.DATE);
	}
	
	/**
	 * <p>获取日期对象年份的天数</p>
	 * @param date 日期对象<br>
	 * @return 天数<br>
	 */
	public static int getYearDays(Date date) {
		return CommHelper.isNull(date) ? Constants.ZERO_INT : CheckHelper.checkLeapYear(getYear(date)) ? 366 : 365;
	}
	
	/**
	 * <p>获取日期月份第一天</p>
	 * @param date 日期对象<br>
	 * @return 日期对象<br>
	 */
	public static Date getFirstDate(Date date) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.set(Calendar.DATE, 1);
			rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取日期月份最后一天</p>
	 * @param date 日期对象<br>
	 * @return 日期对象<br>
	 */
	public static Date getLastDate(Date date) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.set(Calendar.DATE, 1);
			calendar.roll(Calendar.DATE, -1);
			rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取日期所在周星期一的日期对象</p>
	 * @param date 日期对象<br>
	 * @return 日期对象<br>
	 */
	public static Date getMondayDate(Date date) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, Calendar.MONDAY);
			rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取日期所在周星期日的日期对象</p>
	 * @param date 日期对象<br>
	 * @return 日期对象<br>
	 */
	public static Date getSundayDate(Date date) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.add(Calendar.DATE, 7); rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取合法的日期对象:当日期对象为空时，返回当前日期</p>
	 * @param date 日期对象<br>
	 * @return 日期对象<br>
	 */
	public static Date getLegalDate(Date date) {
		return CommHelper.isNull(date) ? new Date() : date;
	}
	
	/**
	 * <p>将日期对象转换为日历对象:当日期对象为空时，返回当前日期的日历对象</p>
	 * @param date 日期对象<br>
	 * @return 日历对象<br>
	 */
	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getLegalDate(date)); return calendar;
	}
	
	/**
	 * <p>获取指定日期的年月字符串</p>
	 * @param date 日期对象<br>
	 * @return 年月字符串<br>
	 */
	public static String getYearMonth(Date date) {
		String rtnStr = "";
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date); rtnStr += calendar.get(Calendar.YEAR);
			rtnStr += StringHelper.leftPadding(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2, "0");
		}
		return rtnStr;
	}
	
	/**
	 * <p>日期对象增加|减少天数</p>
	 * @param date 日期对象<br>
	 * @param days 天数<br>
	 * @return 日期对象<br>
	 */
	public static Date addDays(Date date, int days) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.add(Calendar.DATE, days);
			rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取指定日期的第二天凌晨时间</p>
	 * @param date 指定时间<br>
	 * @return 凌晨时间<br>
	 */
	public static Date getNextMorning(Date date) {
		return DateHelper.getDate(DateHelper.format(DateHelper.addDays(date, 1), "yyyy-MM-dd"));
	}
	
	/**
	 * <p>日期对象增加|减少月数</p>
	 * @param date 日期对象<br>
	 * @param months 指定月数<br>
	 * @return 日期对象<br>
	 */
	public static Date addMonths(Date date, int months) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.add(Calendar.MONTH, months);
			rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>指定日期增加|减少指定年数</p>
	 * @param date 日期对象<br>
	 * @param years 指定年数<br>
	 * @return 日期对象<br>
	 */
	public static Date addYears(Date date, int years) {
		Date rtnDate = null;
		// 合法性判断
		if (CommHelper.isNotNull(date)) {
			Calendar calendar = getCalendar(date);
			calendar.add(Calendar.YEAR, years);
			rtnDate = calendar.getTime();
		}
		return rtnDate;
	}
	
	/**
	 * <p>根据日期格式格式化日期对象</p>
	 * @param date 日期对象<br>
	 * @param format 格式字符串<br>
	 * @return 日期字符串<br>
	 */
	public static String format(Date date, String format) {
		String rtnStr = Constants.EMPTY_STRING;
		try {
			// 合法性判断
			if (CommHelper.isNotNull(date) && CheckHelper.checkDateFormat(format)) {
				rtnStr = new SimpleDateFormat(format).format(date);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnStr;
	}
	
	/**
	 * <p>根据日期格式转换为日期对象</p>
	 * @param date 日期字符串<br>
	 * @param format 日期格式<br>
	 * @return 日期对象<br>
	 */
	public static Date getDate(String date, String format) {
		Date rtnDate = null;
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(date) && CheckHelper.checkDateFormat(format)) {
				rtnDate = new SimpleDateFormat(format).parse(date);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnDate;
	}
	
	/**
	 * <p>获取日期对象间隔的天数</p>
	 * @param begin 开始日期<br>
	 * @param ended 结束日期<br>
	 * @return 间隔天数<br>
	 */
	public static double getDays(Date begin, Date ended) {
		return CommHelper.isNotNull(begin) && CommHelper.isNotNull(ended) ? (ended.getTime() - begin.getTime()) / 86400000.0 : 0.0d;
	}
	
	/**
	 * <p>获取日期区间内的工作日:未排除法定节假日</p>
	 * @param begin 开始日期<br>
	 * @param ended 结束日期<br>
	 * @return 工作日列表对象<br>
	 */
	public static List<Date> getWorkDates(Date begin, Date ended) {
		List<Date> rtnList = null;
		// 合法性判断
		if (CommHelper.isNotNull(begin) && CommHelper.isNotNull(ended)) {
			rtnList = new ArrayList<Date>();
			Calendar current = getCalendar(begin);
			Calendar enddate = getCalendar(ended);
			// 区间循环获取工作日
			int weekday = 0;
			while (current.compareTo(enddate) <= 0) {
				weekday = current.get(Calendar.DAY_OF_WEEK);
				if (weekday != 1 && weekday != 7) {
					rtnList.add(current.getTime());
				}
				current.add(Calendar.DAY_OF_YEAR, 1);
			}
		}
		return rtnList;
	}
	
	/**
	 * <p>根据日期对象获取中文星期枚举对象，默认返回当前日期的星期枚举</p>
	 * @param date 日期对象<br>
	 * @return 中文日期枚举<br>
	 */
	public static ChineseWeekEnum getChineseWeekEnum(Date date) {
		return  (ChineseWeekEnum) EnumHelper.getEnumForValue(ChineseWeekEnum.MONDAY, String.valueOf(getWeek(getLegalDate(date))));
	}
	
	/**
	 * <p>根据日期对象获取英文星期枚举对象，默认返回当前日期的星期枚举</p>
	 * @param date 日期对象<br>
	 * @return 中文日期枚举<br>
	 */
	public static EnglishWeekEnum getEnglishWeekEnum(Date date) {
		return  (EnglishWeekEnum) EnumHelper.getEnumForValue(EnglishWeekEnum.MONDAY, String.valueOf(getWeek(getLegalDate(date))));
	}
}
