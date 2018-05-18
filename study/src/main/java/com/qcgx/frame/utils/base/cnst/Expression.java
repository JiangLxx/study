package com.qcgx.frame.utils.base.cnst;

/**
 * <p>工具包正则表达式定义类</p>
 * @author LFY @date 2017-05-22<br>
 * @version 1.0<br>
 */
public class Expression {
	/** 英文字符串正则表达式验证法则 **/
	public static final String ALPHABET_STR_RULER = "^[A-Za-z]+$";
	/** 十六进制串验证表达式验证法则 **/
	public static final String HEX_STRING_RULER = "^[A-Fa-f0-9]+$";
	/** 年份字符串正则表达式验证法则 **/
	public static final String YEAR_STRING_RULER = "^[1-2]\\d{3}$";
	/** Q Q字符串正则表达式验证法则 **/
	public static final String QQ_STRING_RULER = "^[1-9]\\d{4,9}$";
	/** 邮编字符串正则表达式验证法则 **/
	public static final String ZIPCODE_STRING_RULER = "^[1-9]\\d{5}$";
	/** 卡号字符串正则表达式验证法则 **/
	public static final String BANKCARD_STRING_RULER = "^[1-9]\\d{15,18}$";
	/**汉字字符串正则表达式验证法则**/
	public static final String CHINESE_STRING_RULER = "^[\\u4e00-\\u9fa5]+$";
	/** 数字字符串正则表达式验证法则 **/
	public static final String NUMERAL_STRING_RULER = "^[-\\+]?\\d+(\\.\\d+)?$";
	/** MAC字符串正则表达式验证法则 **/
	public static final String MAC_STRING_RULER = "^[A-Z0-9]{2}(-[A-Z0-9]{2}){5}$";
	/**身份证字符串正则表达式验证法则**/
	public static final String IDCARD_STRING_RULER = "^[1-9]\\d{16}(\\d|X)|[1-9]\\d{14}$";
	/** 百分数字符串正则表达式验证法则 **/
	public static final String PERCENT_STRING_RULER = "^[1][0]{2}|(^\\d{1,2}([.]\\d{1,4})?)$";
	/** 时间字符串正则表达式验证法则 **/
	public static final String TIME_STRING_RULER = "^[0-2]\\d{1}(:|时)?[0-5]\\d{1}(:|分)?([0-5]\\d{1}(秒)?)?$";
	/** 单价字符串正则表达式验证法则 **/
	public static final String PRICE_STRING_RULER = "^([-]?[¥|$]?)?[1-9]\\d{0,2}([,]?\\d{0,3}){0,3}([.]\\d{0,8})?$";
	/** 金额字符串正则表达式验证法则 **/
	public static final String AMOUNT_STRING_RULER = "^([-]?[¥|$]?)?[1-9]\\d{0,2}([,]?\\d{0,3}){0,5}([.]\\d{0,2})?$";
	/** 数字格式串正则表达式验证法则 **/
	public static final String NUMERIC_PATTERN_RULER = "^([-]?[¥|$]?)?([#]{3})?([,]?[0|#]{2,3})*([.]?[0|#]{2,8}[%]?)?$";
	/** 手机字符串正则表达式验证法则 **/
	public static final String MOBILE_STRING_RULER = "^[0]?[1]([3]\\d{1}|[4][5|7]|[5][0-3]|[5][5-9]|[7][0]|[7][6-8]|[8]\\d{1})\\d{8}$";
	/** IP4字符串正则表达式验证法则 **/
	public static final String IP4_STRING_RULER = "^(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})(\\.(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})){3}$";
	/** 邮件字符串正则表达式验证法则 **/
	public final static String EMAIL_STRING_RULER = "^([A-Za-z0-9]*[-|_]?[A-Za-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\\.][a-z]{2,3}([\\.][a-z]{2})?$";
	/** 日期格式串正则表达式验证法则 **/
	public static final String DATE_FORMAT_RULER = "^([E][ ])?[y]{4}(-|/|年)?[M]{2}(-|/|月)?[d]{2}(日)?([ ])?([E])?([H]{2}(:|时)?[m]{2}(:|分)?[s]{2}(秒)?)?([ ][E])?$";
	/** 电话字符串正则表达式验证法则 **/
	public static final String TELEPHONE_STRING_RULER = "(^([0][1-9][0-9]{1,2}[-]*)?[1-9]\\d{6,7}(-\\d{1,4})?$)|(^\\([0][1-9][0-9]{1,2}\\)[1-9]\\d{6,7}(\\(\\d{1,4}\\))?$)|(^[1-9]\\d{6,7}$)";
}
