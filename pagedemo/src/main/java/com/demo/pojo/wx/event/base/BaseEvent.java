package com.demo.pojo.wx.event.base;

import java.io.Serializable;

/**
 * <p>微信请求事件基类</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class BaseEvent implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 7768523370592019646L;
	/** 事件类型 **/
    private String Event;
    /** 消息类型 **/
    private String MsgType;
    /** 消息创建时间 （整型） **/
    private long CreateTime;
	/** 开发者微信号 **/
    private String ToUserName;
    /** 发送方帐号（一个OpenID） **/
    private String FromUserName;
	
	/**
	 * <p>获取事件类型</p>
	 * @return 事件类型<br>
	 */
    public String getEvent() {
		return Event;
	}
	
	/**
	 * <p>设置事件类型</p>
	 * @param event 事件类型<br>
	 */
	public void setEvent(String event) {
		Event = event;
	}
	
	/**
	 * <p>获取消息类型</p>
	 * @return 消息类型<br>
	 */
	public String getMsgType() {
		return MsgType;
	}
	
	/**
	 * <p>设置消息类型</p>
	 * @param msgType 消息类型<br>
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	/**
	 * <p>获取消息创建时间 </p>
	 * @return 消息创建时间 <br>
	 */
	public long getCreateTime() {
		return CreateTime;
	}
	
	/**
	 * <p>设置消息创建时间 </p>
	 * @param createTime 消息创建时间 <br>
	 */
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	
	/**
	 * <p>获取开发者微信号</p>
	 * @return 开发者微信号<br>
	 */
	public String getToUserName() {
		return ToUserName;
	}
	
	/**
	 * <p>设置开发者微信号</p>
	 * @param toUserName 开发者微信号<br>
	 */
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	/**
	 * <p>获取发送方帐号</p>
	 * @return 发送方帐号<br>
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	
	/**
	 * <p>设置发送方帐号</p>
	 * @param fromUserName 发送方帐号<br>
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
}
