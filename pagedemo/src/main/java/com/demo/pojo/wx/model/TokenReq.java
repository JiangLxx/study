package com.demo.pojo.wx.model;

import java.io.Serializable;

/**
 * <p>获取access_token参数类</p>
 * @author jianglan @date 2018-05-04<br>
 * @version 1.0<br>
 */
public class TokenReq implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 2680007468895120604L;
	/** 第三方用户唯一凭证 **/
	private String appid;
	/** 第三方用户唯一凭证秘钥 **/
	private String secret; // 即appsecret
	/** 授予类型 **/
	private String grant_type; // 获取access_token填写client_credential
	
	/**
	 * <p>获取第三方用户唯一凭证 </p>
	 * @return 第三方用户唯一凭证 <br>
	 */
	public String getAppid() {
		return appid;
	}
	
	/**
	 * <p>设置第三方用户唯一凭证 </p>
	 * @param appid 第三方用户唯一凭证 <br>
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/**
	 * <p>获取第三方用户唯一凭证秘钥</p>
	 * @return 第三方用户唯一凭证秘钥<br>
	 */
	public String getSecret() {
		return secret;
	}
	
	/**
	 * <p>设置第三方用户唯一凭证秘钥</p>
	 * @param secret 第三方用户唯一凭证秘钥<br>
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	/**
	 * <p>获取授予类型 </p>
	 * @return 授予类型 <br>
	 */
	public String getGrant_type() {
		return grant_type;
	}
	
	/**
	 * <p>设置授予类型 </p>
	 * @param grant_type 授予类型 <br>
	 */
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
}
