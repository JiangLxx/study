package com.demo.pojo.wx;

import com.demo.pojo.wx.base.BaseMessage;

/**
 * <p>微信请求消息之语音消息</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class VoiceMessage extends BaseMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -5831526656517946557L;
	/** 语音格式 **/
	private String Format;
	/** 语音消息媒体主键,可以调用多媒体文件下载接口拉取数据 **/
	private String MediaId;
	
	/**
	 * <p>获取语音格式</p>
	 * @return 语音格式<br>
	 */
	public String getFormat() {
		return Format;
	}
	
	/**
	 * <p>设置语音格式</p>
	 * @param format 语音格式<br>
	 */
	public void setFormat(String format) {
		Format = format;
	}
	
	/**
	 * <p>获取语音消息媒体主键</p>
	 * @return 语音消息媒体主键<br>
	 */
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * <p>设置语音消息媒体主键</p>
	 * @param mediaId 语音消息媒体主键<br>
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
