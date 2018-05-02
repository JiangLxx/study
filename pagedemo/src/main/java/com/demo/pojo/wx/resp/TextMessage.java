package com.demo.pojo.wx.resp;

import com.demo.pojo.wx.resp.base.BaseMessage;

/**
 * <p>微信响应消息之回复文本消息</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class TextMessage extends BaseMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -309499921612703330L;
	/** 回复的消息内容 **/
    private String Content;
	
    /**
	 * <p>获取回复的消息内容</p>
	 * @return 回复的消息内容<br>
	 */
    public String getContent() {
		return Content;
	}
    
	/**
	 * <p>设置回复的消息内容</p>
	 * @param content 回复的消息内容<br>
	 */
	public void setContent(String content) {
		Content = content;
	}
}
