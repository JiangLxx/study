package com.demo.controller;

import com.demo.pojo.WxSignature;
import com.demo.utils.CommHelper;
import com.demo.utils.wx.SignUtil2;
import com.demo.service.ITestWxService;
import com.demo.controller.wx.base.Action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller @RequestMapping("/wx/check")
public class TestWx extends Action {
	@Autowired
	private ITestWxService wxService;
	/**
	 * <p>微信签名验证</p>
	 */
	@ResponseBody @RequestMapping(method = RequestMethod.GET)
	public String check(WxSignature sign) {
		// 校验数据合法性
		if(CommHelper.isNotNull(sign)) { 
			// 校验signature核实请求来源，若校验成功则原样返回echostr,表示接入成功，否则接入失败
			if(SignUtil2.checkSignature(sign)) {
				System.out.println("微信端发出的随机字符串echostr:" + sign.getEchostr());
				return sign.getEchostr();
			}
		}
		return "fail";
	 }
	
	/**
	 * <p>微信消息处理</p>
	 */
	@ResponseBody @RequestMapping(method = RequestMethod.POST)
	public String handleMessage(WxSignature sign) {
		System.out.println(String.valueOf(getRequest()));
		return wxService.processRequest(getRequest());
	}
}
