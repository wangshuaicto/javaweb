<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeImage()
	{
		var imgele = document.getElementById("imgshow");
		imgele.src = imgele.src+"&"+Math.random();
	}
</script>
</head>
<body>

<form action="<%=request.getContextPath() %>/ServletDemo14">
	<%-- 数字字母验证码：<input type="text" name="checkcode"/>
		<img alt="" src="<%=request.getServletContext().getContextPath()%>/ServletDemo13"> --%>
		<br/>
	验证码：<input type="text" name="checkcode"/>
		<img alt="" id="imgshow" src="<%=request.getContextPath() %>/ServletDemo13?createTypeFlag=ch" onclick="changeImage()">
		<br/>
		<input type="submit" value="提交"/>
</form>

</body>
</html>