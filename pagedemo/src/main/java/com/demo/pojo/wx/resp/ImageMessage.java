package com.demo.pojo.wx.resp;

import com.demo.pojo.wx.resp.base.BaseMessage;

/**
 * <p>微信响应消息之回复图片消息</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class ImageMessage extends BaseMessage  {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 57656879914635274L;
	/** 媒体文件主键 **/
	private String MediaId;
	
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
}
