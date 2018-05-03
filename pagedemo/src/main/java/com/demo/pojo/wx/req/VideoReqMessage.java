package com.demo.pojo.wx.req;

import com.demo.pojo.wx.req.base.BaseReqMessage;

/**
 * <p>微信请求消息之视频消息</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class VideoReqMessage extends BaseReqMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 636693912568091604L;
	/** 视频消息媒体主键,可以调用多媒体文件下载接口拉取数据 **/
	private String MediaId;
	/** 视频消息缩略图的媒体主键，可以调用多媒体文件下载接口拉取数据 **/
	private String ThumbMediaId;
	
	/**
	 * <p>获取视频消息媒体主键</p>
	 * @return 视频消息媒体主键<br>
	 */
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * <p>设置视频消息媒体主键</p>
	 * @param mediaId 视频消息媒体主键<br>
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	/**
	 * <p>获取视频消息缩略图的媒体主键</p>
	 * @return 视频消息缩略图的媒体主键<br>
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	
	/**
	 * <p>设置视频消息缩略图的媒体主键</p>
	 * @param thumbMediaId 视频消息缩略图的媒体主键<br>
	 */
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
