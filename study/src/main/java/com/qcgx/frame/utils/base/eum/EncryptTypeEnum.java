package com.qcgx.frame.utils.base.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>ES加密方式枚举实现类</p>
 * @author FLY @date 2017-01-08<br>
 * @version 1.0<br>
 */
public enum EncryptTypeEnum implements CoreEnum<String, String> {
	DES("DES", "DES"), DES3("DES3", "DES3"), AES("AES", "AES"), RSA("RSA", "RSA");
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private EncryptTypeEnum(String value, String alias) {
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
	public EncryptTypeEnum[] getEnums() {
		return EncryptTypeEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public EncryptTypeEnum getEnumForAlias(String alias) {
		return (EncryptTypeEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public EncryptTypeEnum getEnumForValue(String value) {
		return (EncryptTypeEnum) EnumHelper.getEnumForValue(this, value);
	}
}
