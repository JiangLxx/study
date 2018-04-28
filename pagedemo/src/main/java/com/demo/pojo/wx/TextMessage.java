package com.demo.pojo.wx;

import com.demo.pojo.wx.base.BaseMessage;

/**
 * <p>微信请求消息之文本消息</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class TextMessage extends BaseMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 8252688586063329689L;
	/** 消息内容 **/
	private String Content;
	
	/**
	 * <p>获取消息内容</p>
	 * @return 消息内容<br>
	 */
	public String getContent() {
		return Content;
	}
	
	/**
	 * <p>设置消息内容</p>
	 * @param content 消息内容<br>
	 */
	public void setContent(String content) {
		Content = content;
	}
}
