<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/ws" prefix="wstag" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>输出客户端的地址</title>
</head>
<body>
	你的IP地址：（使用java代码输出）
	<%
		String ip = request.getRemoteAddr(); 
		out.write(ip);
	%>
	<br/>
	你的ip地址：（使用taglib标签输出）
	<wstag:viewip url="/test"/>
</body>
</html>