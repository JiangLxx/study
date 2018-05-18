package com.qcgx.frame.utils.dbs.eum;

import com.qcgx.frame.utils.base.EnumHelper;
import com.qcgx.frame.utils.base.eum.core.CoreEnum;

/**
 * <p>数据库枚举实现类</p>
 * @author FLY 2017-06-25<br>
 * @version 1.0<br>
 */
public enum DatabaseEnum implements CoreEnum<String, String> {
	DB2("DB2", "DB2"), MYSQL("MYSQL", "MYSQL"), ORACLE("ORACLE", "ORACLE"),  MISSQL("MISSQL", "MISSQL");
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * <p>构造函数:初始化枚举对象参数</p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private DatabaseEnum(String value, String alias) {
		this.value = value; this.alias = alias;
	}

	/**
	 * <p>获取枚举对象真实值</p>
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * <p>取枚举对象显示值</p>
	 */
	public String getAlias() {
		return alias;
	}
	
	/**
	 * <p>获取枚举对象数组</p>
	 */
	public DatabaseEnum[] getEnums() {
		return DatabaseEnum.values();
	}
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 */
	public DatabaseEnum getEnumForAlias(String alias) {
		return (DatabaseEnum) EnumHelper.getEnumForAlias(this, alias);
	}
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 */
	public DatabaseEnum getEnumForValue(String value) {
		return (DatabaseEnum) EnumHelper.getEnumForValue(this, value);
	}
}
