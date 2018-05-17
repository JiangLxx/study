package com.demo.pojo.wx.authorization;

import java.io.Serializable;

/**
 * <p>微信-网页授权信息类</p>
 * @author jianglan @date 2018-05-17<br>
 * @version 1.0<br>
 */
public class WeixinOauth2Token implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 2770380778298785931L;
	/** 用户授权作用域 **/
    private String scope;
    /** 用户标识 **/
    private String openid;
    /** 凭证有效时长 **/
    private int expires_in;
	/** 网页授权接口调用凭证 **/
    private String access_token;
    /** 刷新凭证 **/
    private String refresh_token;
	
    /**
	 * <p>获取用户授权作用域 </p>
	 * @return 用户授权作用域 <br>
	 */
    public String getScope() {
		return scope;
	}
	
    /**
	 * <p>设置用户授权作用域 </p>
	 * @param scope 用户授权作用域 <br>
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	/**
	 * <p>获取用户标识</p>
	 * @return 用户标识<br>
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * <p>设置用户标识</p>
	 * @param openid 用户标识<br>
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**
	 * <p>获取凭证有效时长</p>
	 * @return 凭证有效时长<br>
	 */
	public int getExpires_in() {
		return expires_in;
	}
	
	/**
	 * <p>设置凭证有效时长</p>
	 * @param expires_in 凭证有效时长<br>
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	/**
	 * <p>获取网页授权接口调用凭证</p>
	 * @return 网页授权接口调用凭证<br>
	 */
	public String getAccess_token() {
		return access_token;
	}
	
	/**
	 * <p>设置网页授权接口调用凭证</p>
	 * @param access_token 网页授权接口调用凭证<br>
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	/**
	 * <p>获取刷新凭证</p>
	 * @return 刷新凭证<br>
	 */
	public String getRefresh_token() {
		return refresh_token;
	}
	
	/**
	 * <p>设置刷新凭证</p>
	 * @param refresh_token 刷新凭证<br>
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
}
