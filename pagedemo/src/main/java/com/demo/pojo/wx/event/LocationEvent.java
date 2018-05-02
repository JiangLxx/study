package com.demo.pojo.wx.event;

import com.demo.pojo.wx.event.base.BaseEvent;

/**
 * <p>微信请求事件之上报地理位置事件</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class LocationEvent extends BaseEvent {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 4201996656124768842L;
	/** 地理位置纬度 **/
    private String Latitude;
    /** 地理位置经度 **/
    private String Longitude;
    /** 地理位置精度 **/
    private String Precision;
    
    /**
	 * <p>获取地理位置纬度</p>
	 * @return 地理位置纬度<br>
	 */
	public String getLatitude() {
		return Latitude;
	}
	
	/**
	 * <p>设置地理位置纬度</p>
	 * @param latitude 地理位置纬度<br>
	 */
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	
	/**
	 * <p>获取地理位置经度</p>
	 * @return 地理位置经度<br>
	 */
	public String getLongitude() {
		return Longitude;
	}
	
	/**
	 * <p>设置地理位置经度</p>
	 * @param longitude 地理位置经度<br>
	 */
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	
	/**
	 * <p>获取地理位置精度</p>
	 * @return 地理位置精度<br>
	 */
	public String getPrecision() {
		return Precision;
	}
	
	/**
	 * <p>设置地理位置精度</p>
	 * @param precision 地理位置精度<br>
	 */
	public void setPrecision(String precision) {
		Precision = precision;
	}
}
