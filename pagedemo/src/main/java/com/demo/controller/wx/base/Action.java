package com.demo.controller.wx.base;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.demo.utils.CommHelper;
import com.demo.utils.wx.WeiXinUtil;
import com.idiot.utils.base.StringHelper;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;

/**
 * <p>表现层核心服务类</p>
 * @author jianglan @date 2018-05-02<br>
 * @version 1.0<br>
 */
public class Action {
	/** 请求对象 **/
	private HttpServletRequest request;
	private MessageSource messageSource;
	/** 获取日志实例 **/
	private static Logger logger = LoggerFactory.getLogger(WeiXinUtil.class);
	
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
	
	/**
	 * <p>根据信息索引键获取配置信息</p>
	 * @param code 信息索引键<br>
	 * @param params 参数名称<br>
	 * @return 字符串<br>
	 */
	public String getMessage(String code, String[] params) {
		String message = "";
		try {
			Locale locale = getRequest().getLocale();
			if (CommHelper.isNotNull(locale) && CommHelper.isNotEmptyArray(params)) {
				String temp = "";
				for (int i = 0; i < params.length; i ++) {
					temp = messageSource.getMessage(params[i], null, "", locale);
					if (StringHelper.isNotEmpty(temp)) {
						params[i] = temp;
					}
				}
			}
			message = messageSource.getMessage(code, params, locale);
		} catch (Exception ex) {
			ex.printStackTrace(); logger.error(ex.getMessage());
		}
		return message;
	}
}
