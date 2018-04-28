package com.demo.pojo.wx;

import com.demo.pojo.wx.base.BaseMessage;

/**
 * <p>微信请求消息之图片消息</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class ImageMessage extends BaseMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 3763471185866124410L;
	/** 图片链接 **/
	private String PicUrl;
	/** 图片消息媒体主键,可以调用多媒体文件下载接口拉取数据 **/
	private String MediaId;
	
	/**
	 * <p>获取图片链接 </p>
	 * @return 图片链接 <br>
	 */
	public String getPicUrl() {
		return PicUrl;
	}
	
	/**
	 * <p>设置图片链接 </p>
	 * @param picUrl 图片链接 <br>
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	/**
	 * <p>获取图片消息媒体主键</p>
	 * @return 图片消息媒体主键<br>
	 */
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * <p>设置图片消息媒体主键</p>
	 * @param mediaId 图片消息媒体主键<br>
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
