package com.demo.pojo;

import java.io.Serializable;

/**
 * <p>微信签名验证实体类</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class WxSignature implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -5595647307042994592L;
	/** 随机数 **/
	private String nonce;
	/** 随机字符串 **/
	private String echostr;
	/** 微信加密签名 **/
	private String signature; //signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
	/** 时间戳 **/
	private String timestamp;
	
	/**
	 * <p>获取随机数</p>
	 * @return 随机数<br>
	 */
	public String getNonce() {
		return nonce;
	}
	
	/**
	 * <p>获取随机数</p>
	 * @param nonce 随机数<br>
	 */
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	/**
	 * <p>获取随机字符串</p>
	 * @return 随机字符串<br>
	 */
	public String getEchostr() {
		return echostr;
	}
	
	/**
	 * <p>获取随机字符串</p>
	 * @param echostr 随机字符串<br>
	 */
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	/**
	 * <p>获取微信加密签名</p>
	 * @return 微信加密签名<br>
	 */
	public String getSignature() {
		return signature;
	}
	
	/**
	 * <p>获取微信加密签名</p>
	 * @param signature 微信加密签名<br>
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	/**
	 * <p>获取时间戳</p>
	 * @return 时间戳<br>
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * <p>获取时间戳</p>
	 * @param timestamp 时间戳<br>
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
