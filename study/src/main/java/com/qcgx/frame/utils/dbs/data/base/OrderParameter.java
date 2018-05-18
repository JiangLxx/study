package com.qcgx.frame.utils.dbs.data.base;

import java.io.Serializable;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.dbs.eum.OrderTypeEnum;

/**
 * <p>排序参数实现类</p>
 * @author FLY @date 2017-06-24<br>
 * @version 1.0<br>
 */
public class OrderParameter  implements Serializable{
	/** 默认版本编号 **/
	private static final long serialVersionUID = 2207116912543828469L;
	/** 字段名称 **/
	private String field;
	/** 排序类型 **/
	private OrderTypeEnum orderType;
	
	/**
	 * <p>获取字段名称</p>
	 * @return 字段名称<br>
	 */
	public String getField() {
		return field;
	}

	/**
	 * <p>设置字段名称</p>
	 * @param field 字段名称<br>
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * <p>获取排序类型</p>
	 * @return 排序类型<br>
	 */
	public OrderTypeEnum getOrderType() {
		return orderType;
	}
	
	/**
	 * <p>设置排序类型</p>
	 * @param orderType 排序类型<br>
	 */
	public void setOrderType(OrderTypeEnum orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * <p>重载函数:对象转换为字符串</p>
	 */
	public String toString() {
		return field.concat(" ").concat(orderType.getValue());
	}
	
	/**
	 * <p>重载父类方法</p>
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OrderParameter))
			return false;
		final OrderParameter order = (OrderParameter) obj;
		if (!order.getField().equals(getField())) return false;
		return true;
	}
	
	/**
	 * <p>重载父类方法</p>
	 */
	public int hashCode() {
		int hashCode = super.hashCode();
		// 字段名
		if (StringHelper.isNotEmpty(field)) {
			hashCode += field.hashCode();
		}
		// 排序类型
		if (CommHelper.isNotNull(orderType)) {
			hashCode += orderType.hashCode();
		}
		return hashCode;
	}

	/**
	 * <p>构造函数:初始化相关参数</p>
	 * @param field 字段名称<br>
	 * @param orderType 排序类型<br>
	 */
	public OrderParameter(String field, OrderTypeEnum orderType) {
		this.field = field; this.orderType = orderType;
	}
}
