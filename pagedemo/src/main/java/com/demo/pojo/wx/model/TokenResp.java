package com.demo.pojo.wx.model;

import java.io.Serializable;

/**
 * <p>获取access_token响应类</p>
 * @author jianglan @date 2018-05-04<br>
 * @version 1.0<br>
 */
public class TokenResp implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -4540163425762608893L;
    /** 凭证有效期，单位：秒 **/
    private int expiresIn;
	/** 接口访问凭证 **/
    private String accessToken;
	
    /**
	 * <p>获取凭证有效期 </p>
	 * @return 凭证有效期 <br>
	 */
    public int getExpiresIn() {
		return expiresIn;
	}
	
    /**
	 * <p>设置凭证有效期 </p>
	 * @param expiresIn 凭证有效期 <br>
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	/**
	 * <p>获取接口访问凭证 </p>
	 * @return 接口访问凭证 <br>
	 */
	public String getAccessToken() {
		return accessToken;
	}
	
	/**
	 * <p>设置接口访问凭证 </p>
	 * @param accessToken 接口访问凭证 <br>
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
