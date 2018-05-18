package com.qcgx.frame.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.qcgx.frame.filter.base.BaseFilter;
import com.qcgx.frame.utils.base.StringHelper;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>解决AJAX跨域过滤器</p>
 * @author FLY @date 2017-07-15<br>
 * @version 1.0<br>
 */
public class SimpleCorsFilter extends BaseFilter {
	/** 起源类型 **/
	private String access_control_allow_origin = null;
	/** 报头内容 **/
	private String access_control_allow_headers = null;
	/** 请求方式 **/
	private String access_control_allow_methods = null;
	/** 授信标识 **/
	private String access_control_allow_credentials = null;
	
	/**
	 * <p>初始化运行环境监听函数</p>
	 */
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		// 起源类型
		if (StringHelper.isEmpty(access_control_allow_origin)) {
			access_control_allow_origin = getInitParameter("access_control_allow_origin");
		}
		// 报头内容
		if (StringHelper.isEmpty(access_control_allow_headers)) {
			access_control_allow_headers = getInitParameter("access_control_allow_headers");
		}
		// 请求方式
		if (StringHelper.isEmpty(access_control_allow_methods)) {
			access_control_allow_methods = getInitParameter("access_control_allow_methods");
		}
		// 授信标识
		if (StringHelper.isEmpty(access_control_allow_credentials)) {
			access_control_allow_credentials = getInitParameter("access_control_allow_credentials");
		}
	}
	
	/**
	 * <p>执行自定义过滤定义</p>
	 */
	public void doOwnFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Expose-Headers", "*");
		// 起源类型
		if (StringHelper.isNotEmpty(access_control_allow_origin)) {
			response.setHeader("Access-Control-Allow-Origin", access_control_allow_origin);
		} else {
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		}
		// 请求方式
		if (StringHelper.isNotEmpty(access_control_allow_methods)) {
			response.setHeader("Access-Control-Allow-Methods", access_control_allow_methods);
		} else {
			response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		}
		// 报头内容
		if (StringHelper.isNotEmpty(access_control_allow_headers)) {
			response.setHeader("Access-Control-Allow-Headers", access_control_allow_headers);
		} else {
			response.setHeader("Access-Control-Allow-Headers", "Last-Modified, X-Requested-With, Origin, Content-Type");
		}
		// 授信标识
		if (StringHelper.isNotEmpty(access_control_allow_credentials)) {
			response.setHeader("Access-Control-Allow-Credentials", access_control_allow_credentials);
		} else {
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}
		// 试探回应
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(200);
		} else {
			chain.doFilter(request, response);
		}
	}
}
