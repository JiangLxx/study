package com.demo.pojo.wx.resp;

import com.demo.pojo.wx.resp.base.BaseRespMessage;

/**
 * <p>微信响应消息之回复视频消息</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class VideoRespMessage extends BaseRespMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -1159335508581469882L;
	/** 媒体文件主键 **/
	private String MediaId;
    /** 缩略图媒体主键 **/
    private String ThumbMediaId;
    
	/**
	 * <p>获取媒体文件主键 </p>
	 * @return 媒体文件主键 <br>
	 */
	public String getMediaId() {
		return MediaId;
	}
	
	/**
	 * <p>设置媒体文件主键 </p>
	 * @param mediaId 媒体文件主键 <br>
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	/**
	 * <p>获取缩略图媒体主键 </p>
	 * @return 缩略图媒体主键 <br>
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	/**
	 * <p>设置缩略图媒体主键 </p>
	 * @param thumbMediaId 缩略图媒体主键 <br>
	 */
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
