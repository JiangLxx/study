package com.demo.pojo.wx.resp.model;

import java.io.Serializable;

/**
 * <p>微信响应消息之图文model</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class Article implements Serializable {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -4236844561541875466L;
	/** 点击图文消息跳转链接 **/
    private String Url;
	/** 图文消息名称 **/
    private String Title;
    /** 图片链接 **/
    private String PicUrl; //支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
    /** 图文消息描述 **/
    private String Description;
	
    /**
	 * <p>获取点击图文消息跳转链接</p>
	 * @return 点击图文消息跳转链接<br>
	 */
    public String getUrl() {
		return Url;
	}
	
	/**
	 * <p>设置点击图文消息跳转链接</p>
	 * @param url 点击图文消息跳转链接<br>
	 */
	public void setUrl(String url) {
		Url = url;
	}
	
    /**
	 * <p>获取图文消息名称</p>
	 * @return 图文消息名称<br>
	 */
	public String getTitle() {
		return Title;
	}
	
	/**
	 * <p>设置图文消息名称</p>
	 * @param title 图文消息名称<br>
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
    /**
	 * <p>获取图片链接</p>
	 * @return 图片链接<br>
	 */
	public String getPicUrl() {
		return PicUrl;
	}
	
	/**
	 * <p>设置图片链接</p>
	 * @param picUrl 图片链接<br>
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
    /**
	 * <p>获取图文消息描述 </p>
	 * @return 图文消息描述 <br>
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * <p>设置图文消息描述 </p>
	 * @param description 图文消息描述 <br>
	 */
	public void setDescription(String description) {
		Description = description;
	}
}
