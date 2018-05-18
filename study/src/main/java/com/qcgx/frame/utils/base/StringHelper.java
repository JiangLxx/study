package com.qcgx.frame.utils.base;

import java.util.*;

import com.google.common.collect.Lists;
import com.qcgx.frame.utils.secu.CheckHelper;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.base.data.CollatorComparator;

/**
 * <p>字符串操作通用工具类</p>
 * @author FLY @date 2017-05-22<br>
 * @version 1.0<br>
 */
public final class StringHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private StringHelper() {}
	
	/**
	 * <p>生成UUID字符串</p>
	 * @return UUID字符串<br>
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * <p>获取字符串长度</p>
	 * @param str 字符串<br>
	 * @return 整数<br>
	 */
	public static int length(String str) {
		return StringHelper.isEmpty(str) ? 0 : str.length();
	}
	
	/**
	 * <p>获取字符串字节长度</p>
	 * @param str 字符串<br>
	 * @return 整数<br>
	 */
	public static int byteLen(String str) {
		int rtnI = 0;
		// 合法性判断
		if (StringHelper.isNotEmpty(str)) {
			// 循环计算字节长度
			for(int i = 0; i < str.length(); i ++) {
				rtnI += str.codePointAt(i) > 255 ? 2 : 1;
			}
		}
		return rtnI;
	}
	
	/**
	 * <p>字符串数组排序</p>
	 * @param array 字符串数组<br>
	 * @return 字符串数组<br>
	 */
	public static String[] sort(String[] array) {
		TreeMap<String, String> treeMap = null;
		if (CommHelper.isNotEmptyArray(array)) {
			treeMap = new TreeMap<String, String>(new CollatorComparator());
			// 将值装入排序容器
			int len = array.length, index = 0;
			for (index = 0; index < len; index ++) {
				treeMap.put(array[index], array[index]);
			}
			// 输出排序后的值
			array = new String[len]; index = 0;
			Iterator<String> iterator = treeMap.values().iterator();
			while (iterator.hasNext()) {
				array[index] = iterator.next(); index ++;
			}
		}
		return array;
	}
	
	/**
	 * <p>判断字符串为空</p>
	 * @param str 字符串<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0 || str.equals("undefined") || str.equals("unknown");
	}
	
	/**
	 * <p>判断字符串为空格字符串</p>
	 * @param str 字符串<br>
	 * @return true:空格字符串 false:非空格字符串<br>
	 */
	public static boolean isBlank(String str) {
		boolean rtnB = true;
		if (isNotEmpty(str)) {
			// 循环验证空格键
			for (int i = 0; i < str.length(); i ++) {
				if (!Character.isWhitespace(str.charAt(i))) {
					rtnB = false; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>判断字符串为非空</p>
	 * @param str 字符串<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmpty(String str) {
		return !StringHelper.isEmpty(str);
	}
	
	/**
	 * <p>判断字符串为非空格字符串</p>
	 * @param str 字符串<br>
	 * @return true:非空字符串 false:空格字符串<br>
	 */
	public static boolean isNotBlank(String str) {
		return !StringHelper.isBlank(str);
	}
	
	/**
	 * <p>判断字符串容器对象为空</p>
	 * @param buffer 字符串容器对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyBuffer(StringBuffer buffer) {
		return buffer == null || StringHelper.isEmpty(buffer.toString());
	}
	
	/**
	 * <p>判断字符串容器对象非空</p>
	 * @param buffer 字符串容器对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyBuffer(StringBuffer buffer) {
		return !StringHelper.isEmptyBuffer(buffer);
	}
	
	/**
	 * <p>获取合法字符串</p>
	 * @param str 字符串<br>
	 * @return 字符串<br>
	 */
	public static String getLegalString(String str) {
		return StringHelper.isEmpty(str) ? Constants.EMPTY_STRING : str;
	}
	
	/**
	 * <p>去除前后指定字符</p>
	 * @param src 字符串<br>
	 * @param trim 去除字符<br>
	 * @return 字符串<br>
	 */
	public static String trim(String src, String trim) {
		// 合法性判断
		if (StringHelper.isNotEmpty(src) && StringHelper.isNotEmpty(trim)) {
			// 首字符
			String beginChar = src.substring(0, 1);
			while (beginChar.equalsIgnoreCase(trim)) {
				src = src.substring(1, src.length());
				beginChar = src.substring(0, 1);
			}
			// 尾字符
			String endedChar = src.substring(src.length() - 1, src.length());
			while (endedChar.equalsIgnoreCase(trim)) {
				src = src.substring(0, src.length() - 1);
				endedChar = src.substring(src.length() - 1, src.length());
			}
		}
		return src;
	}
	
	/**
	 * <p>将字符串以指定的分隔符进行分割</p>
	 * @param str 字符串<br>
	 * @param delim 分隔符<br>
	 * @return 字符串数组<br>
	 */
	public static String[] split(String str, String delim) {
		String[] rtnA = null;
		// 合法性验证
		if (isNotEmpty(str) && isNotEmpty(delim)) {
			List<String> tempList = new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(str, delim);
			while(tokenizer.hasMoreTokens()) {
				tempList.add(tokenizer.nextToken());
			}
			rtnA = tempList.toArray(new String[]{});
		}
		return rtnA;
	}
	
	/**
	 * <p>定位字符串在数组中的位置</p>
	 * @param strs 数组字符串<br>
	 * @param find 字符串<br>
	 * @return 位置索引<br>
	 */
	public static int location(String[] strs, String find) {
		int index = -1;
		// 合法性判断
		if (CommHelper.isNotEmptyArray(strs)) {
			find = StringHelper.getLegalString(find);
			// 循环数组
			for (int i = 0; i < strs.length; i ++) {
				// 当值相等时，
				if(find.equalsIgnoreCase(strs[i])) {
					index = i; break;
				}
			}
		}
		return index;
	}
	
	/**
	 * <p>将数组字符串以分隔符进行组装</p>
	 * @param strs 字符串数组<br>
	 * @param delim  分隔符<br>
	 * @return 字符串<br>
	 */
	public static String join(String[] strs, String delim) {
		String rtnS = Constants.EMPTY_STRING;
		// 合法性判断
		if (CommHelper.isNotEmptyArray(strs)) {
			delim = StringHelper.getLegalString(delim);
			StringBuffer tempBuf = new StringBuffer();
			// 循环组装
			for (int i = 0; i < strs.length; i ++) {
				if (i == 0) {
					tempBuf.append(strs[i]);
				} else {
					tempBuf.append(delim).append(strs[i]);
				}
			}
			rtnS = tempBuf.toString();
		}
		return rtnS;
	}
	
	/**
	 * <p>根据分隔符将数组进行重新收集</p>
	 * @param stra 字符串数组<br>
	 * @param split 分隔符<br>
	 * @return 字符串数组<br>
	 */
	public static String[] split(String[] stra, String split) {
		String[] rtnA = null;
		// 合法性判断
		if (CommHelper.isNotEmptyArray(stra) && StringHelper.isNotEmpty(split)) {
			List<String> tempList = Lists.newArrayList();
			for (String str : stra) {
				str = str.replace("[", "").replace("]", "");
				// 根据分隔符处理
				if (str.indexOf(split) >= 0) {
					String[] tempA = StringHelper.split(str, split);
					for (String temp : tempA) {
						tempList.add(temp);
					}
				} else {
					tempList.add(str);
				}
			}
			rtnA = tempList.toArray(new String[] {});
		}
		return rtnA;
	}
	
	/**
	 * <p>将列表字符串以分隔符进行组装</p>
	 * @param list 字符串列表<br>
	 * @param delim  分隔符<br>
	 * @return 字符串<br>
	 */
	public static String join(List<String> list, String delim) {
		return CommHelper.isEmptyList(list) ? Constants.EMPTY_STRING : join(list.toArray(new String[]{}), delim);
	}
	
	/**
	 * <p>将首字符为字母的字符串首字母变为大写</p>
	 * @param str 字符串<br>
	 * @return 字符串<br>
	 */
	public static String firstCharToUpperCase(String str) {
		String rtnS = StringHelper.getLegalString(str);
		// 合法性验证
		if (StringHelper.isNotEmpty(str)) {
			String temp = str.substring(0, 1);
			if (CheckHelper.checkAlphabet(temp)) {
				rtnS = temp.toUpperCase().concat(str.substring(1));
			}
		}
		return rtnS;
	}
	
	/**
	 * <p>将首字符为字母的字符串首字母变为小写</p>
	 * @param str 字符串<br>
	 * @return 字符串<br>
	 */
	public static String firstCharToLowerCase(String str) {
		String rtnS = StringHelper.getLegalString(str);
		// 合法性验证
		if (StringHelper.isNotEmpty(str)) {
			String temp = str.substring(0, 1);
			if (CheckHelper.checkAlphabet(temp)) {
				rtnS = temp.toLowerCase().concat(str.substring(1));
			}
		}
		return rtnS;
	}
	
	/**
	 * <p>获取字符串在字符串中出现次数</p>
	 * @param str 字符串<br>
	 * @param find 字符串<br>
	 * @return 出现次数<br>
	 */
	public static int appearTimes(String str, String find) {
		int times = 0;
		// 合法性判断
		if (isNotEmpty(str) && isNotEmpty(find)) {
			int index = -1;
			while ((index = str.indexOf(find, index)) > -1) {
				++ index; ++ times;
			}
		}
		return times;
	}

	/**
	 * <p>将字符串根据字符串数组进行格式化</p>
	 * @param formatter 格式字符串<br>
	 * @param params 字符串数组<br>
	 * @return 字符串<br>
	 */
	public static String format(String formatter, String[] params) {
		String rtnS = getLegalString(formatter);
		// 合法性判断
		if (CommHelper.isNotEmptyArray(params)) {
			// 循环替换字符串
			for (int i =0; i < params.length; i ++) {
				rtnS = rtnS.replace("{".concat(String.valueOf(i)).concat("}"), params[i]);
			}
		}
		return rtnS;
	}
	
	/**
	 * <p>将字符串进行左补位操作</p>
	 * @param str 字符串<br>
	 * @param len 长  度<br>
	 * @param pad 补位符<br>
	 * @return 字符串<br>
	 */
	public static String leftPadding(String str, int len, String pad) {
		str = StringHelper.getLegalString(str);
		// 合法性判断
		if (len > 0 && StringHelper.isNotEmpty(pad)) {
			int length = str.length();
			// 循环补位
			for (int i = 0; i < len - length; i ++) {
				str = pad.concat(str);
			}
		}
		return str;
	}
	
	/**
	 * <p>将字符串进行右补位操作</p>
	 * @param str 字符串<br>
	 * @param len 长  度<br>
	 * @param pad 补位符<br>
	 * @return 字符串<br>
	 */
	public static String rightPadding(String str, int len, String pad) {
		str = StringHelper.getLegalString(str);
		// 合法性判断
		if (len > 0 && StringHelper.isNotEmpty(pad)) {
			int length = str.length();
			// 循环补位
			for (int i = 0; i < len - length; i ++) {
				str = str.concat(pad);
			}
		}
		return str;
	}
	
	/**
	 * 字符串替换<p>
	 * @param str 源字符串<br>
	 * @param replaced 被替换字符串<br>
	 * @param replace 替换为字符串<br>
	 * @return 字符串<br>
	 */
	public static String replaceAll(String str, String replaced, String replace) {
		String dest = "";
		if (isNotEmpty(str) && isNotEmpty(replaced)) {
			int replacedLen = replaced.length(), i;
			while((i = str.indexOf(replaced)) != -1) {
				dest = dest.concat(str.substring(0, i));
				dest = dest.concat(replace);
				str = str.substring(i + replacedLen);
			}
			dest = dest.concat(str);
		}
		return dest;
	}
}
