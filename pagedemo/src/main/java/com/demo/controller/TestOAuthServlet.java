package com.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.pojo.wx.authorization.SNSUserInfo;
import com.demo.pojo.wx.authorization.WeixinOauth2Token;
import com.demo.utils.CommHelper;
import com.demo.utils.wx.AdvancedUtil;
import com.demo.utils.wx.constant.Constants;

/**
 * <p>授权后的回调处理</p>
 * @author jianglan @date 2018-05-17<br>
 * @version 1.0<br>
 */
public class TestOAuthServlet extends HttpServlet  {
	/** 默认版本编号 **/
	private static final long serialVersionUID = 7728794810842782090L;
	
	/**
	 * <p>处理请求</p>
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 用户同意授权后，能获取到code
        String code = request.getParameter("code"); String state = request.getParameter("state");
        // 用户同意授权
        if (!"authdeny".equals(code)) {
        	WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(Constants.WX_ACCESS_TOKEN_APPID, Constants.WX_ACCESS_TOKEN_SECRET, code);
        	if(CommHelper.isNotNull(weixinOauth2Token)) {
        		// 用户标识
                String openId = weixinOauth2Token.getOpenid();
        		// 网页授权接口访问凭证
                String accessToken = weixinOauth2Token.getAccess_token();
                // 获取用户信息
                SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
                if(CommHelper.isNotNull(snsUserInfo)) {
                	// 设置要传递的参数
                	request.setAttribute("state", state);
                    request.setAttribute("snsUserInfo", snsUserInfo);
                }
        	}
        }
        // 跳转到index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
