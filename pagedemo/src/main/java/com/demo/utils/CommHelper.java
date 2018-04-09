package com.demo.utils;

import java.util.*;
import javax.script.*;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.idiot.utils.base.StringHelper;

/**
 * <p>通用操作工具类</p>
 * @author FLY @date 2017-05-22<br>
 * @version 1.0<br>
 */
@SuppressWarnings("restriction")
public final class CommHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private CommHelper() {}
	
	/**
	 * <p>获取字符串计算式值</p>
	 * @param str 计算式<br>
	 * @return 计算结果<br>
	 */
	public static Object execute(String str)  {
		Object rtnO = null;
		try {
			if (StringHelper.isNotEmpty(str)) {
				rtnO = new ScriptEngineManager().getEngineByName("nashorn").eval(str);
			}
		} catch (ScriptException ex) {
			ex.printStackTrace();
		}
		return rtnO;
	}
	
	/**
	 * <p>判断指定的对象为空</p>
	 * @param obj 对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	/**
	 * <p>判断指定的对象为非空</p>
	 * @param obj 对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}
	
	/**
	 * <p>判断集合对象为空</p>
	 * @param set 集合对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptySet(Set<?> set) {
		return set == null || set.size() == 0;
	}
	
	/**
	 * <p>判断集合对象非空</p>
	 * @param set 集合对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptySet(Set<?> set) {
		return set != null && set.size() > 0;
	}
	
	/**
	 * <p>判断散列对象为空</p>
	 * @param map 散列对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyMap(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}
	
	/**
	 * <p>判断散列对象非空</p>
	 * @param map 散列对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyMap(Map<?, ?> map) {
		return map != null && map.size() > 0;
	}
	
	/**
	 * <p>判断整型数组为空</p>
	 * @param array 整型数组<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyIntArray(int[] array) {
		return array == null || array.length == 0;
	}
	
	/**
	 * <p>判断整型数组位非空</p>
	 * @param array 整型数组<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyIntArray(int[] array) {
		return array != null && array.length > 0;
	}
	
	/**
	 * <p>判断字节数组为已空</p>
	 * @param bytes 字节数组<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyByteArray(byte[] bytes) {
		return bytes == null  || bytes.length == 0;
	}
	
	/**
	 * <p>判断字节数组非空</p>
	 * @param bytes 字节数组<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyByteArray(byte[] bytes) {
		return bytes != null  && bytes.length > 0;
	}
	
	/**
	 * <p>判断列表对象为空</p>
	 * @param list 列表对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyList(List<?> list) {
		return list == null || list.size() == 0;
	}
	
	/**
	 * <p>判断列表对象非空</p>
	 * @param list 列表对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyList(List<?> list) {
		return list != null && list.size() > 0;
	}
	
	/**
	 * <p>判断数组对象为空</p>
	 * @param array 数组对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyArray(Object[] array) {
		return array == null || array.length == 0;
	}
	
	/**
	 * <p>判断数组对象非空</p>
	 * @param array 数组对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyArray(Object[] array) {
		return !CommHelper.isEmptyArray(array);
	}
	
	/**
	 * <p>根据哈希值获取哈希键</p>
	 * @param map 哈希集<br>
	 * @param value 哈希值<br>
	 * @return 哈希键<br>
	 */
	public static String getMapKey(Map<?, ?> map, Object value) {
		String rtnKey = "";
		// 合法性校验
		if (isNotEmptyMap(map) && isNotNull(value)) {
			for(Entry<?, ?> entry : map.entrySet()) {
				if (value.equals(entry.getValue())) {
					rtnKey = (String) entry.getKey(); break;
				}
			}
		}
		return rtnKey;
	}
}
