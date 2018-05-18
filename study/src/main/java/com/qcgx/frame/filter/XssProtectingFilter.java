package com.qcgx.frame.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.qcgx.frame.filter.base.BaseFilter;
import javax.servlet.http.HttpServletResponse;
import com.qcgx.frame.filter.waper.XssHttpServletRequestWrapper;

/**
 * <p>防止XSS攻击过滤器实现类</p>
 * @author JL @date 2018-05-14<br>
 * @version 1.0<br>
 */
public class XssProtectingFilter extends BaseFilter {
	/**
	 * <p>执行过滤功能</p>
	 */
	public void doOwnFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper(request), response);
	}

}
