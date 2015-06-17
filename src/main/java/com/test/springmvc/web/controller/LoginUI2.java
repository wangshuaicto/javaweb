package com.test.springmvc.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.test.springmvc.Controller;
import com.test.springmvc.RequestMapping;
import com.test.springmvc.View;
import com.test.springmvc.WebContext;

/**
 * 复杂测试
 * @author wangshuai
 *
 */
@Controller
public class LoginUI2 {

	@RequestMapping("springmvc2/login")
	public View loginhandler()
	{
		HttpServletRequest reqest = WebContext.requestHolder.get();
		String username = reqest.getParameter("username");
		System.out.println("接收到的请求参数："+username);
		return new View("/jsp/springmvc/forward1.jsp");
	}
	
}
