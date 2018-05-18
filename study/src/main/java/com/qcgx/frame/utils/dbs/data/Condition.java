package com.qcgx.frame.utils.dbs.data;

import java.util.*;
import java.io.Serializable;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.dbs.data.base.OrderParameter;
import com.qcgx.frame.utils.dbs.data.base.ConditionParameter;

/**
 * <p>查询条件信息类</p>
 * @author FLY @date 2017-06-24<br>
 * @version 1.0<br>
 */
public class Condition implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 1748536979082940149L;
	/** 条件组装样式串 **/
	private String maskString = "";
	/** 分组字段容器 **/
	private HashSet<String> groups = null;
	/** 排序信息容器 **/
	private List<OrderParameter> orders = null;
	/** 条件信息容器 **/
	private List<ConditionParameter> conditions = null;
	
	/**
	 * <p>清空条件信息对象</p>
	 */
	public void clear() {
		maskString = "";
		if (CommHelper.isNotEmptyList(orders)) orders.clear();
		if (CommHelper.isNotEmptySet(groups)) groups.clear();
		if (CommHelper.isNotEmptyList(conditions)) conditions.clear();
	}
	
	/**
	 * <p>收集分组字段信息</p>
	 * @param group 分组字段信息<br>
	 */
	public void put(String group) {
		// 容器为空则初始化
		if (CommHelper.isEmptySet(groups)) {
			groups = new HashSet<String>();
		}
		groups.add(group);
	}
	
	/**
	 * <p>收集排序字段信息</p>
	 * @param order 排序字段信息<br>
	 */
	public void put(OrderParameter order) {
		// 容器为空则初始化
		if (CommHelper.isEmptyList(orders)) {
			orders = new ArrayList<OrderParameter>();
		}
		// 元素不存在则添加
		if (orders.contains(order) == false) {
			orders.add(order);
		}
	}
	
	/**
	 * <p>收集查询条件信息</p>
	 * @param param 条件对象<br>
	 */
	public void put(ConditionParameter param) {
		// 容器为空则初始化
		if (CommHelper.isEmptyList(conditions)) {
			conditions = new ArrayList<ConditionParameter>();
		}
		// 元素存在则删除
		if (conditions.contains(param)) {
			conditions.remove(param);
		}
		conditions.add(param);
	}
	
	/**
	 * <p>移除分组字段信息</p>
	 * @param group 分组字段信息<br>
	 */
	public void remove(String group) {
		if (CommHelper.isNotEmptySet(groups)) groups.remove(group);
	}
	
	/**
	 * <p>移除排序字段信息</p>
	 * @param order 排序字段信息<br>
	 */
	public void remove(OrderParameter order) {
		if (CommHelper.isNotEmptyList(orders)) orders.remove(order);
	}
	
	/**
	 * <p>移除条件参数信息</p>
	 * @param param 条件参数信息<br>
	 */
	public void remove(ConditionParameter param) {
		if (CommHelper.isNotEmptyList(conditions)) conditions.remove(param);
	}
	
	/**
	 * <p>融合查询信息对象</p>
	 * @param condition 查询信息<br>
	 */
	public void merge(Condition condition) {
		// 融合分组信息
		HashSet<String> groupSet = condition.getGroups();
		if (CommHelper.isNotEmptySet(groupSet)) {
			groups.addAll(groupSet);
		}
		// 融合排序信息
		List<OrderParameter> orderList = condition.getOrders();
		if (CommHelper.isNotEmptyList(orderList)) {
			orders.addAll(orderList);
		}
		// 融合条件信息
		List<ConditionParameter> condList = condition.getConditions();
		if (CommHelper.isNotEmptyList(condList)) {
			conditions.addAll(condList);
		}
	}

	/**
	 * <p>获取分组字段容器信息</p>
	 * @return 分组字段容器信息<br>
	 */
	public HashSet<String> getGroups() {
		return groups;
	}

	/**
	 * <p>获取排序字段容器信息</p>
	 * @return 排序字段容器信息<br>
	 */
	public List<OrderParameter> getOrders() {
		return orders;
	}

	/**
	 * <p>获取条件参数容器信息</p>
	 * @return 条件参数容器信息<br>
	 */
	public List<ConditionParameter> getConditions() {
		return conditions;
	}
	
	/**
	 * <p>设置条件组装样式串</p>
	 * @param maskString 条件组装样式串<br>
	 */
	public void setMaskString(String maskString) {
		this.maskString = maskString;
	}

	/**
	 * <p>重写父类方法</p>
	 */
	public String toString() {
		StringBuffer condition = new StringBuffer("");
		// 组装条件参数
		if (CommHelper.isNotEmptyList(conditions)) {
			condition.append(" ").append("WHERE").append(" ");
			// 当条件组装样式非空时，组装查询条件
			if (StringHelper.isNotEmpty(maskString)) {
				for (int i = 0; i < conditions.size(); i ++) {
					maskString = maskString.replace("#".concat(String.valueOf(i + 1)), conditions.get(i).toString().toUpperCase());
				}
				condition.append(maskString);
			} else {
				for (int i = 0; i < conditions.size(); i ++) {
					if (i == 0) {
						condition.append(conditions.get(i).toString().toUpperCase()).append(" ");
					} else {
						condition.append("AND ").append(conditions.get(i).toString().toUpperCase()).append(" ");
					}
				}
			}
			// 组装分组信息
			if (CommHelper.isNotEmptySet(groups)) {
				condition.append("GROUP BY ");
				Iterator<String> iterator = groups.iterator(); boolean first = true;
				while (iterator.hasNext()) {
					if (first) {
						condition.append(iterator.next().toUpperCase()).append(" "); first = false;
					} else {
						condition.append(", ").append(iterator.next().toUpperCase()).append(" ");
					}
				}
			}
			// 组装排序信息
			if (CommHelper.isNotEmptyList(orders)) {
				condition.append(" ORDER BY ");
				for (int i = 0; i < orders.size(); i ++) {
					if (i == 0) {
						condition.append(orders.get(i).toString().toUpperCase()).append(" ");
					} else {
						condition.append(", ").append(orders.get(i).toString().toUpperCase()).append(" ");
					}
				}
			}
		}
		return condition.toString();
	}
}
