package com.qcgx.frame.utils.dbs.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>排序方式枚举类</p>
 * @author FLY @date 2017-06-25<br>
 * @version 1.0<br>
 */
public enum OrderTypeEnum implements CoreEnum<String, String> {
	ASC("ASC", "升序"), DESC("DESC", "降序");
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private OrderTypeEnum(String value, String alias) {
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
	public OrderTypeEnum[] getEnums() {
		return OrderTypeEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public OrderTypeEnum getEnumForAlias(String alias) {
		return (OrderTypeEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public OrderTypeEnum getEnumForValue(String value) {
		return (OrderTypeEnum) EnumHelper.getEnumForValue(this, value);
	}
}
