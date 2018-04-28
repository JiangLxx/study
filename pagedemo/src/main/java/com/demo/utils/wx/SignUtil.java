package com.demo.utils.wx;

import java.util.Arrays;
import com.demo.pojo.WxSignature;
import com.demo.utils.CommHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>检验signature工具类</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public final class SignUtil {
	// 和响应微信发送的验证Token一致
	private static String token = "jianglannihaowa";
	// 十六进制参数数组
	private static char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
	
	/**
	 * <p>验证签名</p>
	 * @param sign 传入参数实体<br>
	 * @return 验证结果<br>
	 */
	public static boolean checkSignature(WxSignature sign) {
//		加密/校验流程如下：
//		1）将token、timestamp、nonce三个参数进行字典序排序
//		2）将三个参数字符串拼接成一个字符串进行sha1加密
//		3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if(CommHelper.isNotNull(sign)) {
			// 将token、timestamp、nonce三个参数进行字典排序
			String[] arr = new String[] {token, sign.getTimestamp(), sign.getNonce()};
			Arrays.sort(arr);
			
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			StringBuilder content = new StringBuilder();
			for (String string : arr) {
				content.append(string);
			}
			System.out.println("加密前数据：" + content.toString());
			
			MessageDigest md = null; String tmpStr = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
				// 将此拼接字符串进行sha1加密
				byte[] digest = md.digest(content.toString().getBytes());
				tmpStr = byteToStr(digest);
				System.out.println("加密后数据：" + tmpStr);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} finally {
				// 释放资源
				content = null;
			}
			// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
			return tmpStr != null ? tmpStr.equals(sign.getSignature().toUpperCase()) : Boolean.FALSE;
		}
		return false;
	}
	
	/**
	 * <p>将字节数组转换为十六进制字符串</p>
	 * @param byteArray 目标字节数组<br>
	 * @return 转换结果<br>
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		if(CommHelper.isNotEmptyByteArray(byteArray)) {
			for (byte b : byteArray) {
				strDigest += byteToHexStr(b);
			}
		}
		return strDigest;
	}
	
	/**
	 * <p>将字节转换为十六进制字符串</p>
	 * @param mByte 目标字节<br>
	 * @return 转换结果<br>
	 */
	private static String byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
