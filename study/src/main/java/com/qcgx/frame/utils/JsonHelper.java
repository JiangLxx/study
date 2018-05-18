package com.qcgx.frame.utils;

import java.util.Map;
import java.util.List;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import com.qcgx.frame.utils.base.StringHelper;

/**
 * <p>JSON字符串通用工具类</p>
 * @author FLY @date 2017-12-27<br>
 * @version 1.0<br>
 */
@SuppressWarnings("deprecation")
public class JsonHelper {
	/** 日志书写对象 **/
	private static Logger logger = Logger.getLogger(JsonHelper.class);
	
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private JsonHelper() {}
	
	/**
	 * <p>将JSON字符串转换为对象数组</p>
	 * @param json JSON字串<br>
	 * @param clzz 字节对象<br>
	 * @return 对象数组<br>
	 */
	public static List<?> jsonToList(String json, Class<?> clzz) {
		List<?> rtnList = null;
		// 合法性判断
		try {
			if (StringHelper.isNotEmpty(json) && CommHelper.isNotNull(clzz)) {
				rtnList = (List<?>) JSONArray.toCollection(JSONArray.fromObject(json), clzz);
			}
		} catch (Exception ex) {
			ex.printStackTrace(); logger.error(ex);
		}
		return rtnList;
	}
	
	/**
	 * <p>将JSON字符串转换为复杂对象数组</p>
	 * @param json JSON字符串<br>
	 * @param clzz 字节对象组<br>
	 * @param clzMap 子属性字节集<br>
	 * @return
	 */
	public static List<?> jsonToList(String json, Class<?> clzz, Map<String, Class<?>> clzMap) {
		List<?> rtnList = null;
		// 合法性判断
		try {
			// 合法性判断
			if (StringHelper.isNotEmpty(json) && CommHelper.isNotNull(clzz)) {
				if (CommHelper.isEmptyMap(clzMap)) {
					rtnList = (List<?>) JSONArray.toList(JSONArray.fromObject(json), clzz);
				} else {
					rtnList = JSONArray.toList(JSONArray.fromObject(json), clzz, clzMap);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace(); logger.error(ex);
		}
		return rtnList;
	}
}
