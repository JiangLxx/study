package com.qcgx.frame.utils.base;

/**
 * 布尔类型数据通用操作工具类<p>
 * @author FLY @date 2017-05-23<br>
 * @version 1.0<br>
 */
public class BooleanHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private BooleanHelper() {}
	
	/**
	 * <p>将字符串转换为布尔值</p>
	 * @param bool 字符串<br>
	 * @return 布尔值<br>
	 */
	public static boolean getBoolean(String bool) {
		return "true".equalsIgnoreCase(bool) || "1".equals(bool) || "是".equals(bool) || "yes".equalsIgnoreCase(bool) || "y".equalsIgnoreCase(bool) || "t".equalsIgnoreCase(bool);
	}
	
	/**
	 * <p>将布尔值转换为布尔字符串</p>
	 * @param bool 布尔值<br>
	 * @return 字符串[true|false]<br>
	 */
	public static String getBooleanString(boolean bool) {
		return bool ? "true" : "false";
	}
	
	/**
	 * <p>将布尔值转换为数字字符串</p>
	 * @param bool 布尔值<br>
	 * @return 数字字符串[1|0]<br>
	 */
	public static String getIntegerString(boolean bool) {
		return bool ? "1" : "0";
	}
	
	/**
	 * <p>将布尔值转换为中文字符串</p>
	 * @param bool 布尔值<br>
	 * @return 中文字符串[是|非]<br>
	 */
	public static String getChineseString(boolean bool) {
		return bool ? "是" : "非";
	}
	
	/**
	 * <p>将布尔值转换为英文字符串</p>
	 * @param bool 布尔值<br>
	 * @return 英文字符串[yes|no]<br>
	 */
	public static String getEnglishString(boolean bool) {
		return bool ? "yes" : "no";
	}
}
