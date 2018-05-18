package com.qcgx.frame.utils.base;

import java.util.*;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>枚举类型数据通用操作类</p>
 * @author FLY @date 2017-05-25<br>
 * @version 1.0<br>
 */
public class EnumHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private EnumHelper() {}
	
	/**
	 * <p>获取枚举对象的散列值集合</p>
	 * @param coreEnum 枚举对象<br>
	 * @return 散列值集合<br>
	 */
	public static Map<?, ?> getHashMap(CoreEnum<?, ?> coreEnum) {
		HashMap<Object,Object> rtnmap = null;
		// 合法性判断
		CoreEnum<?, ?>[] array = null;
		if (CommHelper.isNotNull(coreEnum)) {
			rtnmap = new HashMap<Object,Object>();
			array = (CoreEnum<?, ?>[])coreEnum.getEnums();
			if (CommHelper.isNotEmptyArray(array)) {
				for(CoreEnum<?, ?> per : array) {
					rtnmap.put(per.getValue(), per.getAlias());
				}
			}
		}
		return rtnmap;
	}
	
	/**
	 * <p>根据显示值获取对应的枚举对象</p>
	 * @param coreEnum 枚举对象<br>
	 * @param alias 显示值<br>
	 * @return 枚举对象<br>
	 */
	public static CoreEnum<?, ?> getEnumForAlias(CoreEnum<?, ?> coreEnum, String alias) {
		CoreEnum<?, ?> rtnEnum = null; CoreEnum<?, ?>[] array = null;
		// 合法性判断
		if (StringHelper.isNotEmpty(alias) && CommHelper.isNotNull(coreEnum)) {
			array = (CoreEnum<?, ?>[])coreEnum.getEnums();
			// 循环枚举对象
			for(CoreEnum<?, ?> per :  array) {
				// 当匹配到合法对象，自动退出
				if (alias.equalsIgnoreCase(String.valueOf(per.getAlias()))) {
					rtnEnum = per; break;
				}
			}
		}
		return rtnEnum;
	}
	
	/**
	 * <p>根据真实值获取对应的枚举对象</p>
	 * @param coreEnum 枚举对象<br>
	 * @param value 真实值<br>
	 * @return 枚举对象<br>
	 */
	public static CoreEnum<?, ?> getEnumForValue(CoreEnum<?, ?> coreEnum, String value) {
		CoreEnum<?, ?> rtnEnum = null; CoreEnum<?, ?>[] array = null;
		// 合法性判断
		if (StringHelper.isNotEmpty(value) && CommHelper.isNotNull(coreEnum)) {
			array = (CoreEnum<?, ?>[])coreEnum.getEnums();
			// 循环枚举对象
			for(CoreEnum<?, ?> per :  array) {
				// 当匹配到合法对象，自动退出
				if (value.equalsIgnoreCase(String.valueOf(per.getValue()))) {
					rtnEnum = per; break;
				}
			}
		}
		return rtnEnum;
	}
	
	/**
	 * <p>根据值数组获取枚举对象的散列值集合</p>
	 * @param coreEnum 枚举对象<br>
	 * @param values 值数组<br>
	 * @return 散列值集合<br>
	 */
	public static Map<?, ?> getHashMapForValues(CoreEnum<?, ?> coreEnum, String[] values) {
		HashMap<Object,Object> rtnmap = null; CoreEnum<?, ?>[] array = null;
		// 合法性验证
		if (CommHelper.isNotNull(coreEnum) && CommHelper.isNotEmptyArray(values)) {
			rtnmap = new HashMap<Object,Object>();
			array = (CoreEnum<?, ?>[])coreEnum.getEnums();
			for (String value : values) {
				// 循环枚举对象
				for(CoreEnum<?, ?> per :  array) {
					// 当匹配到合法对象，自动退出
					if (value.equalsIgnoreCase(String.valueOf(per.getValue()))) {
						rtnmap.put(per.getValue(), per.getAlias()); break;
					}
				}
			}
		}
		return rtnmap;
	}
	
	/**
	 * <p>根据值数组获取枚举对象的散列值集合</p>
	 * @param coreEnum 枚举对象<br>
	 * @param aliases 值数组<br>
	 * @return 散列值集合<br>
	 */
	public static Map<?, ?> getHashMapForAliases(CoreEnum<?, ?> coreEnum, String[] aliases) {
		HashMap<Object,Object> rtnmap = null; CoreEnum<?, ?>[] array = null;
		// 合法性验证
		if (CommHelper.isNotNull(coreEnum) && CommHelper.isNotEmptyArray(aliases)) {
			rtnmap = new HashMap<Object,Object>();
			array = (CoreEnum<?, ?>[])coreEnum.getEnums();
			for (String alias : aliases) {
				// 循环枚举对象
				for(CoreEnum<?, ?> per :  array) {
					// 当匹配到合法对象，自动退出
					if (alias.equalsIgnoreCase(String.valueOf(per.getAlias()))) {
						rtnmap.put(per.getValue(), per.getAlias()); break;
					}
				}
			}
		}
		return rtnmap;
	}
}
