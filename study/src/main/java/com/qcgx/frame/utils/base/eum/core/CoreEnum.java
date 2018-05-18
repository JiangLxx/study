package com.qcgx.frame.utils.base.eum.core;

/**
 * <p>枚举类型通用接口类</p>
 * @author FLY @date 2017-05-25<br>
 * @version 1.0<br>
 */
public interface CoreEnum<V, D> {
	/**
	 * <p>获取枚举对象真实值</p>
	 * @return 枚举对象真实值<br>
	 */
	public V getValue();
	
	/**
	 * <p>获取枚举对象显示值</p>
	 * @return 枚举对象显示值<br>
	 */
	public D getAlias();
	
	/**
	 * <p>获取枚举对象数组</p>
	 * @return 枚举对象数组<br>
	 */
	public Enum<?>[] getEnums();
	
	/**
	 * <p>根据真实值获取枚举对象</p>
	 * @param value 真实值<br>
	 * @return 枚举对象<br>
	 */
	public Enum<?> getEnumForValue(V value);
	
	/**
	 * <p>根据显示值获取枚举对象</p>
	 * @param alias 显示值<br>
	 * @return 枚举对象<br>
	 */
	public Enum<?> getEnumForAlias(D alias);
}
