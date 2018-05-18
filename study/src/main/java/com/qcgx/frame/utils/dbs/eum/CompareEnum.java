package com.qcgx.frame.utils.dbs.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>比较类型枚举实现类</p>
 * @author FLY  @date 2017-06-24<br>
 * @version 1.0<br>
 */
public enum CompareEnum implements CoreEnum<String, String> {
	COMPARE_EQUEAL("=", "等于"), COMPARE_LESS("<", "小于"),
	COMPARE_MORE(">", "大于"), COMPARE_LESS_EQUEAL("<=", "小于等于"),
	COMPARE_MORE_EQUEAL(">=", "大于等于"), COMPARE_IS_NULL("IS NULL", "等于空"),
	COMPARE_IN("IN", "包含"), COMPARE_NOT_IN("NOT IN", "不包含"),
	COMPARE_NOT_EQUEAL("<>", "不等于"), COMPARE_LIKE("ALL_LIKE", "全匹配"),
	COMPARE_LEFT_LIKE("LEFT_LIKE", "左匹配"),COMPARE_RIGHT_LIKE("RIGHT_LIKE", "右匹配");
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private CompareEnum(String value, String alias) {
		this.value = value; this.alias = alias;
	}
	
	/**
	 * <p>获取枚举对象真实值</p>
	 */
	public String getValue() {
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
	public CompareEnum[] getEnums() {
		return CompareEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public CompareEnum getEnumForAlias(String alias) {
		return (CompareEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public CompareEnum getEnumForValue(String value) {
		return (CompareEnum) EnumHelper.getEnumForValue(this, String.valueOf(value));
	}
}
