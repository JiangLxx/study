package com.qcgx.frame.filter.base;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.*;

/**
 * <p>过滤器基本实现类</p>
 * @author JL @date 2017-05-14<br>
 * @version 1.0<br>
 */
public abstract class BaseFilter implements Filter{
	/** 用户环境 **/
	private FilterConfig config = null;
	/** 会话对象 **/
	private HttpSession session = null;
	
	/**
	 * <p>销毁过滤器时调用的接口函数</p>
	 */
	public void destroy() {
		config = null;
	}
	
	/**
	 * <p>获取会话对象</p>
	 * @return 会话对象<br>
	 */
	public HttpSession getSession() {
		return session;
	}
	
	/**
	 * <p>获取过滤器环境对象</p>
	 * @return 环境对象<br>
	 */
	public FilterConfig getFilterConfig() {
		return config;
	}
	
	/**
	 * <p>获取配置文件中配置的参数值</p>
	 * @param name 参数名<br>
	 * @return 参数值<br>
	 */
	public String getInitParameter(String name) {
		return config.getInitParameter(name);
	}

	/**
	 * <p>初始化运行环境监听函数</p>
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	/**
	 * <p>执行过滤功能实现函数</p>
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request  = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		session = request.getSession(); doOwnFilter(request, response, chain);
	}
	
	/**
	 * <p>自定义过滤功能抽象方法</p>
	 * @param request 请求对象<br>
	 * @param response 响应对象<br>
	 * @param chain 过滤链对象<br>
	 * @throws IOException 异常<br>
	 * @throws ServletException 异常<br>
	 */
	public abstract void doOwnFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

}
