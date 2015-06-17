package com.test.springmvc.web.controller;

import com.test.springmvc.Controller;
import com.test.springmvc.RequestMapping;
import com.test.springmvc.View;

@Controller
public class LoginUI {

	@RequestMapping(value="springmvc/login")
	public View forward1()
	{
		return new View("/jsp/springmvc/forward1.jsp");
	}
}
