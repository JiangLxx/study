package com.demo.pojo.wx.req;

import com.demo.pojo.wx.req.base.BaseReqMessage;

/**
 * <p>微信请求消息之链接消息</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class LinkReqMessage extends BaseReqMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -2720470691030246559L;
	/** 消息链接 **/
	private String Url;
	/** 消息标题 **/
	private String Title;
	/** 消息描述 **/
	private String Description;
	
	/**
	 * <p>获取消息链接</p>
	 * @return 消息链接<br>
	 */
	public String getUrl() {
		return Url;
	}
	
	/**
	 * <p>设置消息链接</p>
	 * @param url 消息链接<br>
	 */
	public void setUrl(String url) {
		Url = url;
	}
	
	/**
	 * <p>获取消息标题</p>
	 * @return 消息标题<br>
	 */
	public String getTitle() {
		return Title;
	}
	
	/**
	 * <p>设置消息标题</p>
	 * @param title 消息标题<br>
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
	/**
	 * <p>获取消息描述</p>
	 * @return 消息描述<br>
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * <p>设置消息描述</p>
	 * @param description 消息描述<br>
	 */
	public void setDescription(String description) {
		Description = description;
	}
}
