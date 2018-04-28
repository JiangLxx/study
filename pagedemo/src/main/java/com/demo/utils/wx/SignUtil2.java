package com.demo.utils.wx;

import java.util.Arrays;
import com.demo.pojo.WxSignature;
import com.demo.utils.CommHelper;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>检验signature工具类</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public final class SignUtil2 {
	// 和响应微信发送的验证Token一致
	private static String token = "jianglannihaowa";
	
	/**
	 * <p>验证签名</p>
	 * @param sign 传入参数实体<br>
	 * @return 验证结果<br>
	 */
	public static boolean checkSignature(WxSignature sign) {
		if(CommHelper.isNotNull(sign)) {
			// 字典排序
			String[] arr = new String[] {token, sign.getTimestamp(), sign.getNonce()};
			Arrays.sort(arr);
			
			// 连接成一个字符串
			StringBuilder content = new StringBuilder();
			for (String string : arr) {
				content.append(string);
			}
			System.out.println("加密前数据： " + content);
			String tmpStr = DigestUtils.sha1Hex(content.toString());
			System.out.println("加密后数据：" + tmpStr);
			// 将加密后的字符串与signature进行对比，核实请求来源于微信
			return tmpStr != null ? tmpStr.equals(sign.getSignature()) : false;
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		WxSignature sign = new WxSignature();
//		sign.setEchostr("12546132");
//		sign.setNonce("313464");
//		sign.setSignature("f7f26c2235ade1ccc33548f53637d708d186ea0c");
//		sign.setTimestamp("1524900572740");
//		System.out.println("校验结果：" + SignUtil2.checkSignature(sign));
//	}
}
