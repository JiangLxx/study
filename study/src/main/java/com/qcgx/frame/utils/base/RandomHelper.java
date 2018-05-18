package com.qcgx.frame.utils.base;

import java.util.Random;
import com.qcgx.frame.utils.base.cnst.Constants;

/**
 * <p>随机数生成工具类</p>
 * @author FLY @date 2017-09-20<br>
 * @version 1.0<br>
 */
public class RandomHelper {
	/**
	 * <p>根据设定长度返回随机混合字符串</p>
	 * @param length 随机字符串长度<br>
	 * @return 随机字符串<br>
	 */
	public static String generateMixString(int length) {
		StringBuffer buffer = new StringBuffer();
		try {
			Random random = new Random(); int ranLen = Constants.ALL_CHAR_STRING.length();
			for(int i = 0; i < length; i ++) {
				buffer.append(Constants.ALL_CHAR_STRING.charAt(random.nextInt(ranLen)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * <p>根据设定长度返回随机字母字符串</p>
	 * @param length 随机字符串长度<br>
	 * @return 随机字符串<br>
	 */
	public static String generateLetterString(int length) {
		StringBuffer buffer = new StringBuffer();
		try {
			Random random = new Random(); int ranLen = Constants.LETTER_CHAR_STRING.length();
			for(int i = 0; i < length; i ++) {
				buffer.append(Constants.LETTER_CHAR_STRING.charAt(random.nextInt(ranLen)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * <p>根据设定长度返回随机数字字符串</p>
	 * @param length 随机字符串长度<br>
	 * @return 随机字符串<br>
	 */
	public static String generateNumberString(int length) {
		StringBuffer buffer = new StringBuffer();
		try {
			Random random = new Random(); int ranLen = Constants.NUMBER_CHAR_STRING.length();
			for(int i = 0; i < length; i ++) {
				buffer.append(Constants.NUMBER_CHAR_STRING.charAt(random.nextInt(ranLen)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer.toString();
	}
	
	/**
	 * <p>根据设定长度返回随机纯小写混合字符串</p>
	 * @param length 随机字符串长度<br>
	 * @return 随机字符串<br>
	 */
	public static String generateLowerMixString(int length) {
		return generateMixString(length).toLowerCase();
	}
	
	/**
	 * <p>根据设定长度返回随机纯大写混合字符串</p>
	 * @param length 随机字符串长度<br>
	 * @return 随机字符串<br>
	 */
	public static String generateUpperMixString(int length) {
		return generateMixString(length).toUpperCase();
	}
}
