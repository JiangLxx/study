package com.demo.controller.wx.base;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>表现层核心服务类</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class Action {
	/** 请求对象 **/
	private HttpServletRequest request;
	
	/**
	 * <p>获取请求对象</p>
	 * @return 请求对象<br>
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}
	
	/**
	 * <p>获取请求对象</p>
	 * @param request 请求对象<br>
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
