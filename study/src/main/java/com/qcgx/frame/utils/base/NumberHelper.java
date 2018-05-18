package com.qcgx.frame.utils.base;

import java.util.*;
import java.text.*;
import java.math.*;
import com.qcgx.frame.utils.CommHelper;
import com.qcgx.frame.utils.secu.CheckHelper;
import com.qcgx.frame.utils.base.cnst.Constants;

/**
 * <p>数字类型数据通用操作工具类</p>
 * @author FLY @date 2017-05-23<br>
 * @version 1.0<br>
 */
public class NumberHelper {
	/**
	 * <p>构造函数:禁止新增的方式获取对象</p>
	 */
	private NumberHelper() {}
	
	/**
	 * <p>将字符串转换为字节数据类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 字节类型<br>
	 */
	public static byte getByte(String str) {
		return CheckHelper.checkByte(str) ? Byte.parseByte(getLegalNumeric(str)) : Constants.ZERO_INT;
	}
	
	/**
	 * <p>将字符串转换为短整型数据类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 短整型类型<br>
	 */
	public static short getShort(String str) {
		return CheckHelper.checkShort(str) ? Short.parseShort(getLegalNumeric(str)) : Constants.ZERO_INT;
	}
	
	/**
	 * <p>将字符串转换为整型数据类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 整型类型<br>
	 */
	public static int getInteger(String str) {
		return CheckHelper.checkInteger(str) ? Integer.parseInt(getLegalNumeric(str)) : Constants.ZERO_INT;
	}
	
	/**
	 * <p>将字符串转换为长整型数据类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 长整型类型<br>
	 */
	public static long getLong(String str) {
		return CheckHelper.checkLong(str) ? Long.parseLong(getLegalNumeric(str)) : Constants.ZERO_INT;
	}
	
	/**
	 * <p>将字符串转换为浮点型数据类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 浮点型类型<br>
	 */
	public static float getFloat(String str) {
		return NumberHelper.getBigDecimal(str).floatValue();
	}
	
	/**
	 * <p>将字符串转换为双精度型数据类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 双精度型类型<br>
	 */
	public static double getDouble(String str) {
		return NumberHelper.getBigDecimal(str).doubleValue();
	}
	
	/**
	 * <p>将系统进制字符串转换为十进制</p>
	 * @param str 系统进制字符串<br>
	 * @param digit 进制类型标识<br>
	 * @return 十进制{@link java.lang.Integer}}
	 */
	public static int getInteger(String str, int digit) {
		return Integer.valueOf(getLegalNumeric(str), digit);
	}
	
	/**
	 * <p>将金额数字变为财务中的大写</p>
	 * @param str 数字字符串<br>
	 * @return 大写财务数字<br>
	 */
	public static String chinaFinance(String str) {
		// 初始化返回值
		StringBuffer rtnSB = new StringBuffer("");
		// 判断数字字符串是否为空|合法
		str = NumberHelper.getLegalNumeric(str);
		if (CheckHelper.checkNumeric(str)) {
			// 将数字字符串转换为数字类型
			double number = NumberHelper.multiply(getBigDecimal(str), getBigDecimal("100"), 2).doubleValue();
			if (number < 0)
				rtnSB.append("负");
			long temp = java.lang.Math.round(java.lang.Math.abs(number));
			// 获取整数部分
			long integer = temp / 100;
			// 获取小数部分
			long decimal = temp % 100;
			
			// 当只有小数部分时
			if (integer == 0) {
				rtnSB = rtnSB.append(dealDecimalPart(integer,decimal));
			} else {
				rtnSB = rtnSB.append(dealIntegerPart(integer)).append("元").append(dealDecimalPart(integer,decimal));
			}
		}
		return rtnSB.toString();
	}
	
	/**
	 * <p>将大数值类型对象进行单价格式化</p>
	 * @param bd 大数值类型对象<br>
	 * @return 单价字符串<br>
	 */
	public static String formatPrice(BigDecimal bd) {
		return new DecimalFormat(Constants.DEFAULT_PRICE_FORMAT).format(NumberHelper.getLegalBigDecimal(bd));
	}
	
	/**
	 * <p>根据自定义样式格式化大数值数据对象</p>
	 * @param bd 大数值数据对象<br>
	 * @return 格式化后的字符串<br>
	 */
	public static String formatAmount(BigDecimal bd) {
		return new DecimalFormat(Constants.DEFAULT_AMOUNT_FORMAT).format(NumberHelper.getLegalBigDecimal(bd));
	}
	
	/**
	 * <p>将大数值类型对象进行百分比格式化</p>
	 * @param bd 大数值类型对象<br>
	 * @return 百分比字符串<br>
	 */
	public static String formatPercent(BigDecimal bd) {
		return new DecimalFormat(Constants.DEFAULT_PERCENT_FORMAT).format(NumberHelper.getLegalBigDecimal(bd));
	}
	
	/**
	 * <p>货币字符串进行格式化</p>
	 * @param bd 大数值类型对象<br>
	 * @return 货币字符串<br>
	 */
	public static String formatCurrency(BigDecimal bd) {
		return formatCurrency(getLegalBigDecimal(bd), Locale.getDefault());
	}
	
	/**
	 * <p>根据指定格式串格式化大数值类型对象</p>
	 * @param pattern 格式样式<br>
	 * @param bd 大数值类型对象<br>
	 * @return 格式化字符串<br>
	 */
	public static String format(String pattern, BigDecimal bd) {
		return CheckHelper.checkPattern(pattern) ? new DecimalFormat(pattern).format(getLegalBigDecimal(bd)) : Constants.ZERO_STRING;
	}
	
	/**
	 * <p>将大数值类型对象进行金额格式化</p>
	 * @param bd 大数值类型对象<br>
	 * @param locale 本地语言对象<br>
	 * @return 金额字符串<br>
	 */
	public static String formatCurrency(BigDecimal bd, Locale locale) {
		return NumberFormat.getCurrencyInstance(getLegalLocale(locale)).format(getLegalBigDecimal(bd));
	}
	
	/**
	 * <p>将字符串转换为大整数类型:当输入不合法或溢出时返回为零</p>
	 * @param str 字符串<br>
	 * @return 大整数类型<br>
	 */
	public static BigInteger getBigInteger(String str) {
		return NumberHelper.getBigDecimal(str).toBigInteger();
	}
	
	/**
	 * <p>将字符串转换为大数据类型值:输入数据不合法时，返回零值</p>
	 * @param str 字符串<br>
	 * @return {@link java.math.BigDecimal} 大数据类型值<br>
	 */
	public static BigDecimal getBigDecimal(String str) {
		BigDecimal rtnBD = Constants.BIG_DEC_ZERO;
		str = NumberHelper.getLegalNumeric(str);
		if (CheckHelper.checkNumeric(str)) {
			rtnBD = new java.math.BigDecimal(str);
		}
		return rtnBD;
	}
	
	/**
	 * <p>获取合法的本地语言环境对象</p>
	 * @param locale 语言环境对象<br>
	 * @return {@link java.util.Locale}语言环境对象<br>
	 */
	public static Locale getLegalLocale(Locale locale) {
		return CommHelper.isNull(locale) ? Locale.getDefault() : locale;
	}
	
	/**
	 * <p>替换数字字符串中格式、金额字符</p>
	 * @param number 字符串<br>
	 * @return 数字字符串<br>
	 */
	public static String getLegalNumeric(String number) {
		return StringHelper.isNotEmpty(number) ? number.replaceAll("¥|\\$|,", "") : Constants.ZERO_STRING;
	}
	
	/**
	 * <p>将十进制数值转换为系统识别数值</p>
	 * @param numeric 十进制<br>
	 * @param digit 进制标识值<br>
	 * @return 系统识别数值<br>
	 */
	public static String getSystemDigital(int numeric, int digit) {
		String rtnDigital = Constants.EMPTY_STRING;
		switch (digit)  {
		case 2:
			rtnDigital = Integer.toBinaryString(numeric); break;
		case 8:
			rtnDigital = Integer.toOctalString(numeric); break;
		case 16:
			rtnDigital = Integer.toHexString(numeric); break;
			default:
			rtnDigital = String.valueOf(numeric); break;
		}
		return rtnDigital;
	}
	
	/**
	 * <p>判断整数对象为空</p>
	 * @param integer 整数对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyInteger(Integer integer) {
		return CommHelper.isNull(integer) || integer == 0;
	}
	
	/**
	 * <p>判断整数对象非空</p>
	 * @param integer 整数对象<br>
	 * @return false:非空 false:已空<br>
	 */
	public static boolean isNotEmptyInteger(Integer integer) {
		return isEmptyInteger(integer) == false;
	}
	
	/**
	 * <p>判断大数值数据类型为已空</p>
	 * @param bd 大数值对象<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyBigDecimal(BigDecimal bd) {
		return CommHelper.isNull(bd) || bd.floatValue() == 0.0f;
	}
	
	/**
	 * <p>判断大数值数据类型为非空</p>
	 * @param bd 大数值对象<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyBigDecimal(BigDecimal bd) {
		return !NumberHelper.isEmptyBigDecimal(bd);
	}
	
	/**
	 * <p>判断大整数数据类型为已空</p>
	 * @param integer 大整数类型<br>
	 * @return true:已空 false:非空<br>
	 */
	public static boolean isEmptyBigInteger(BigInteger integer) {
		return CommHelper.isNull(integer) || integer.longValue() == 0;
	}
	
	/**
	 * <p>判断大整数数据类型为非空</p>
	 * @param integer 大整数类型<br>
	 * @return true:非空 false:已空<br>
	 */
	public static boolean isNotEmptyBigInteger(BigInteger integer) {
		return !NumberHelper.isEmptyBigInteger(integer);
	}
	
	/**
	 * <p>获取合法的整数对象</p>
	 * @param integer 整数<br>
	 * @return 整数<br>
	 */
	public static Integer getLegalInteger(Integer integer) {
		return isEmptyInteger(integer) ? Constants.ZERO_INT : integer;
	}
	
	/**
	 * <p>获取合法的大整数对象</p>
	 * @param bi 大整数对象<br>
	 * @return 大整数对象<br>
	 */
	public static BigInteger getLegalBigInteger(BigInteger bi) {
		return CommHelper.isNull(bi) ? Constants.BIG_INT_ZERO : bi;
	}
	
	/**
	 * <p>获取合法的大数值对象</p>
	 * @param bd 大数值对象<br>
	 * @return 大数值对象<br>
	 */
	public static BigDecimal getLegalBigDecimal(BigDecimal bd) {
		return CommHelper.isNull(bd) ? Constants.BIG_DEC_ZERO : bd;
	}
	
	/**
	 * <p>计算两个数量值的和值</p>
	 * @param value01 整数<br>
	 * @param value02 整数<br>
	 * @return 整数<br>
	 */
	public static Integer sum(Integer value01, Integer value02) {
		return getLegalInteger(value01) + getLegalInteger(value02);
	}
	
	/**
	 * <p>计算两个数量值的差值</p>
	 * @param value01 整数<br>
	 * @param value02 整数<br>
	 * @return 整数<br>
	 */
	public static Integer subtract(Integer value01, Integer value02) {
		return getLegalInteger(value01) - getLegalInteger(value02);
	}
	
	/**
	 * <p>计算两个数量值的和值</p>
	 * @param value01 大整型<br>
	 * @param value02 大整型<br>
	 * @return 大整型<br>
	 */
	public static BigInteger sum(BigInteger value01, BigInteger value02) {
		return getLegalBigInteger(value01).add(getLegalBigInteger(value02));
	}
	
	/**
	 * <p>计算两个数量值的差值</p>
	 * @param value01 整数<br>
	 * @param value02 整数<br>
	 * @return 大整型<br>
	 */
	public static BigInteger subtract(BigInteger value01, BigInteger value02) {
		return getLegalBigInteger(value01).subtract(getLegalBigInteger(value02));
	}
	
	/**
	 * <p>精确计算两个数量值的和并指定保留小数的位数</p>
	 * @param value01 数量值<br>
	 * @param value02 数量值<br>
	 * @param precision 保留小数位数<br>
	 * @return {@link java.math.BigDecimal} 数据对象<br>
	 */
	public static BigDecimal sum(BigDecimal value01, BigDecimal value02, int precision) {
		return  getLegalBigDecimal(value01).add(getLegalBigDecimal(value02)).setScale(precision, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * <p>精确计算两个数量值的商并指定保留小数的位数</p>
	 * @param value01 数量值<br>
	 * @param value02 数量值<br>
	 * @param precision 保留小数位数<br>
	 * @return {@link java.math.BigDecimal} 数据对象<br>
	 */
	public static BigDecimal divide(BigDecimal value01, BigDecimal value02, int precision) {
		return  isNotEmptyBigDecimal(value02) ? getLegalBigDecimal(value01).divide(getLegalBigDecimal(value02)).setScale(precision, BigDecimal.ROUND_HALF_UP) : Constants.BIG_DEC_ZERO;
	}
	
	/**
	 * <p>精确计算两个数量值的差并指定保留小数的位数</p>
	 * @param value01 数量值<br>
	 * @param value02 数量值<br>
	 * @param precision 保留小数位数<br>
	 * @return {@link java.math.BigDecimal} 数据对象<br>
	 */
	public static BigDecimal subtract(BigDecimal value01, BigDecimal value02, int precision) {
		return getLegalBigDecimal(value01).subtract(getLegalBigDecimal(value02)).setScale(precision, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * <p>精确计算两个数量值的积并指定保留小数的位数</p>
	 * @param value01 数量值<br>
	 * @param value02 数量值<br>
	 * @param precision 保留小数位数<br>
	 * @return {@link java.math.BigDecimal} 数据对象<br>
	 */
	public static BigDecimal multiply(BigDecimal value01, BigDecimal value02, int precision) {
		return getLegalBigDecimal(value01).multiply(getLegalBigDecimal(value02)).setScale(precision, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * <p>处理整数部分</p>
	 * @param integer 整数部分字符串<br>
	 * @return 大写财务数字<br>
	 */
	private static StringBuffer dealIntegerPart(long integer) {
		// 初始化返回值
		StringBuffer rtnSB = new StringBuffer();
		// 转化为数字字符串
		String strInt = String.valueOf(integer);
		boolean lastZero = false ,hasValue = false;
		// 获取字符串长度
		int len = strInt.length();
		for (int i = len -1; i >= 0; i --) {
			int n = strInt.charAt(len - i - 1) - '0';
			// 判断当前数字是否为零
			if (n != 0) {
				if (lastZero)
					rtnSB = rtnSB.append(Constants.HanDigiStr[0]);
				if (!(n == 1 && (i % 4) == 1 && i == len - 1))
					rtnSB = rtnSB.append(Constants.HanDigiStr[n]);
				rtnSB = rtnSB.append(Constants.HanDiviStr[i]);
				hasValue = true;
			} else {
				if ( (i % 8) == 0 || ( (i % 8) == 4 && hasValue))
					rtnSB = rtnSB.append(Constants.HanDiviStr[i]);
			}
			if (i % 8 == 0)
				hasValue = false;
			lastZero = (n == 0) && (i % 4 != 0);
		}
		return rtnSB;
	}
	
	/**
	 * <p>处理小数部分</p>
	 * @param integer 整数部分字符串<br>
	 * @param decimal 小数部分字符串<br>
	 * @return 大写财务数字<br>
	 */
	private static StringBuffer dealDecimalPart(long integer, long decimal) {
		// 初始化返回值
		StringBuffer rtnSB = new StringBuffer();
		// 获取角位数值
		int jiao = (int) decimal / 10;
		// 获取分位数值
		int fen = (int) decimal % 10;
		
		if (jiao == 0 && fen == 0) {
			return rtnSB.append("零元整");
		} else {
			if (jiao > 0)
				rtnSB = rtnSB.append(Constants.HanDigiStr[jiao]).append("角");
			if (integer > 0 && jiao == 0)
				rtnSB.append("零");
			if (fen > 0)
				rtnSB = rtnSB.append(Constants.HanDigiStr[fen]).append("分");
		}
		return rtnSB;
	}
}
