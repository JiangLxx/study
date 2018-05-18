package com.qcgx.frame.utils.dbs.data.base;

import java.io.Serializable;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.dbs.eum.CompareEnum;

/**
 * <p>数值比较参数实现类</p>
 * @author FLY @date 2017-06-24<br>
 * @version 1.0<br>
 */
public class ConditionParameter implements Serializable {
	/*** 默认版本编号 **/
	private static final long serialVersionUID = 5288660476394592364L;
	/** 字段名 **/
	private String field;
	/** 字段值 **/
	private Object value;
	/** 比较类型 **/
	private CompareEnum compare;
	
	/**
	 * <p>获取字段名</p>
	 * @return 字段名<br>
	 */
	public String getField() {
		return field;
	}

	/**
	 * <p>设置字段名</p>
	 * @param field 字段名<br>
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * <p>获取字段值</p>
	 * @return 字段值<br>
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * <p>设置字段值</p>
	 * @param value 字段值<br>
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * <p>获取比较类型</p>
	 * @return 比较类型<br>
	 */
	public CompareEnum getCompare() {
		return compare;
	}
	
	/**
	 * <p>设置比较类型</p>
	 * @param compare 比较类型<br>
	 */
	public void setCompare(CompareEnum compare) {
		this.compare = compare;
	}
	
	/**
	 * <p>构造函数:初始化相关参数</p>
	 * @param field  字段名<br>
	 * @param value 字段值<br>
	 * @param compare 比较类型<br>
	 */
	public ConditionParameter(String field, Object value, CompareEnum compare) {
		this.field = field; this.value = value; this.compare = compare;
	}
	
	/**
	 * <p>重写父类方法</p>
	 */
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof ConditionParameter))
			return false;
		final ConditionParameter param = (ConditionParameter) other;
		if (!param.getField().equals(getField())) return false;
		if (!param.getCompare().equals(this.getCompare())) return false;
		return true;
	}
	
	/**
	 * <p>重写父类方法</p>
	 */
	public int hashCode() {
		int hashcode = super.hashCode();
		// 字段名
		if (StringHelper.isNotEmpty(field)) {
			hashcode += field.hashCode();
		}
		// 字段值
		if (CommHelper.isNotNull(value)) {
			hashcode += value.hashCode();
		}
		// 比较类型
		if (CommHelper.isNotNull(compare)) {
			hashcode += compare.hashCode();
		}
		return hashcode;
	}
	
	/**
	 * <p>重载核心函数</p>
	 */
	public String toString() {
		if (CompareEnum.COMPARE_EQUEAL.equals(compare) || CompareEnum.COMPARE_NOT_EQUEAL.equals(compare)) {
			String param = (String) value;
			if (StringHelper.isNotEmpty(param)) {
				if (param.indexOf("TO_DATE") >= 0 || param.indexOf("STR_TO_DATE") >= 0 || param.indexOf("TIMESTAMP") >= 0 || param.indexOf("CAST") >= 0) {
					return field.concat(" ").concat(compare.getValue()) .concat(" ").concat(value.toString().trim()).concat(" ");
				}  else {
					return field.concat(" ").concat(compare.getValue()).concat(" '").concat(value.toString().trim()).concat("' ");
				}
			}
		} else if (CompareEnum.COMPARE_LESS.equals(compare) || CompareEnum.COMPARE_MORE.equals(compare)
				|| CompareEnum.COMPARE_LESS_EQUEAL.equals(compare) || CompareEnum.COMPARE_MORE_EQUEAL.equals(compare)) {
			return field.concat(" ").concat(compare.getValue()) .concat(" ").concat(value.toString().trim()).concat(" ");
		} else if (CompareEnum.COMPARE_LIKE.equals(compare)) {
			return field.concat(" ").concat("LIKE '%").concat(value.toString().trim()).concat("%'").concat(" ");
		} else if (CompareEnum.COMPARE_LEFT_LIKE.equals(compare)) {
			return field.concat(" ").concat("LIKE '%").concat(value.toString().trim()).concat("'").concat(" ");
		} else if (CompareEnum.COMPARE_RIGHT_LIKE.equals(compare)) {
			return field.concat(" ").concat("LIKE '").concat(value.toString().trim()).concat("%'").concat(" ");
		} else if (CompareEnum.COMPARE_NOT_IN.equals(compare) || CompareEnum.COMPARE_IN.equals(compare) && value instanceof Object[]) {
			StringBuffer rtnBuf = new StringBuffer();
			Object[] tempArray = (Object[]) value;
			if (CommHelper.isNotEmptyArray(tempArray)) {
				rtnBuf.append(field).append(" ").append(compare.getValue()).append(" (");
				for (int i = 0; i < tempArray.length; i ++) {
					String condition = CommHelper.isNotNull(tempArray[i]) ? (String) tempArray[i] : "XXXX";
					if (i == 0) {
						rtnBuf.append("'").append(condition).append("'");
					} else {
						rtnBuf.append(" ,").append("'").append(condition).append("'");
					}
				}
				rtnBuf.append(") ");
			} else if (CompareEnum.COMPARE_IS_NULL.equals(compare)) {
				return field.concat(" ").concat(compare.getValue()).concat(" ");
			}
			return rtnBuf.toString();
		}
		return Constants.EMPTY_STRING;
	}
}
