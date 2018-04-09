package com.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.demo.pojo.User;
import com.demo.service.IUserService;
import com.demo.utils.LPage2;

@Controller("userController")
public class UserController {

	@Resource(name="userServiceImpl")
	private IUserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest req,HttpServletResponse resp,@Param("pageSize") int pageSize) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("**********");
		
		List<User> list = userService.findAllUser();
		
		LPage2<User> page = new LPage2<User>(list, pageSize, req);
		
		System.out.println("page.getPageNo(): " + page.getPageNo());
		System.out.println("page.getPageSize(): " + page.getPageSize());
		
		System.out.println();
		System.out.println(JSON.toJSONString(page));
		
		return JSON.toJSONString(page);
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String list1() {
		
		System.out.println("testing..........");
		
		List<User> list = userService.findAllUser(new RowBounds(0, 3));
		
		return JSON.toJSONString(list);
	}
	
}