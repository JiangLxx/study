package com.demo.controller;

import com.demo.pojo.WxSignature;
import com.demo.utils.CommHelper;
import com.demo.utils.wx.SignUtil2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx")
public class TestWx {
	@ResponseBody @RequestMapping("/check")
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
}
