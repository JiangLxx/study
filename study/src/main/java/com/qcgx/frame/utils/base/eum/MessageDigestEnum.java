package com.qcgx.frame.utils.base.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>消息摘要类型枚举类</p>
 * @author FLY @date 2017-05-31<br>
 * @version 1.0<br>
 */
public enum MessageDigestEnum implements CoreEnum<String, String> {
	MD5("MD5", "MD5"), SHA("SHA", "SHA"), SHA1("SHA-1", "SHA-1"),
	SHA256("SHA-256", "SHA-256"), SHA384("SHA-384", "SHA-384"), SHA512("SHA-512", "SHA-512");
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private MessageDigestEnum(String value, String alias) {
		this.value = value;
		this.alias = alias;
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
	public MessageDigestEnum[] getEnums() {
		return MessageDigestEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public MessageDigestEnum getEnumForAlias(String alias) {
		return (MessageDigestEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public MessageDigestEnum getEnumForValue(String value) {
		return (MessageDigestEnum) EnumHelper.getEnumForValue(this, String.valueOf(value));
	}
}
