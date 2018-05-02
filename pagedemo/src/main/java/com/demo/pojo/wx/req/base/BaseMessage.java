package com.demo.pojo.wx.req.base;

import java.io.Serializable;

/**
 * <p>微信请求消息基类</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class BaseMessage implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -5595647307042994592L;
	/** 消息主键(整型) **/
	private long MsgId;
	/** 消息类型 **/
	private String MsgType; //图片：image 语音：voice 视频：video 小视频：shortvideo 地理位置：location 链接：link
	/** 消息创建时间 (整型)**/
	private long CreateTime;
	/** 开发者微信号 **/
	private String ToUserName;
	/** 发送方账号，OPEN_ID **/
	private String FromUserName;
	
	/**
	 * <p>获取消息主键</p>
	 * @return 消息主键<br>
	 */
	public long getMsgId() {
		return MsgId;
	}
	
	/**
	 * <p>设置消息主键</p>
	 * @param msgId 消息主键<br>
	 */
	public void setMsgId(long msgId) {
		MsgId = msgId;
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
	 * <p>获取消息创建时间</p>
	 * @return 消息创建时间<br>
	 */
	public long getCreateTime() {
		return CreateTime;
	}
	
	/**
	 * <p>设置消息创建时间</p>
	 * @param createTime 消息创建时间<br>
	 */
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	
	/**
	 * <p>获取发送方账号</p>
	 * @return 发送方账号<br>
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	
	/**
	 * <p>设置发送方账号</p>
	 * @param fromUserName 发送方账号<br>
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
}
