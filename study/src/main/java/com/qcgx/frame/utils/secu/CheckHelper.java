package com.qcgx.frame.utils.secu;

import java.util.*;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.base.DateHelper;
import com.qcgx.frame.utils.base.NumberHelper;
import com.qcgx.frame.utils.base.StringHelper;
import com.qcgx.frame.utils.base.cnst.Constants;
import com.qcgx.frame.utils.base.cnst.Expression;
import org.apache.commons.codec.binary.Base64;

/**
 * <p>数据校验通用工具类</p>
 * @author FLY @date 2017-05-22<br>
 * @version 1.0<br>
 */
public class CheckHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private CheckHelper() {}
	
	/**
	 * <p>验证十六进制格式字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkHex(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.HEX_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证QQ字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkQQ(String str) {
		return  StringHelper.isNotEmpty(str) && str.matches(Expression.QQ_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证年份字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkYear(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.YEAR_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证MAC地址的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkMAC(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.MAC_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证IP地址的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkIP4(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.IP4_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证时间字符串的合法性</p>
	 * @param time 时间字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkTime(String time) {
		return StringHelper.isNotEmpty(time) && time.matches(Expression.TIME_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证单价字符串的合法性</p>
	 * @param str 单价字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkPrice(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.PRICE_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证字符串BASE64字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkBase64(String str) {
		return StringHelper.isNotEmpty(str) && Base64.isBase64(str);
	}
	
	/**
	 * <p>当字符串非空时，验证数字格式样式的合法性</p>
	 * @param str 数字格式样式<br>
	 * @return 数字格式样式<br>
	 */
	public static boolean checkPattern(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.NUMERIC_PATTERN_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证金额字符串数据的合法性</p>
	 * @param str 金额字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkAmount(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.AMOUNT_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证邮件地址的合法性</p>
	 * @param str 邮件地址<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkEmail(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.EMAIL_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证百分数的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkPercent(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.PERCENT_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证中文字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkChinese(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.CHINESE_STRING_RULER);
	}
	
	/**
	 *  <p>当字符串非空时，验证手机号码的合法性</p>
	 * @param str 手机号码字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkMobile(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.MOBILE_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证电话号码的合法性</p>
	 * @param str 电话号码<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkTelephone(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.TELEPHONE_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证邮政编码的合法性</p>
	 * @param str 邮政编码<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkZipCode(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.ZIPCODE_STRING_RULER);
	}
	
	/**
	 * <p>判断年份为闰年</p>
	 * @param year 年份整数<br>
	 * @return true:是 false:非<br>
	 */
	public static boolean checkLeapYear(int year) {
		return year != 0 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
	
	/**
	 * <p>当字符串非空时，验证字节类型字符串的合法性</p>
	 * @param str 字节字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkByte(String str) {
		return checkNumeric(str) && checkNumberRange(str, "-128", "127");
	}
	
	/**
	 * <p>当字符串非空时，验证短整型字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkShort(String str) {
		return checkNumeric(str) && checkNumberRange(str, "-32768", "32767");
	}
	
	/**
	 * <p>当字符串非空时，验证整型字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkInteger(String str) {
		return checkNumeric(str) && checkNumberRange(str, "-2147483648", "2147483647");
	}
	
	/**
	 * <p>当字符串非空时，验证长整型字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkLong(String str) {
		return checkNumeric(str) && checkNumberRange(str, "-9223372036854775808", "9223372036854775807");
	}
	
	/**
	 * <p>验证日期字符串的合法性</p>
	 * @param date 日期字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkDate(String date) {
		boolean rtnB = false;
		try {
			Map.Entry<?, ?> entry = null; String formater = null;
			// 合法性判断
			if (StringHelper.isNotEmpty(date)) {
				Map<?, ?> rulerMap = Constants.DATE_STYLE_MAP;
				Iterator<?> iterator = rulerMap.entrySet().iterator();
				while (iterator.hasNext()) {
					entry = (Map.Entry<?, ?>) iterator.next();
					formater = entry.getValue().toString();
					if (date.matches(entry.getKey().toString())) {
						rtnB = CheckHelper.checkDate(date, formater); break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnB;
	}
	
	/**
	 * <p>验证身份证号码的合法性</p>
	 * @param IdCard 字符串<br>
	 * @return 0:完全合法 1:格式错误 2:区域码错误 3:日期错误 4:校验码错误<br>
	 */
	public static String checkIDCard(String IdCard) {
		String rtnCode = "0";
		// 合法性验证
		if (StringHelper.isNotEmpty(IdCard)) {
			// 格式验证
			if (!IdCard.matches(Expression.IDCARD_STRING_RULER)) return "1";
			// 区码验证
			if (!Constants.IDCardCity.containsKey(IdCard.substring(0, 2))) return "2";
			// 标准化身份证信息
			IdCard = getLegalIDCard(IdCard);
			// 日期验证
			if (!CheckHelper.checkDate(IdCard.substring(6, 14))) return "3";
			// 校验位验证
			if (!IdCard.substring(17, 18).equals(generateVerifyBit(IdCard))) return "4";
		}
		return rtnCode;
	}
	
	/**
	 * <p>验证银行卡号的合法性,不支持邮政银行卡号</p>
	 * @param str 信用卡号<br>
	 * @return Boolean true:合法 false:非法<br>
	 */
	public static boolean checkBankCard(String str) {
		boolean rtnB = false;
		// 验证信用卡号不为空,且符合信用卡验证格式
		if (StringHelper.isNotEmpty(str) && str.matches(Expression.BANKCARD_STRING_RULER)) {
			// 银行卡号的权重编码
			int weight = str.length() > 0 ? str.length() % 2 > 0 ? 1 : 2 : 0;
			// 银行卡号的权重合计
			int weightSum = 0;
			// 循环计算银行卡号的权重合计
			for (int i = 0; i < str.length(); i ++) {
				int value = Integer.valueOf(str.substring(i, i + 1));
				weightSum += value * weight > 9 ? value * weight - 9 : value * weight;
				// 重新估算银行卡号的权重编码
				weight = weight > 0 ? weight % 2 > 0 ? 2:1:0;
			}
			rtnB = weightSum > 0 && weightSum % 10 == 0;
		}
		return rtnB;
	}
	
	/**
	 * <p>当字符串非空时，验证数字字符串的合法性</p>
	 * @param str 数字字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkNumeric(String str) {
		return StringHelper.isNotEmpty(str) && str.matches(Expression.NUMERAL_STRING_RULER);
	}
	
	/**
	 * <p>当字符串非空时，验证英文字符串的合法性</p>
	 * @param str 字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkAlphabet(String str) {
		return  StringHelper.isNotEmpty(str) && str.matches(Expression.ALPHABET_STR_RULER);
	}
	
	/**
	 * <p>验证日期格式字符串的合法性</p>
	 * @param format 日期格式字符串<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkDateFormat(String format) {
		return StringHelper.isNotEmpty(format) && format.matches(Expression.DATE_FORMAT_RULER);
	}
	
	/**
	 * <p>判断字符串是否以数组对象中字符作为结尾</p>
	 * @param find 字符串<br>
	 * @param ends 结尾字符串数组<br>
	 * @return true:是 false:非<br>
	 */
	public static boolean checkEndwith(String find, String[] ends) {
		boolean rtnB = false;
		// 合法性判断
		if (CommHelper.isNotEmptyArray(ends) && StringHelper.isNotEmpty(find)) {
			for (String end : ends) {
				if (find.endsWith(end)) {
					rtnB = true; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>判断字符串是否包含数组中的字符串</p>
	 * @param find 字符串<br>
	 * @param indexOfs 字符串数组<br>
	 * @return true:是 false:非<br>
	 */
	public static boolean checkIndexOf(String find, String[] indexOfs) {
		boolean rtnB = false;
		// 合法性判断
		if (StringHelper.isNotEmpty(find) && CommHelper.isNotEmptyArray(indexOfs)) {
			for (String indexof : indexOfs) {
				if (find.indexOf(indexof) >= 0) {
					rtnB = true; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>判断字符串中是否存在数组中的字符串</p>
	 * @param str 字符串<br>
	 * @param finds 字符串数组<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean checkExistence(String str, String[] finds) {
		boolean rtnB = false;
		// 合法性判断
		if (StringHelper.isNotEmpty(str) && CommHelper.isNotEmptyArray(finds)) {
			for (String find : finds) {
				if (str.indexOf(find) >= 0) {
					rtnB = true; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>判断数组对象中是否包含指定的字符串</p>
	 * @param strs 数组对象<br>
	 * @param find 指定字符串<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean checkExistence(String[] strs, String find) {
		boolean rtnB = false;
		// 合法性验证
		if (CommHelper.isNotEmptyArray(strs) && StringHelper.isNotEmpty(find)) {
			for (String str : strs) {
				if (str.equals(find)) {
					rtnB = true; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>判断字符串数组中是存在含指定的字符串</p>
	 * @param strs 字符串数组<br>
	 * @param finds 字符串数组<br>
	 * @return true:已存在 false:未存在<br>
	 */
	public static boolean checkExistence(String[] strs, String[] finds) {
		boolean rtnB = false;
		// 合法性判断
		if (CommHelper.isNotEmptyArray(strs) && CommHelper.isNotEmptyArray(finds)) {
			// 循环判定
			for (String find : finds) {
				if (checkExistence(strs, find)) {
					rtnB = true; break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * <p>根据指定的日期格式判断日期字符串合法</p>
	 * @param date 日期字符串<br>
	 * @param format 日期格式<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkDate(String date, String format) {
		boolean rtnB = true;
		// 合法性验证
		if (StringHelper.isNotEmpty(date) && checkDateFormat(format)) {
			rtnB = date.equals(DateHelper.format(DateHelper.getDate(date, format), format));
		}
		return  rtnB;
	}
	
	/**
	 * <p>验证日期区间的合法性</p>
	 * @param begin 开始日期<br>
	 * @param ended 结束日期<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkDateRange(String begin, String ended) {
		return checkDate(begin) && checkDate(ended) && DateHelper.getDays(DateHelper.getDate(begin), DateHelper.getDate(ended)) >= 0;
	}
	
	/**
	 * <p>验证日期区间的合法性</p>
	 * @param mid 日期字串<br>
	 * @param min 最小日期<br>
	 * @param max 最大日期<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkDateRange(String mid, String min, String max) {
		boolean rtnB = false;
		// 合法性判断
		if (checkDate(mid) && checkDate(min) && checkDate(max)) {
			long midD = DateHelper.getDate(mid).getTime();
			long minD = DateHelper.getDate(min).getTime();
			long maxD = DateHelper.getDate(max).getTime();
			rtnB = midD - minD >= 0 && midD - maxD <= 0;
		}
		return rtnB;
	}
	
	/**
	 * <p>验证字符串字节长度区间的合法性</p>
	 * @param str 字符串<br>
	 * @param minLen 最小字节长度<br>
	 * @param maxLen 最大字节长度<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkByteRange(String str, int minLen, int maxLen) {
		return maxLen >= minLen && StringHelper.byteLen(str) >= minLen && StringHelper.byteLen(str) <= maxLen;
	}
	
	/**
	 * <p>验证字符串字符长度区间的合法性</p>
	 * @param str 字符串<br>
	 * @param minLen 最小字符长度<br>
	 * @param maxLen 最大字符长度<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkLengthRange(String str, int minLen, int maxLen) {
		return maxLen >= minLen && StringHelper.length(str) >= minLen && StringHelper.length(str) <= maxLen;
	}
	
	/**
	 * <p>验证数量区间的合法性</p>
	 * @param mid 数量<br>
	 * @param min 最小值<br>
	 * @param max 最大值<br>
	 * @return true:合法 false:非法<br>
	 */
	public static boolean checkNumberRange(String mid, String min, String max) {
		boolean rtnB = false;
		// 合法性验证
		if (checkNumeric(mid) && checkNumeric(min) && checkNumeric(max)) {
			java.math.BigDecimal midB = NumberHelper.getBigDecimal(mid);
			java.math.BigDecimal minB = NumberHelper.getBigDecimal(min);
			java.math.BigDecimal maxB = NumberHelper.getBigDecimal(max);
			rtnB = midB.compareTo(minB) >= 0 && midB.compareTo(maxB) <= 0;
		}
		return rtnB;
	}
	
	/**
	 * <p>获取合法的身份证编号</p>
	 * @param IdCard 身份证编号<br>
	 * @return 合法的身份证编号<br>
	 */
	private static String getLegalIDCard(String IdCard) {
		String rtnIdCard = IdCard;
		// 执行合法身份证信息转换
		if (StringHelper.isNotEmpty(IdCard) && IdCard.length() == 15) {
			IdCard = IdCard.substring(0, 6).concat("19").concat(IdCard.substring(6, 15));
			rtnIdCard = IdCard.concat(CheckHelper.generateVerifyBit(IdCard));
		}
		return rtnIdCard;
	}
	
	/**
	 * <p>根据身份证号码前17位生成校验位</p>
	 * @param IdCard 身份证号码<br>
	 * @return String 身份证号码的校验位<br>
	 */
	private static String generateVerifyBit(String IdCard) {
		String verifyBit = Constants.EMPTY_STRING;
		// 合法性验证
		if (StringHelper.isNotEmpty(IdCard)) {
			IdCard = IdCard.trim().length() == 18 ? IdCard.substring(0, 17) : IdCard;
			// 根据规则生成校验位
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				sum += Constants.IDCardWi[i] * Integer.parseInt(IdCard.substring(i, i + 1));
			}
			verifyBit = sum % 11 == 2 ? "X" : String.valueOf(Constants.IDCardVi[sum % 11]);
		}
		return verifyBit;
	}
}
