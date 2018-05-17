package com.demo.pojo.wx.authorization;

import com.demo.pojo.wx.authorization.base.BaseUserInfo;

/**
 * <p>微信-微信用户基本信息类</p>
 * @author jianglan @date 2018-05-17<br>
 * @version 1.0<br>
 */
public class WeixinUserInfo extends BaseUserInfo {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -3214848979017723153L;
	/** 关注状态 **/
    private int subscribe; //（1是关注，0是未关注），未关注时获取不到其余信息
    /** 用户语言 **/
    private String language; // 简体中文为zh_CN
    /** 关注时间 **/
    private String subscribeTime; // 时间戳。如果用户曾多次关注，则取最后关注时间
	
    /**
	 * <p>获取关注状态</p>
	 * @return 关注状态<br>
	 */
    public int getSubscribe() {
		return subscribe;
	}
	
    /**
	 * <p>设置关注状态</p>
	 * @param subscribe 关注状态<br>
	 */
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	
	/**
	 * <p>获取用户语言</p>
	 * @return 用户语言<br>
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * <p>设置用户语言</p>
	 * @param language 用户语言<br>
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * <p>获取关注时间</p>
	 * @return 关注时间<br>
	 */
	public String getSubscribeTime() {
		return subscribeTime;
	}
	
	/**
	 * <p>设置关注时间</p>
	 * @param subscribeTime 关注时间<br>
	 */
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
}
