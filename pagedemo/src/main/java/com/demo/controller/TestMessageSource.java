package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.controller.wx.base.Action;

@Controller @RequestMapping("/test")
public class TestMessageSource extends Action {
	@RequestMapping("/message")
	public void testMessageSource() {
		System.out.println(getMessage("SMS0001", null));
//		System.out.println(getMessage("SMS0001", new String[] {"oprt.search"}));
	}
}
