package com.demo.pojo.wx;

import com.demo.pojo.wx.base.BaseMessage;

/**
 * <p>微信请求消息之地理位置消息</p>
 * @author jianglan @date 2018-04-28<br>
 * @version 1.0<br>
 */
public class LocationMessage extends BaseMessage {
	/** 默认版本编号 **/
	private static final long serialVersionUID = -5736687719704804115L;
	/** 地图缩放大小 **/
	private String Scale;
	/** 地理位置信息 **/
	private String Label;
	/** 地理位置维度 **/
	private String Location_X;
	/** 地理位置经度 **/
	private String Location_Y;
	
	/**
	 * <p>获取地图缩放大小</p>
	 * @return 地图缩放大小<br>
	 */
	public String getScale() {
		return Scale;
	}
	
	/**
	 * <p>设置地图缩放大小</p>
	 * @param scale 地图缩放大小<br>
	 */
	public void setScale(String scale) {
	
		
		Scale = scale;
	}
	
	/**
	 * <p>获取地理位置信息</p>
	 * @return 地理位置信息<br>
	 */
	public String getLabel() {
		return Label;
	}
	
	/**
	 * <p>设置地理位置信息</p>
	 * @param label 地理位置信息<br>
	 */
	public void setLabel(String label) {
		Label = label;
	}
	
	/**
	 * <p>获取地理位置维度</p>
	 * @return 地理位置维度<br>
	 */
	public String getLocation_X() {
		return Location_X;
	}
	
	/**
	 * <p>设置地理位置维度</p>
	 * @param location_X 地理位置维度<br>
	 */
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	
	/**
	 * <p>获取地理位置经度</p>
	 * @return 地理位置经度<br>
	 */
	public String getLocation_Y() {
		return Location_Y;
	}
	
	/**
	 * <p>设置地理位置经度</p>
	 * @param location_Y 地理位置经度<br>
	 */
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
}
