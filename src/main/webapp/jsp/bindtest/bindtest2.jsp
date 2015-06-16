<%@page import="com.test.bindlistenerdemo.JavaBeanDemo2"%>
<%@page import="com.test.bindlistenerdemo.JavaBeanDemo1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ServletContextAttributeListener   Test</title>
</head>
<body>
	一访问页面就会创建session，session的id是${pageContext.session.id }
	
	<%
		JavaBeanDemo2 bean = new JavaBeanDemo2("wangshuai");
		//将bean绑定到Session  会触发bean绑定事件
		session.setAttribute("bean", bean); 
	%>
</body>
</html>