package com.qcgx.frame.utils.base.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>中文星期枚举实现类</p>
 * @author LiuFengYong @date 2017-05-25<br>
 * @version 1.0<br>
 */
public enum ChineseWeekEnum implements CoreEnum<Integer, String> {
	MONDAY(2, "星期一"), TUESDAY(3, "星期二"), WEDNESDAY(4, "星期三"),
	THURSDAY(5, "星期四"), FRIDAY(6, "星期五"), SATURDAY(7, "星期六"),
	SUNDAY(1, "星期天");
	/** 真实值 **/
	private Integer value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private ChineseWeekEnum(Integer value, String alias) {
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
	public ChineseWeekEnum[] getEnums() {
		return ChineseWeekEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public ChineseWeekEnum getEnumForAlias(String alias) {
		return (ChineseWeekEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public ChineseWeekEnum getEnumForValue(Integer value) {
		return (ChineseWeekEnum) EnumHelper.getEnumForValue(this, String.valueOf(value));
	}
}
