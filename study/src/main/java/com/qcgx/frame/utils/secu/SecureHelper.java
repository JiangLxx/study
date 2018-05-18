package com.qcgx.frame.utils.secu;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.qcgx.frame.utils.file.FileHelper;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;

/**
 * <p>通用安全工具类</p>
 * @author FLY @date 2017-02-05<br>
 * @version 1.0<br>
 */
public class SecureHelper {
	/** 正则匹配 **/
	private static Pattern pattern = null;
	
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private SecureHelper() {}
	
	/**
	 * <p>静态代码块</p>
	 */
	static {
		InputStream input = null;
		try {
			input = SecureHelper.class.getClassLoader().getResourceAsStream("keyword.properties");
			if (CommHelper.isNotNull(input)) {
				StringBuilder builder = new StringBuilder("");
				Properties props = new Properties(); props.load(input);
				// 循环组装
				Enumeration<?> enumeration = props.propertyNames();
				while (enumeration.hasMoreElements()) {
					builder.append(enumeration.nextElement()).append("|");
				}
				pattern = Pattern.compile(new String(builder.deleteCharAt(builder.length() - 1)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			FileHelper.closeIO(input);
		}
	}
	
	/**
	 * <p>防止SQL注入方式攻击数据库服务器</p>
	 * @param str SQL语句<br>
	 * @return true:正常 false:异常<br>
	 */
	public static boolean preventSQLInjection(String str) {
		boolean rtnB = true;
		// 判断字符串是否为空
		if (StringHelper.isNotEmpty(str)) {
			String words[] = StringHelper.split(Constants.SQL_INJECTION, "|");
			for (int i = 0; i < words.length; i ++) {
				if (str.indexOf(words[i]) >= 0 || str.indexOf(words[i].toUpperCase()) >= 0) {
					rtnB = false; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>执行敏感词过滤</p>
	 * @param str 源字符串<br>
	 * @return 过滤后的字符串<br>
	 */
	public static String sensitiveWordsFilter(String str) {
		return StringHelper.isNotEmpty(str) ? pattern.matcher(str).replaceAll("***") : str;
	}
	
	/**
	 * <p>过滤字符串中的特殊字符</p>
	 * @param str 字符串<br>
	 * @return 字符串<br>
	 */
	public static String specialCharacterFilter(String str) {
		return StringHelper.isNotEmpty(str) ? Pattern.compile(Constants.SPECIAL_CHAR).matcher(str).replaceAll("") : str;
	}
}
