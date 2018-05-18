package com.qcgx.frame.utils.base.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>英文星期枚举实现类</p>
 * @author FLY @date 2017-05-25<br>
 * @version 1.0<br>
 */
public enum EnglishWeekEnum implements CoreEnum<Integer, String> {
	MONDAY(2, "Monday"), TUESDAY(3, "Tuesday"), WEDNESDAY(4, "Wednesday"),
	THURSDAY(5, "Thursday"), FRIDAY(6, "Friday"), SATURDAY(7, "Saturday"), SUNDAY(1, "Sunday");
	/** 真实值 **/
	private Integer value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private EnglishWeekEnum(Integer value, String alias) {
		this.value = value;
		this.alias = alias;
	}
	
	/**
	 * <p>获取枚举对象真实值</p>
	 */
	public Integer getValue() {
		return value;
	}
	
	/**
	 * <p>获取枚举对象显示值</p>
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * <p>获取枚举对象数组</p>
	 */
	public EnglishWeekEnum[] getEnums() {
		return EnglishWeekEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public EnglishWeekEnum getEnumForAlias(String alias) {
		return (EnglishWeekEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public EnglishWeekEnum getEnumForValue(Integer value) {
		return (EnglishWeekEnum) EnumHelper.getEnumForValue(this, String.valueOf(value));
	}
}
