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
    private int expires_in;
	/** 接口访问凭证 **/
    private String access_token;
	
    /**
	 * <p>获取凭证有效期 </p>
	 * @return 凭证有效期 <br>
	 */
    public int getExpires_in() {
		return expires_in;
	}

    /**
	 * <p>设置凭证有效期 </p>
	 * @param expires_in 凭证有效期 <br>
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * <p>获取接口访问凭证 </p>
	 * @return 接口访问凭证 <br>
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * <p>设置接口访问凭证 </p>
	 * @param access_token 接口访问凭证 <br>
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
