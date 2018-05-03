package com.demo.pojo.wx.resp;

import com.demo.pojo.wx.resp.base.BaseRespMessage;

/**
 * <p>微信响应消息之回复音乐消息</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class MusicRespMessage extends BaseRespMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -6608445238809242374L;
    /** 音乐标题 **/
    private String Title;
    /** 音乐描述 **/
    private String Description;
    /** 音乐链接 **/
    private String MusicUrl;
    /** 高质量音乐链接(WIFI环境优先使用该链接播放音乐 )**/
    private String HQMusicUrl;
    /** 缩略图媒体主键(通过上传多媒体文件得到的主键) **/
    private String ThumbMediaId;
	
    /**
	 * <p>获取音乐标题</p>
	 * @return 音乐标题<br>
	 */
    public String getTitle() {
		return Title;
	}
	
    /**
	 * <p>设置音乐标题</p>
	 * @param title 音乐标题<br>
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
	/**
	 * <p>获取音乐描述 </p>
	 * @return 音乐描述 <br>
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * <p>设置音乐描述 </p>
	 * @param description 音乐描述 <br>
	 */
	public void setDescription(String description) {
		Description = description;
	}
	
	/**
	 * <p>获取音乐链接</p>
	 * @return 音乐链接<br>
	 */
	public String getMusicUrl() {
		return MusicUrl;
	}
	
	/**
	 * <p>设置音乐链接</p>
	 * @param musicUrl 音乐链接<br>
	 */
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	
	/**
	 * <p>获取高质量音乐链接</p>
	 * @return 高质量音乐链接<br>
	 */
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	
	/**
	 * <p>设置高质量音乐链接</p>
	 * @param hQMusicUrl 高质量音乐链接<br>
	 */
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	
	/**
	 * <p>获取缩略图媒体主键</p>
	 * @return 缩略图媒体主键<br>
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	
	/**
	 * <p>设置缩略图媒体主键</p>
	 * @param thumbMediaId 缩略图媒体主键<br>
	 */
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
