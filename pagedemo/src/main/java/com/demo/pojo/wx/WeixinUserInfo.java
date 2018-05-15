package com.demo.pojo.wx;

import java.io.Serializable;

/**
 * <p>微信用户的基本信息</p>
 * @author jianglan @date 2018-05-15<br>
 * @version 1.0<br>
 */
public class WeixinUserInfo implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 6850508451919001669L;
    /** 用户性别 **/
    private int sex; // 1是男性，2是女性，0是未知
    /** 所在城市 **/
    private String city;
	/** 用户标识 **/
    private String openId;
    /** 关注状态 **/
    private int subscribe; //（1是关注，0是未关注），未关注时获取不到其余信息
    /** 所在国家 **/
    private String country;
    /** 所在省份 **/
    private String province;
    /** 用户昵称 **/
    private String nickname;
    /** 用户语言 **/
    private String language; // 简体中文为zh_CN
    /** 用户头像 **/
    private String headImgUrl;
    /** 关注时间 **/
    private String subscribeTime; // 时间戳。如果用户曾多次关注，则取最后关注时间
	
    /**
	 * <p>获取用户性别 </p>
	 * @return 用户性别<br>
	 */
    public int getSex() {
		return sex;
	}
	
    /**
	 * <p>设置用户性别 </p>
	 * @param sex 用户性别<br>
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	/**
	 * <p>获取所在城市 </p>
	 * @return 所在城市 <br>
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * <p>设置所在城市 </p>
	 * @param city 所在城市 <br>
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * <p>获取用户标识 </p>
	 * @return 用户标识 <br>
	 */
	public String getOpenId() {
		return openId;
	}
	
	/**
	 * <p>设置用户标识 </p>
	 * @param openId 用户标识 <br>
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	/**
	 * <p>获取关注状态 </p>
	 * @return 关注状态 <br>
	 */
	public int getSubscribe() {
		return subscribe;
	}
	
	/**
	 * <p>设置关注状态</p>
	 * @param subscribe 关注状态 <br>
	 */
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	
	/**
	 * <p>获取所在国家 </p>
	 * @return 所在国家 <br>
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * <p>设置所在国家 </p>
	 * @param country 所在国家 <br>
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * <p>获取所在省份</p>
	 * @return 所在省份 <br>
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * <p>设置所在省份 </p>
	 * @param province 所在省份 <br>
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * <p>获取用户昵称 </p>
	 * @return 用户昵称 <br>
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * <p>设置用户昵称 </p>
	 * @param nickname 用户昵称 <br>
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * <p>获取用户语言</p>
	 * @return 用户语言 <br>
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * <p>设置用户语言 </p>
	 * @param language 用户语言 <br>
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * <p>获取用户头像 </p>
	 * @return 用户头像 <br>
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	
	/**
	 * <p>设置用户头像 </p>
	 * @param headImgUrl 用户头像 <br>
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	/**
	 * <p>获取关注时间 </p>
	 * @return 关注时间 <br>
	 */
	public String getSubscribeTime() {
		return subscribeTime;
	}
	
	/**
	 * <p>设置关注时间 </p>
	 * @param subscribeTime 关注时间 <br>
	 */
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
}
