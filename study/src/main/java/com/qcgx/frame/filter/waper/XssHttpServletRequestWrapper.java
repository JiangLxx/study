package com.qcgx.frame.filter.waper;

import com.qcgx.frame.utils.CommHelper;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringEscapeUtils;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>防止XSS攻击的请求包装实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	/** 原始请求对象 **/
	private HttpServletRequest originalRequest = null;

	/**
	 * <p>构造函数:初始化相关参数</p>
	 * @param request 请求对象<br>
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request); this.originalRequest = request;
	}
	
	/**
	 * <p>获取查询字符串</p>
	 */
	public String getQueryString() {
		return StringEscapeUtils.escapeHtml4(super.getQueryString());
	}

	/**
	 * <p>根据参数名称获取请求的头部信息</p>
	 */
	public String getHeader(String name) {
		return StringEscapeUtils.escapeHtml4(super.getHeader(name));
	}
	
	/**
	 * <p>根据参数名称获取参数值</p>
	 */
	public String getParameter(String name) {
		return StringEscapeUtils.escapeHtml4(super.getParameter(name));
	}
	
	/**
	 * <p>获取原始的请求对象</p>
	 * @return 请求对象<br>
	 */
	public HttpServletRequest getOriginalRequest() {
		return originalRequest;
	}
	
	/**
	 * <p>根据参数名称获取参数值</p>
	 */
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (CommHelper.isNotEmptyArray(values)) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i ++) {
				escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}
	
	/**
	 * <p>获取最原始的请求对象</p>
	 * @param request 请求对象<br>
	 * @return 请求对象<br>
	 */
	public static HttpServletRequest getOriginalRequest(HttpServletRequest request) {
		return request instanceof XssHttpServletRequestWrapper ? ((XssHttpServletRequestWrapper) request).getOriginalRequest() : request;
	}
}