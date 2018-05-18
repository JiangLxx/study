package com.qcgx.frame.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.qcgx.frame.utils.base.cnst.Constants;

/**
 * <p>emoji工具类</p>
 * @author jianglan @date 2018-04-23<br>
 * @version 1.0<br>
 */
public final class EmojiHelper {
	/**
	 * <p>将str中的emoji表情转为byte数组</p>
	 * @param str 目标字符串<br>
	 * @return 含byte数组字符串<br>
	 */
	public static String resolveToByteFromEmoji(String str) {
		Pattern pattern = Pattern.compile("[^(\u2E80-\u9FFF\\w\\s`~!@#\\$%\\^&\\*\\(\\)_+-？（）——=\\[\\]{}\\|;。，、《》”：；“！……’:'\"<,>\\.?/\\\\*)]");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb2 = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb2, resolveToByte(matcher.group(0)));
		}
		matcher.appendTail(sb2);
		return sb2.toString();
	}

	/**
	 * <p>将str中的byte数组类型的emoji表情转为正常显示的emoji表情</p>
	 * @param str 目标字符串<br>
	 * @return 含emoji表情字符串<br>
	 */
	public static String resolveToEmojiFromByte(String str) {
		Pattern pattern2 = Pattern.compile("<:([[-]\\d*[,]]+):>");
		Matcher matcher2 = pattern2.matcher(str);
		StringBuffer sb3 = new StringBuffer();
		while (matcher2.find()) {
			matcher2.appendReplacement(sb3, resolveToEmoji(matcher2.group(0)));
		}
		matcher2.appendTail(sb3);
		return sb3.toString();
	}

	/**
	 * <p>emoji转byte数组</p>
	 * @param str 目标字符串<br>
	 * @return 转化结果<br>
	 */
	private static String resolveToByte(String str) {
		String rtnS = "";
		try {
			byte[] b = str.getBytes(Constants.UTF_8);
			StringBuffer sb = new StringBuffer();
			sb.append("<:");
			for (int i = 0; i < b.length; i++) {
				if (i < b.length - 1) {
					sb.append(Byte.valueOf(b[i]).toString() + ",");
				} else {
					sb.append(Byte.valueOf(b[i]).toString());
				}
			}
			sb.append(":>");
			rtnS = sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}

	/**
	 * <p>byte数组转emoji</p>
	 * @param str 目标字符串<br>
	 * @return 转化结果<br>
	 */
	private static String resolveToEmoji(String str) {
		String rtnS = "";
		try {
			str = str.replaceAll("<:", "").replaceAll(":>", "");
			String[] s = str.split(",");
			byte[] b = new byte[s.length];
			for (int i = 0; i < s.length; i++) {
				b[i] = Byte.valueOf(s[i]);
			}
			rtnS = new String(b, Constants.UTF_8);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtnS;
	}
}
