<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="/ws/tagdemo01" prefix="ws" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>循环标签</h1>
	<%
		int[] data = new int[]{1,23,34,5};
		request.setAttribute("items", data);
	%>
	<ws:foreach items="${items }" var="str">
		${str }<br/>
	</ws:foreach>
</body>
</html>